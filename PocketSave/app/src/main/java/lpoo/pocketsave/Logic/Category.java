package lpoo.pocketsave.Logic;

import android.util.Log;

import java.io.Console;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeSet;


public class Category {

    int id;
    String title;
    int typeID;
    double estimatedValue;
    double totalSpent;
    TreeSet<Transaction> transactions;


    public Category(String title, int typeID){

        this.id = DatabaseSingleton.getInstance().getDB().addCategory(title, typeID);
        this.typeID = typeID;
        this.title = title;
        this.totalSpent=0;
        transactions = new TreeSet<Transaction>();
    }



    public int getID(){

        return id;
    }

    public void setEstimatedValue(double value){
        this.estimatedValue = value;
    }

    public Transaction addTransaction(int value, String date, String description, boolean done) {

        Transaction transaction=null;
        try {
            transaction = new Transaction(value, date, description, this.id, done);
        }catch (NoSuchElementException a){

            if(a.getMessage() == "Transaction")
                return null;
        }
        transactions.add(transaction);
        addTotalSpent(value);
        return transaction;
    }



    //TODO: transactions
    public TreeSet<Transaction> getTransactionsBetween(Date d1, Date d2){

        TreeSet<Transaction> transactionsBetween = new TreeSet<Transaction>();
       if((transactions.first().getDate().after(d1)|| transactions.first().getDate().equals(d1)) &&
               (d2.before(transactions.last().getDate()) || d2.equals(transactions.last().getDate()))){

              for(Transaction tran: transactions){

                if((tran.getDate().after(d1) || tran.getDate().equals(d1)) && (d2.before(tran.getDate()) || d2.equals(tran.getDate())))
                    transactionsBetween.add(tran);
            }

       }
        return transactionsBetween;
    }


    public void addTotalSpent(double value){
        totalSpent += value;
    }




   /* @Override
    public boolean equals(Object obj){

        if((this.title.equals(((Category)obj).title) && this.type.equals(((Category)obj).type)) || obj == null)
            return true;
        else
            return false;
    }*/

}