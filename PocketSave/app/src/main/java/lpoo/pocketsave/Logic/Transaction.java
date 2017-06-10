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
     * Initialize a transaction instance
     * @param value Transaction's value
     * @param dateS Transaction's date as "yyyy-MM-dd"
     * @param catID Category id of the transaction
     * @param done True if the transaction already happened and false otherwise
     * @param cash True if it was paied with money and false otherwise
     */
    public Transaction(double value, String dateS, long catID, boolean done, boolean cash){
        this.value = value;
        this.date = dateS;
        this.done = done;
        this.cash = cash;
        this.catID = catID;
    }


    /**
     * Get Transaction's Value
     * @return Returns Transaction's value
     */
    public double getValue(){
        return value;
    }

    /**
     * Get Transaction's Date
     * @return Returns Transaction's Date
     */
    public String getDate(){
        return this.date;
    }

    /**
     * Get Transaction's ID
     * @return Returns Transaction's ID
     */
    public long getID(){return this.id;}


    /**
     * Get Transaction's Description
     * @return Returns Transaction's descriptions
     */
    public String getDescription(){
        return description;
    }

    /**
     * Get Transaction's Image
     * @return Returns Transaction's Image
     */
    public String getImage(){return image;}

    /**
     * Get Transaction's category ID
     * @return Returns transaction's category ID
     */
    public long getCatID(){return this.catID;}

    /**
     * Get if the Transaction is done
     * @return Returns if the Transaction is done
     */
    public boolean getDone(){ return this.done;}

    /**
     * Get if the Transaction was paied with cash
     * @return Returns if the Transaction was paied with cash
     */
    public int isCashMethod(){return cash? 1: 0;}

    /**
     * Set Transaction's ID
     * @param id New ID
     */
    public void setID(long id){this.id = id;}

    /**
     * Set Transaction's Date
     * @param Date New Date
     */
    public void setDate(String Date){
        this.date = Date;
    }

    /**
     * Set Transaction's value
     * @param value New value
     */
    public void setValue(double value){
        this.value = value;
    }

    /**
     * Set Transaction's Image
     * @param image New image
     */
    public void setImage(String image){
        this.image = image;
    }

    /**
     * Set Transaction's Description
     * @param description New description
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Compares 2 transaction verifying if the value is the same
     * @param o Transactions to compare with
     * @return Returns negative if this.value < o.getValue, 0 if they are equals and true if this.value > o.getValue()
     */
    @Override
    public int compareTo(@NonNull Transaction o) {

        return Double.compare(getValue(),o.getValue());
    }

    /**
     * Verify if 2 Transactions are the same comparing the category ID and the Description. To be used in incomes and fixed expenses
     * @param obj Transaction object to compare with
     * @return Returns true if they are equals and false otherwise
     */
    @Override
    public boolean equals(Object obj) {

            return this.catID == ((Transaction)obj).getCatID() && this.description.equals(((Transaction)obj).getDescription());

    }

    /**
     * Code to order the transactions recyclerview
     * @return Returns the position of the transaction in the recyclerview
     */
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}