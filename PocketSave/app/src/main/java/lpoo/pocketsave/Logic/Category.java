package lpoo.pocketsave.Logic;

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

    public boolean addTransaction(int value, String date, String description, boolean done) {

        Transaction transaction=null;
        try {
            transaction = new Transaction(value, date, description, this.id, done);
        }catch (NoSuchElementException a){

            if(a.getMessage() == "Transaction")
                return false;
        }
        transactions.add(transaction);
        addTotalSpent(value);
        return true;
    }
    //TODO: transactions
    public TreeSet<Transaction> getTransactionsBetween(Date d1, Date d2){

        if((transactions.first().getDate().after(d1) || transactions.first().getDate().equals(d1))
                && (transactions.last().getDate().before(d2)) || transactions.last().getDate().equals(d2)){

            for(Transaction tran: transactions){
                //if(tran.getDate().)
            }
        }
        return transactions;
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