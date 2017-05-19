package lpoo.pocketsave.Logic;


import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeSet;

import static lpoo.pocketsave.Logic.DatabaseHelper.*;

public class User {

    private String username;
    private double totalSaved;
    private int availableMonth;
    private HashMap<String, Integer> types;
    private HashMap<String, Category> userCategories;
    DateFormat df1;

    static private User instance = null;

    static public User getInstance(){
        if(instance == null) {
            instance = new User();
        }
        return instance;

    }


    public User(){
       this.userCategories = new HashMap<String, Category>();
        this.types = new HashMap<String, Integer>();
        df1 = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
    };

    /**
     * Login the user in the database and set his values in user singleton class using setUser function
     * @param email user email
     * @param password user password
     * @return Returns true if the user logged in with success and false if not
     */
    public boolean login(String email, String password){


        Cursor userInfo = DatabaseSingleton.getInstance().getDB().openUser(email, password);
        if(userInfo != null){

            setUser(userInfo);
            return true;
        }
        return false;
    }

    /**
     * Set user settings in the user singleton class
     * @param userInfo Cursor containing the user data from database
     */
    public void setUser(Cursor userInfo){

        this.username = userInfo.getString(userInfo.getColumnIndex(USER_EMAIL));
        this.totalSaved = userInfo.getDouble(userInfo.getColumnIndex(USER_TOTALSAVED));
        //TODO: only to test. Delete
        setTypes();
        addCategory("cat1", "income");
        addCategory("cat2", "fixed expense");
        addCategory("cat3", "variable expense");


    }

    public void setTypes(){

        addType("income");
        addType("fixed expense");
        addType("variable expense");

    }

    /**
     *
     * @param title
     * @return
     */
    public int getCategoryID(String title){

       return userCategories.get(title).getID();
    }


    public boolean addCategory(String title, String type){

        //get type id
        Integer typeID;
        if((typeID= types.get(type)) == null){
            System.out.println("This type does not exist");
            return false;
        }

        //Verify if this category doesn't already exist, and if not, add it
        if(userCategories.get(title) == null){

            //create category
            Category newCategory = new Category(title, typeID);
            userCategories.put(title, newCategory);
            return true;
        }
        else
            return false;
    }

    public Category getCategory(String title){
        return userCategories.get(title);
    }

    public boolean addType(String title){


        int id;

        //android.util.Log.d("Numero", "addType");
        if((id = DatabaseSingleton.getInstance().getDB().addType(title)) != -1){

            System.out.println(id);
            this.types.put(title, id);
            return true;
        }
        System.out.println(id);
        return false;
    }



    /*public boolean addTransaction(int value, String date, String description, String category){

        Category newCategory;
        if((newCategory = userCategories.get(category)) == null){
            System.out.println("Error getting category\n");
            return false;
        }
        else {
            Transaction newTransaction = new Transaction(value, date, description);
            //newCategory.addTransaction(newTransaction);
            return true;
        }
    }*/

    //TODO: transactions
    public TreeSet<Transaction> getAllTransactionsBetween(String dS1, String dS2){


        TreeSet<Transaction> trans = new TreeSet<Transaction>();
        try {

            Date d1 = df1.parse(dS1);
            Date d2 = df1.parse(dS2);
            TreeSet<Transaction> temporary;


            for(HashMap.Entry<String, Category> it: userCategories.entrySet()){

                temporary = it.getValue().getTransactionsBetween(d1, d2);
                if(temporary.size()>0)
                    for(Transaction tran: temporary)
                        trans.add(tran);
            }
            return trans;


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;


    }

    public String getUsername(){
        return username;
    }

    /*public int getTotalSaved(){
        return totalSaved;
    }*/

    public int getAvailableMonth(){

        return availableMonth;
    }


}