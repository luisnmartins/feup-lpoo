package lpoo.pocketsave.Logic;

import android.content.Context;


public class PocketSave {


    private DatabaseHelper myDB;
    private Context context;
    private User currUser;
    static private PocketSave instance = null;

    static public PocketSave getInstance(){
        if(instance == null) {
            instance = new PocketSave();
        }
        return instance;

    }

    public void startTypes(){

        myDB.addType("income");
        myDB.addType("fixed expense");
        myDB.addType("variable expense");
    }

    public void addTransaction(int value, String date, String description, String category){

        //currUser.addTransaction(value, date, description, category);
        myDB.addTransaction(value, date, description, currUser.getCategoryID(category));

    }

    public void addCategory(String title, String type){
        currUser.addCategory(title, type);
        myDB.addCategory(title, 0);
    }

    public User getUser(){

        return currUser;
    }

    private PocketSave(){
        //myDB.deleteAllData();
        this.currUser = new User();


    }

    public DatabaseHelper createDB(Context context){

        myDB = new DatabaseHelper(context);
        startTypes();
        return myDB;
    }

    public DatabaseHelper getDB(){

        return myDB;
    }




}