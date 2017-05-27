package lpoo.pocketsave.Logic;


import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Transaction implements Comparable<Transaction>{
    private long id;
    private double value;
    private Date date;
    private String description;
    private long catID;
    private boolean done;
    DateFormat df1;
    //Image

    public Transaction(long id, double value, String dateS, String description, long catID, boolean done) {

        this.id = id;
        this.value = value;
        df1 = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
        try {
            this.date = df1.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.description = description;
        this.done = done;
        this.catID = catID;
    }



    public double getValue(){
        return value;
    }

    public Date getDate(){
        return this.date;
    }

    public long getID(){return this.id;}

    public void setID(long value){this.id = value;}

    public long getCatID(){return this.catID;}

    public boolean getDone(){ return this.done;}

    public String getDateString(){
        return df1.format(date);
    }

    public String getDescription(){
        return description;
    }


    @Override
    public int compareTo(@NonNull Transaction o) {

        return Double.compare(getValue(),o.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        return this.id == ((Transaction)obj).getID();
    }
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}