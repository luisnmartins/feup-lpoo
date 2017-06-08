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
    private String date;
    private String description;
    private long catID;
    private boolean done;
    private String image;
    private boolean cash;


    /**
     *
     * @param value
     * @param dateS
     * @param catID
     * @param done
     * @param cash
     */
    public Transaction(double value, String dateS, long catID, boolean done, boolean cash){
        this.value = value;
        this.date = dateS;
        this.done = done;
        this.cash = cash;
        this.catID = catID;
    }


    /**
     *
     * @return
     */
    public double getValue(){
        return value;
    }

    public String getDate(){
        return this.date;
    }

    public long getID(){return this.id;}

    public void setID(long value){this.id = value;}

    public long getCatID(){return this.catID;}

    public boolean getDone(){ return this.done;}


    public void setDate(String Date){
        this.date = Date;
    }

    public void setValue(double value){
        this.value = value;
    }

    public String getDescription(){
        return description;
    }

    public String getImage(){return image;}

    public void setImage(String image){
        this.image = image;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int isCashMethod(){return cash? 1: 0;}

    @Override
    public int compareTo(@NonNull Transaction o) {

        return Double.compare(getValue(),o.getValue());
    }

    @Override
    public boolean equals(Object obj) {

            return this.catID == ((Transaction)obj).getCatID() && this.description.equals(((Transaction)obj).getDescription());

    }


    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}