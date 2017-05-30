package lpoo.pocketsave.Logic;


import android.provider.CalendarContract;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Suggestions {

    ArrayList<Integer> daysofmonths = new ArrayList<Integer>();


    public Suggestions(){

        int year = Calendar.getInstance().get(Calendar.YEAR);
        daysofmonths.add(31);
        if(year%4==0){
            if(year%100 ==0){
                if(year%400==0)
                    daysofmonths.add(29);
                else
                    daysofmonths.add(28);
            }else
                daysofmonths.add(29);
        }else
            daysofmonths.add(28);
        daysofmonths.add(31);
        daysofmonths.add(30);
        daysofmonths.add(31);
        daysofmonths.add(30);
        daysofmonths.add(31);
        daysofmonths.add(31);
        daysofmonths.add(30);
        daysofmonths.add(31);
        daysofmonths.add(30);
        daysofmonths.add(31);
    }


    //CORRENTE

    //TODO: verificar se uma categoria esta acima (20%) do seu valor previsto comparativamente com o que ja passou do mes corrente
    public HashMap<String, Boolean> OnlimitCategory(String category){

        HashMap<String, Boolean> limitCategories = new HashMap<String, Boolean>();
        Double occurred;
        Double expected;

        Calendar c = Calendar.getInstance();
        String d1 = c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-01"; //first day of the current month
        String d2 = c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH); //current date


        HashMap<String, Double> expectedValues = DataManager.getInstance().getTotalSpentValues("Category", null, d1, d1, false);
        HashMap<String, Double> occurredValues = DataManager.getInstance().getTotalSpentValues("Category", null, d1, d2, true);
        double daysLeftperc = (daysofmonths.get(c.get(Calendar.MONTH)-1) - c.get(Calendar.DAY_OF_MONTH))/daysofmonths.get(c.get(Calendar.MONTH)-1)*100;

        for (HashMap.Entry<String, Double> exp : expectedValues.entrySet()){

            if((occurred=occurredValues.get(exp.getKey())) != null){
                expected = exp.getValue();
                double moneyPerc = (expected-occurred)/expected*100;

                if(daysLeftperc-moneyPerc > 20)
                    limitCategories.put(exp.getKey(), true);
            }
        }

        return limitCategories;

    }



    //TODO: Verificar o metodo de pagamaneto dominante e se for dinheiro verificar se o valor medio das transacoes
        // e de baixa quantia (<20€) - para categorias que estao muito proximo do limite



    //INICIO MES

    //TODO: comparacao do ultimo mes para cada categoria verificando as que ficaram a baixo e as que ficaram a cima do valor previsto

    //TODO: verificar valores medios para os valores previstos e valores efetivos para cada categoria desde o inicio dos registos

    //TODO: sugestao dos valores aproximados para o mes seguinte tendo em conta a variacao em termos de receitas
    // e de despesas fixas e mantendo a mesma percentagem de poupanca do mes passado



}
