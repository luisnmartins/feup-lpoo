package lpoo.pocketsave.Logic;


public class Transaction {
    private int value;
    private String date;
    private String description;
    //Image

    public Transaction(int value, String date, String description){

        this.value = value;
        this.date = date;
        this.description = description;

    }

    public int getValue(){
        return value;
    }

    public String getDate(){
        return date;
    }

    public String getDescription(){
        return description;
    }


}