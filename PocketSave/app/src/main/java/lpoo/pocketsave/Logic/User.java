package lpoo.pocketsave.Logic;


import android.database.Cursor;

import java.util.HashMap;

import static lpoo.pocketsave.Logic.DatabaseHelper.*;

public class User {

    private String username;
    private double totalSaved;
    private int availableMonth;
    private HashMap<String, Category> userCategories;

    static private User instance = null;

    static public User getInstance(){
        if(instance == null) {
            instance = new User();
        }
        return instance;

    }


    public User(){
       this.userCategories = new HashMap<String, Category>();
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

    }

    /**
     *
     * @param title
     * @return
     */
    public int getCategoryID(String title){

       return userCategories.get(title).getID();
    }


    public Category addCategory(String title, String type){


        Category newCategory;
       if(!userCategories.isEmpty()){
        if((newCategory = userCategories.get(title)) == null){

            //newCategory = new Category(title, type, myDB);
            userCategories.put(title, newCategory);
        }
       }
       else
        newCategory = new Category(title, type);

        return newCategory;
    }

    public boolean addTransaction(int value, String date, String description, String category){

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