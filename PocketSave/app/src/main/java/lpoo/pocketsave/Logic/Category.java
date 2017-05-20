package lpoo.pocketsave.Logic;

import android.database.Cursor;
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

    public Transaction addTransaction(double value, String date, String description, boolean done) {

        Transaction transaction=null;
        try {
            transaction = new Transaction(value, date, description, this.id, done);
        }catch (NoSuchElementException a){

            if(a.getMessage() == "Transaction")
                return null;
        }
        transactions.add(transaction);
        totalSpent += value;
        return transaction;
    }



    //TODO: transactions
    public TreeSet<Transaction> getTransactionsBetween(Date d1, Date d2){

        TreeSet<Transaction> transactionsBetween = new TreeSet<Transaction>();
        Date temp_date;
       if((d1.after(transactions.first().getDate()) || d1.equals(transactions.first().getDate())) &&
               (d2.before(transactions.last().getDate()) || d2.equals(transactions.last().getDate()))) {

           for (Transaction tran : transactions) {

               temp_date = tran.getDate();
               if ((temp_date.after(d1) || temp_date.equals(d1)) &&
                       (temp_date.before(d2) || temp_date.equals(d2)))

                       transactionsBetween.add(tran);

           }
       }
       else
       {
           Cursor cursor = DatabaseSingleton.getInstance().getDB().getTransactionsBetween(d1, d2, this.id);
           Transaction newTransaction;
           do{
               boolean done = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TRANS_DONE))>0;
                newTransaction = new Transaction(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TRANS_VALUE)), cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_DATE)), cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TRANS_CATEGORY_ID)), done);
                transactionsBetween.add(newTransaction);
           }while(cursor.moveToNext());


       }
        return transactionsBetween;
    }


    public double getTransactionsTotalValue(Date d1, Date d2){

        double total=0;
        Date temp_date;
        if((d1.after(transactions.first().getDate()) || d1.equals(transactions.first().getDate())) &&
                (d2.before(transactions.last().getDate()) || d2.equals(transactions.last().getDate()))) {

            for (Transaction tran : transactions) {

                temp_date = tran.getDate();
                if ((temp_date.after(d1) || temp_date.equals(d1)) &&
                        (temp_date.before(d2) || temp_date.equals(d2)))

                    total+=tran.getValue();

            }
            return total;
        }
        else{
            return DatabaseSingleton.getInstance().getDB().getTransactionsTotalValue(d1, d2, this.id);
        }
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