package lpoo.pocketsave.Logic;


import android.support.annotation.NonNull;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

public class Transaction implements Comparable<Transaction>{
    private int id;
    private int value;
    private Date date;
    private String description;
    private boolean done;
    DateFormat df1;
    //Image

    public Transaction(int value, String dateS, String description, int catID, boolean done) {

        this.id = DatabaseSingleton.getInstance().getDB().addTransaction(value, dateS, description, catID, done);
        if (this.id == -1)
            throw new NoSuchElementException("Transaction");
        this.value = value;
        df1 = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
        try {
            this.date = df1.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.description = description;
        this.done = done;
    }



    public int getValue(){
        return value;
    }

    public Date getDate(){
        return this.date;
    }

    public String getDateString(){
        return df1.format(date);
    }

    public String getDescription(){
        return description;
    }


    @Override
    public int compareTo(@NonNull Transaction o) {

        return this.date.compareTo(o.date);
    }
}