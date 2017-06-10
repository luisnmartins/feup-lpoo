package lpoo.pocketsave.Logic;


import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Suggestions {


    static final double PERCENTAGE_DIFERENCE = 0.4;
    static final double A_LOT_OF_CASH = 0.7;
    static final double CASH_FREQ_QUANT = 20;

    private static final String TAG = "Suggestions";

    private Date d;

    /**
     * Initialize suggestions setting Date instance
     */
    public Suggestions() {

        d = new Date();
    }




    //TODO: verificar se uma categoria esta acima (20%) do seu valor previsto comparativamente com o que ja passou do mes corrente

    /**
     * Verifies if any category's spents is big comparing with the day of the month
     * @return Returns an array with the name of the categories in this situation
     */
    public ArrayList<String> onlimitCategory() {


        ArrayList<String> limitCategories = new ArrayList<>();
        Double occurred;
        Double expected;
        Calendar c = Calendar.getInstance();
        String d1 = d.getInitialDate(true, "current");
        String d2 = d.getInitialDate(false, "currentDate");
        HashMap<String, Double> expectedValues = DataManager.getInstance().getTotalSpentValues("Category", null, d1, d1, false);
        HashMap<String, Double> occurredValues = DataManager.getInstance().getTotalSpentValues("Category", null, d1, d2, true);
        double daysLeftperc = 100 - c.get(Calendar.DAY_OF_MONTH) * 100 / d.getDaysofMonth(c.get(Calendar.MONTH));
        Log.d(TAG, "DIAS: " + daysLeftperc);

        if(expectedValues == null)
        {
            return null;
        }

        for (HashMap.Entry<String, Double> exp : expectedValues.entrySet()) {
            Log.d(TAG, exp.getKey());
            if ((occurred = occurredValues.get(exp.getKey())) != null) {
                expected = exp.getValue();

                double moneyPerc = 100 - occurred * 100 / expected;
                Log.d("TESTES: FIM ", "Dias: " + daysLeftperc);
                Log.d("TESTES: FIM ", "DINHEIRO: " + moneyPerc);
                if (daysLeftperc - moneyPerc > 20) {
                    Log.d("TESTES: FIM ", exp.getKey());
                    limitCategories.add(exp.getKey());
                }
            }
        }

        return limitCategories;

    }


    //TODO: Verificar o metodo de pagamento dominante e se for dinheiro verificar se o valor medio das transacoes
    // e de baixa quantia (<20€) - para categorias que estao muito proximo do limite

    /**
     * For the limit categories, it verifies if there's a lot of transactions of cash
     * and in that case it verifies if the average quantity in not higher than 20€
     * @return Returns an array with the name of the categories taht are in that situation
     */
    public ArrayList<String> limitCashMethodCategory() {

        ArrayList<String> onLimit = onlimitCategory();
        ArrayList<String> ret = new ArrayList<>();
        int cashQuantity;
        int numberOfCashTransactions;


        String d1 = d.getInitialDate(true, "current");
        String d2 = d.getInitialDate(false, "currentDate");

        for (String exp : onLimit) {
            cashQuantity = 0;
            numberOfCashTransactions = 0;
            ArrayList<Transaction> trans = DataManager.getInstance().getTransactionsBetweenDates("Category", exp, d1, d2, true);

            for (int i = 0; i < trans.size(); i++) {
                if (trans.get(i).isCashMethod() > 0) {
                    cashQuantity += trans.get(i).getValue();
                    numberOfCashTransactions++;
                }
            }

            if (numberOfCashTransactions / trans.size() >= A_LOT_OF_CASH) {

                if (cashQuantity / numberOfCashTransactions < CASH_FREQ_QUANT) {
                    ret.add(exp);
                }
            }

        }
        return ret;

    }

    /**
     * Gets the name of categories which spent money in the previous month was higher than that was expected
     * @return Returns an array with the name of the categories in that situation
     */
    public ArrayList<String> compareCatValuesBefore() {

        Calendar c = Calendar.getInstance();
        String dBefore = d.getInitialDate(true, "last");
        String dBeforeEnd = d.getInitialDate(false, "last");
        ArrayList<String> ret = new ArrayList<>();

        if (dBeforeEnd == null)
            return null;

        HashMap<String, Double> history = DataManager.getInstance().getTotalSpentValues("Category", null, dBefore, dBeforeEnd, true);
        HashMap<String, Double> estimated = DataManager.getInstance().getTotalSpentValues("Category", null, dBefore, dBefore, false);
        Double value;

        if(estimated == null || history == null)
        {
            return null;
        }
        for (HashMap.Entry<String, Double> it : estimated.entrySet()) {

            if (it.getKey().equals("Income") || it.getKey().equals("Fixed Expense")) {
                continue;
            }

            if((value = history.get(it.getKey())) != null) {

                if (it.getValue() < value) {
                    ret.add(it.getKey());
                }
            }
        }


        return ret;


    }




    /**
     * Suggests an expense value for each category, considering the available money in the current month
     * and the money spent in the last month in each category
     *
     * @return Returns an hashmap with value as the name of the category and the value as the suggested value to that category
     */
    public HashMap<String, Double> suggestCatValues() {
        HashMap<String, Double> ret= null;

        String dBefore = d.getInitialDate(true, "last");
        String dBeforeEnd = d.getInitialDate(false, "last");


        HashMap<String, Double> catSpent = DataManager.getInstance().getTotalSpentValues("Category", null, dBefore, dBeforeEnd, true);

        Double lastMontSum = lastMontSumSpents(catSpent);
        Log.d(TAG, "Last Month Sum: "+lastMontSum);


        Double available = availableCurrentMonth();
        Log.d(TAG, "AVAILABLE THIS MONTH: "+available);


        if (Math.abs((1 - available / lastMontSum)) < PERCENTAGE_DIFERENCE) {
            ret =  new HashMap<String, Double>();

            for (HashMap.Entry<String, Double> it : catSpent.entrySet()) {

                if (!(it.getKey().equals("Fixed Expense") || it.getKey().equals("Income")))
                    ret.put(it.getKey(), (it.getValue() / lastMontSum) * available);
            }
        }


        return ret;

    }


    /**
     * Calculates the money available to spend in variable expenses in the current month,
     * considering the same savings percentage of the previous month
     * @return Returns the quantity available in th current month
     */
    public Double availableCurrentMonth(){

        String dBefore = d.getInitialDate(true, "last");
        String dBeforeEnd = d.getInitialDate(false, "last");

        Double percLastMonth = lastMonthSavings(dBefore, dBeforeEnd);

        if (percLastMonth == null)
            return null;

        String currentDate = d.getInitialDate(true, "current");

        Double currentIncome = DataManager.getInstance().getTotalSpentValues("Type", "Income", currentDate, currentDate, false).get("Income");

        Double currentFixedExpenses = DataManager.getInstance().getTotalSpentValues("Type", "Fixed Expense", currentDate, currentDate, false).get("Fixed Expense");


        if (currentFixedExpenses == null)
            return null;


        Double available = currentIncome - currentFixedExpenses - percLastMonth * currentIncome;

        return available;

    }


    /**
     * Gets the total value spent last month
     * @param catSpent Hash map with all categories and spent values
     * @return Returns the total value spent last Month
     */
    public Double lastMontSumSpents(HashMap<String, Double> catSpent){

        Double lastMontSum=0.0;

        for (HashMap.Entry<String, Double> it : catSpent.entrySet()) {
            if (!it.getKey().equals("Fixed Expense") && !it.getKey().equals("Income")) {
                lastMontSum += it.getValue();

            }
        }

        return lastMontSum;
    }



    /**
     * Calculates the last Month savings percentage
     *
     * @return Returns a double with the percentage of money that was not spent last month
     */
    public Double lastMonthSavings(String dBefore, String dBeforeEnd) {


        Double incomes = 0.0;
        Double balance = 0.0;

        HashMap<String, Double> typeValues = DataManager.getInstance().getTotalSpentValues("Type", null, dBefore, dBeforeEnd, true);
        for (HashMap.Entry<String, Double> it : typeValues.entrySet()) {
            if (it.getKey().equals("Income")) {
                incomes = it.getValue();
                balance += incomes;


            } else {
                balance -= it.getValue();
            }
        }

        if (balance < 0)
            return null;

        Double perc = balance / incomes;

        return perc;

    }




}
