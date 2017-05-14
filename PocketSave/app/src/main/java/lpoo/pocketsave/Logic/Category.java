package lpoo.pocketsave.Logic;

import java.util.ArrayList;


public class Category {

    int id;
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

    public int getID(){

        return id;
    }

    public void setEstimatedValue(double value){
        this.estimatedValue = value;
    }

    public void addTransaction(int value, String date, String description) {

        Transaction transaction = new Transaction(value, date, description);
        transactions.add(transaction);
        DatabaseSingleton.getInstance().getDB();
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