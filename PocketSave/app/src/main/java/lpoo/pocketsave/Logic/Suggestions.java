package lpoo.pocketsave.Logic;


import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Suggestions {


    private static final double PERCENTAGE_DIFFERENCE = 0.4;
    private static final double A_LOT_OF_CASH = 0.7;
    private static final double CASH_FREQ_QUANT = 20;

    private static final String TAG = "Suggestions";

    private final Date d;

    /**
     * Initialize suggestions setting Date instance
     */
    public Suggestions() {

        d = new Date();
    }





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


        if(expectedValues == null || occurredValues == null)
        {
            return null;
        }

        for (HashMap.Entry<String, Double> exp : expectedValues.entrySet()) {
            Log.d(TAG, exp.getKey());
            if(exp.getKey().equals("Income") || exp.getKey().equals("Fixed Expense"))
            {
                continue;
            }
            if ((occurred = occurredValues.get(exp.getKey())) != null) {
                expected = exp.getValue();

                double moneyPerc = 100 - occurred * 100 / expected;

                if (daysLeftperc - moneyPerc > 20) {

                    limitCategories.add(exp.getKey());
                }
            }
        }

        return limitCategories;

    }


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

        if(onLimit == null)
            return null;

        for (String exp : onLimit) {
            cashQuantity = 0;
            numberOfCashTransactions = 0;
            ArrayList<Transaction> trans = DataManager.getInstance().getTransactionsBetweenDates("Category", exp, d1, d2, true);

            if(trans == null)
                return null;
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
        if(catSpent == null)
            return null;
        Double lastMontSum = lastMontSumSpents(catSpent);
        Log.d(TAG, "Last Month Sum: "+lastMontSum);


        Double available = availableCurrentMonth();
        Log.d(TAG, "AVAILABLE THIS MONTH: "+available);

        if(available == null || lastMontSum == null)
            return null;

        if (Math.abs((1 - available / lastMontSum)) < PERCENTAGE_DIFFERENCE) {
            ret = new HashMap<>();

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
    private Double availableCurrentMonth(){

        String dBefore = d.getInitialDate(true, "last");
        String dBeforeEnd = d.getInitialDate(false, "last");

        Double percLastMonth = lastMonthSavings(dBefore, dBeforeEnd);

        if (percLastMonth == null)
            return null;

        String currentDate = d.getInitialDate(true, "current");

        Log.d(TAG," DATA: " +currentDate);


        Double currentFixedExpenses = DataManager.getInstance().getTotalSpentValues("Type", "Fixed Expense", currentDate, currentDate, false).get("Fixed Expense");

        Double currentIncome = DataManager.getInstance().getTotalSpentValues("Type", "Income", currentDate, currentDate, false).get("Income");

        if (currentFixedExpenses == null)
            return null;


        return currentIncome - currentFixedExpenses - percLastMonth * currentIncome;

    }


    /**
     * Gets the total value spent last month
     * @param catSpent Hash map with all categories and spent values
     * @return Returns the total value spent last Month
     */
    private Double lastMontSumSpents(HashMap<String, Double> catSpent){

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
    private Double lastMonthSavings(String dBefore, String dBeforeEnd) {


        Double incomes = 0.0;
        Double balance = 0.0;

        HashMap<String, Double> typeValues = DataManager.getInstance().getTotalSpentValues("Type", null, dBefore, dBeforeEnd, true);
        if(typeValues == null)
            return null;
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

        return balance / incomes;

    }




}
