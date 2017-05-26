package lpoo.pocketsave.Logic;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class DataManager {

    static private DatabaseHelper db;

    private static final String TAG = "DataManager";

    static private DataManager instance = null;

    static public void startDB(Context context){
        if(db == null) {
        db = new DatabaseHelper(context);
        }
    }

    static public DataManager getInstance(){
        if(instance == null)
            instance = new DataManager();
        return instance;
    }

    /**
     * Add or update an user
     * @param operation name of the operation - "Add", "Open" or "Update"
     * @param email new email of the user
     * @param password new password of the user
     * @return Returns true if it was added/updated with success, and false if not
     */
    public boolean addOpenChangeUser(String operation, String email, String password){

        if(operation == "Add"){
            return db.addUser(email, password);
        }else if(operation == "Update"){
            return db.updateUser(email, password);
        }else if(operation == "Open"){
            return db.openUser(email, password);
        }
        return false;
    }

    /**
     * Get instance of user
     * @return Returns an instance of the user that is logged in
     */
    public User getUser(){
        return db.getUser();
    }

    /**
     * Add a new type and returns the id of the type
     * @param operation name of the operation - "Add" or "Update"
     * @param title Name of the new type/ name of the type to get ID
     * @return Retuns type ID
     */
    public long addGetType(String operation, String title){

        long id=-1;
        if(operation == "Add"){
            id = db.addType(title);
        }
        else if(operation == "Get"){
            id = db.getTypeID(title);
        }
        return id;

    }


    //CATEGORY FUNCTIONS

    /**
     *
     * @param operation
     * @param id
     * @param title
     * @param type
     * @param mainMenu
     * @return
     */
    public boolean addChangeCategory(String operation, long id, String title,String type, boolean mainMenu){
        Category newCategory;
        if(title== null || type == null)
            newCategory= new Category(id, null, -1, false);
        else
            newCategory = new Category(id, title, addGetType("Get", type), mainMenu);
        if(operation == "Add"){
            if(getCategory(title) == null)
                return db.addCategory(newCategory);
            else
                return false;
        }else if(operation == "Update"){
            return db.updateCategory(newCategory);
        }
        return false;
    }

    /**
     * Get all or only one category. If is called without parameters ( null) it will return all user categories
     * @param name Name of the category that should be returned. To get categories to be shown on the main menu,
     *             this variable value should be "mainMenuCategories"
     *
     * @return Returns an array containing the categories
     */
    public ArrayList<Category> getCategory(String name){
        Cursor cursor;
        ArrayList<Category> categories=null;
        Category newCategory;
        boolean mainMenu;
        if(name == "mainMenuCategories"){
            cursor = db.getMainCategories();
        }
        else
            cursor= db.getCategory(name);
        if(cursor == null) {
            return null;
        }


        if(cursor.moveToFirst()) {
            do {
                categories = new ArrayList<Category>();
                mainMenu = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.CAT_MAIN)) > 0;
                newCategory = new Category(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.CAT_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.CAT_TITLE)),
                        cursor.getLong(cursor.getColumnIndex(DatabaseHelper.CAT_TYPE_ID)),
                        mainMenu);
                categories.add(newCategory);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return categories;
    }

    /**
     *
     * @param operation
     * @param id
     * @param value
     * @param date
     * @param description
     * @param catID
     * @param done
     * @return
     */
    public boolean addChangeTransaction( String operation, long id, double value, String date, String description, long catID, boolean done){
        Transaction newTransaction = new Transaction(id, value, date, description, catID, done);
        if(operation == "Add"){
            return db.addTransaction(newTransaction);
        }else if(operation == "Update"){
            return db.updateTransaction(newTransaction);
        }
        return false;
    }



    /**
     * Get transactions between dates or not, from a category or not. If is called with null parameters
     * all categories will be returned.
     * @param catTitle_transID category title or transaction id to get
     * @param d1 initial date to get transactions between dates.
     * @param d2 final date to get transactions between dates.
     * @return Returns an array containing the transactions
     */
    public ArrayList<Transaction> getTransactions(String catTitle_transID, String d1, String d2){

        ArrayList<Transaction> transactions = null;
        Transaction newTransaction;
        boolean done;
        Cursor cursor;
        if(d1 != null && d2 != null){
         cursor = db.getTransactionsBetween(d1, d2, catTitle_transID);
        }
        else{
         cursor = db.getTransaction(catTitle_transID);
        }
        if(cursor == null || cursor.getCount()<1)
            return null;
        if(cursor.moveToFirst()){

            transactions = new ArrayList<Transaction>();
            do{
                done = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TRANS_DONE))>0;
                newTransaction = new Transaction(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TRANS_ID)),
                                                 cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.TRANS_VALUE)),
                                                 cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_DATE)),
                                                 cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_DESCRIPTION)),
                                                 cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TRANS_CATEGORY_ID)),
                                                 done);

                transactions.add(newTransaction);
            }while(cursor.moveToNext());
            cursor.close();
        }

        return transactions;

    }


    /**
     *
     * @param d1
     * @param d2
     * @param typeTitle
     * @return
     */
    public HashMap<String, Double> getCategoriesValues(Date d1, Date d2, String typeTitle){

        HashMap<String, Double> categories= null;
        String category;
        double value;
        Cursor cursor;
        cursor = db.getTransactionsTotalValue(d1, d2, typeTitle);
        if(cursor == null)
            return null;
        if(cursor.moveToFirst()){

            do{
                category = cursor.getString(0);
                value = cursor.getDouble(1);
                categories.put(category, value);

            }while(cursor.moveToNext());
            cursor.close();
        }
        return categories;
    }

    /**
     * Delete and element from database
     * @param element type of the element - "Transaction", "Category" or "User"
     * @param id ID of the element to delete
     * @return Returns true id the element was deleted and false if not
     */
    public boolean DeleteElements(String element, long id){

        if(element.equals("Transaction")){
            return db.deleteTransaction(id);
        }else if( element.equals("Category")){
            return db.deleteCategory(id);
        }else if( element.equals("User")){
            return db.deleteUser();
        }
        return false;

    }


}
