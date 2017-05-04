package lpoo.pocketsave.Logic;


import java.util.Date;

public class Transaction {
    String title;
    int value;
    Date date;
    String description;
    //Image

    public Transaction(String title, int value, Date date, String description){

        this.title = title;
        this.value = value;
        this.date = date;
        this.description = description;
    }




}
