package lpoo.pocketsave.Logic;

import android.content.Context;
import android.util.Log;


public class PocketSave {

    private static final String TAG = "PocketSave";

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

    /**
     * Sign in in the user account
     * @param email user email
     * @param password user password
     * @return Returns true if logged in successfully and false if not
     */
    public boolean signin(String email, String password){

        if((currUser = myDB.openUser(email, password)) != null){

            Log.d(TAG, "User logged in");
            return true;
        }
        else {
            Log.d(TAG, "Error trying to log in\n");
            return false;
        }

    }

    /**
     * Sign up a new user
     * @param email user email
     * @param password user password
     * @return Returns true if user was added and false if not
     */
    public boolean signup(String email, String password){

        currUser = new User(email);
        return myDB.addUser(currUser, password);

    }

    //TODO: verify best place for this

    /**
     * Inicialize default types
     */
    public void setInicialTypes(){
        addType("income");
        addType("fixed expense");
        addType("variable expense");
    }

    /**
     * Add a new type
     * @param title type title
     * @return Returns true if type was added and false if not
     */
    public boolean addType(String title){
        long id;
        if((id= myDB.addType(title) )!= -1) {
            currUser.addType(title, id);
            return true;
        }
        else{
            return false;
        }

    }


    public boolean addCategory(String title, String type){

        Category newCategory;
        if((newCategory =currUser.addCategory(title, type)) != null){

            return myDB.addCategory(newCategory);
        }
        else {
            return false;
        }
    }


    /*public Transaction addTransaction(double value, String date, String description, boolean done){

        Transaction newTransaction = currUser.addTransaction(value, date, description, done);
        myDB.addTransaction(value, date, description, currUser.getCategoryID(category));

    }

    public void addCategory(String title, String type){
        currUser.addCategory(title, type);
        myDB.addCategory(title, 0);
    }*/

    public User getUser(){

        return currUser;
    }

    private PocketSave(){};

    public DatabaseHelper createDB(Context context){

        myDB = new DatabaseHelper(context);
        //startTypes();
        return myDB;
    }

    public DatabaseHelper getDB(){

        return myDB;
    }




}