package lpoo.pocketsave.Logic;

import android.content.Context;


public class DatabaseSingleton {

    private DatabaseHelper myDB;

    static private DatabaseSingleton instance = null;

    static public DatabaseSingleton getInstance(){
        if(instance == null) {
            instance = new DatabaseSingleton();
        }
        return instance;
    }

    public DatabaseHelper getDB(){
        return myDB;
    }

    public void createDB(Context context){
        this.myDB = new DatabaseHelper(context);
        System.out.println("Database created\n");
    }

}
