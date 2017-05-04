package lpoo.pocketsave.Logic;

import java.util.ArrayList;

/**
 * Created by luisnmartins on 03/05/2017.
 */

public class Category {

    String title;
    String type;
    double estimatedValue;
    double totalSpent;
    ArrayList<Transaction> transactions;


    public Category(String title, String type){
        this.title = title;
        this.type = type;
        this.totalSpent=0;
    }

    public void setEstimatedValue(double value){
        this.estimatedValue = value;
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }


    public void addTotalSpent(double value){
        totalSpent += value;
    }


    @Override
    public boolean equals(Object obj){

        if((this.title.equals(((Category)obj).title) && this.type.equals(((Category)obj).type)) || obj == null)
            return true;
        else
            return false;
    }

}
