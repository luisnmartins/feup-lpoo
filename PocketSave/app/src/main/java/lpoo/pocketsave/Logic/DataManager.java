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
     * @param totalSaved Total value saved by the user. Set 0 if your adding or opening a user
     * @return Returns true if it was added/updated with success, and false if not
     */
    public boolean addOpenUpdateUser(String operation, String email, String password, double totalSaved){

        if(operation == "Add"){
            return db.addUser(email, password);
        }else if(operation == "Update"){
            return db.updateUser(email, password, totalSaved);
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


    //TYPE FUNCTIONS


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
     * Add a new category or update one that already exists
     * @param operation String to know which operation should be made - "Add" or "Update"
     * @param id Id to identify the category that should be updated. Set -1 if is to add a new category
     * @param title Title of the category that should be added or updated
     * @param type type of the category that should be added or updated
     * @param mainMenu Boolean representing if this category is one of the 5 main categories or not
     * @return Returns true if the catgeory was added or updated and false if not
     */
    public boolean addUpdateCategory(String operation, long id, String title,String type, boolean mainMenu){
        Category newCategory;
        newCategory = new Category(-1, title, addGetType("Get", type), mainMenu);
        if(operation == "Add"){
            if(getCategory(title, false, type) == null)
                return db.addCategory(newCategory);
            else
                return false;
        }else if(operation == "Update"){
            return db.updateCategory(newCategory);
        }
        return false;
    }

    /**
     * Get all or only one category. If is called without parameters (null) it will return all user categories
     * @param catTitle Title of the category that should be returned. To get categories to be shown on the main menu,
     *             this variable should be "mainMenuCategories". If is nul it will return all current user's categories
     * @param main If is a main category then this represents if is to get the 5 visible categories or the other ones.
     *             If null, it will return return the categories considering only catTitle value.
     * @return Returns an array containing the categories
     */
    public ArrayList<Category> getCategory(String catTitle, Boolean main,String type){
        Cursor cursor;
        ArrayList<Category> categories=null;
        Category newCategory;
        boolean mainMenu;
        if(catTitle=="mainMenuCategories"){
            cursor = db.getMainCategories(main,type);
        }
        else
            cursor= db.getCategory(catTitle,type);
        if(cursor == null) {

            return null;
        }
        if(cursor.moveToFirst()) {
            categories = new ArrayList<Category>();
            do {

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
     * Add a new category or update one that already exists
     * @param operation String to know which operation should be made - "Add" or "Update"
     * @param id Id to identify the category that should be updated. Set -1 if is to add a new transaction
     * @param value Value of the transaction to be added or updated
     * @param date Date of the transaction to be added or updated
     * @param description Date of the transaction to be added or updated
     * @param catID ID of the category of the new transaction
     * @param done True if the transaction is already done and false if not
     * @param image Path od the receipt image
     * @return
     */
    public boolean addUpdateTransaction( String operation, long id, double value, String date, String description, long catID, boolean done, String image, boolean cash){
        Transaction newTransaction = new Transaction(id, value, date, description, catID, done, image, cash);
        if(operation == "Add"){
            return db.addTransaction(newTransaction);
        }else if(operation == "Update"){
            return db.updateTransaction(newTransaction);
        }
        return false;
    }


    /**
     * get an array with one or all transactions of the asked type
     * @param type Name of the type to get transactions
     * @return Returns an array with asked transactions, or null if there is any error
     */
    public ArrayList<Transaction> getTypeTransaction(String type){


        ArrayList<Transaction> transactions = null;
        Transaction newTransaction;


        Cursor cursor = db.getTypeTransactions(type);
        if(cursor == null)
            return null;
        if(cursor.moveToFirst()){

            transactions = new ArrayList<Transaction>();
            do{

                newTransaction = new Transaction(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TRANS_ID)),
                        cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.TRANS_VALUE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_DATE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_DESCRIPTION)),
                        cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TRANS_CATEGORY_ID)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TRANS_DONE))>0,
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_IMAGE)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TRANS_CASH))>0);

                transactions.add(newTransaction);
            }while(cursor.moveToNext());
            cursor.close();

        }
        return transactions;

    }






    /**
     * Get transactions between dates of a specific category or a specific type
     * @param structure Variable to identify if is to get transactions of a category ("Category") or of a type ("Type")
     * @param catTitle_typeTitle category title or type title to get transactions
     * @param d1 initial date
     * @param d2 final date
     * @param done variable to define if it is to get transactions that was already done or the ones that will happen in the future
     * @return Returns an array containing the transactions
     */
    public ArrayList<Transaction> getTransactionsBetweenDates(String structure, String catTitle_typeTitle, String d1, String d2, boolean done){

        ArrayList<Transaction> transactions = null;
        Transaction newTransaction;
        Cursor cursor= null;
        if(structure == "Category")
            cursor = db.getCatTransactionsBetweenDates(catTitle_typeTitle, d1, d2, done);
        else if (structure == "Type")
            cursor = db.getTypeTransactionsBetweenDates(catTitle_typeTitle, d1, d2, done);

        if(cursor == null){

            Log.d(TAG, "CUROSR NULL");
            return null;
        }


        if(cursor.moveToFirst()){

            transactions = new ArrayList<Transaction>();
            do{
                newTransaction = new Transaction(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TRANS_ID)),
                                                 cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.TRANS_VALUE)),
                                                 cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_DATE)),
                                                 cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_DESCRIPTION)),
                                                 cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TRANS_CATEGORY_ID)),
                                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TRANS_DONE))>0,
                                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_IMAGE)),
                                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TRANS_CASH))>0);

                transactions.add(newTransaction);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return transactions;

    }


    /**
     * Get total values spent of all categories or a specific category or a specific type
     * @param structure Variable to identify if is to get transactions of a category ("Category") or of a type ("Type")
     * @param catTitle_typeTitle category title or type title to get spent values
     * @param d1 initial date
     * @param d2 final date
     * @param done variable to define if it is to get transactions that was already done or the ones that will happen in the future
     * @return Returns an hashmap with the category/type name as key and the spent value as value
     */
    public HashMap<String, Double> getTotalSpentValues(String structure, String catTitle_typeTitle, String d1, String d2, Boolean done){

        HashMap<String, Double> categories= null;
        String catType;
        double value;
        Cursor cursor;
        if(structure == "Category")
            cursor = db.getCategoryTotalValueSpent(catTitle_typeTitle, d1, d2, done);
        else if(structure == "Type")
            cursor = db.getTypeTotalValueSpent(catTitle_typeTitle, d1, d2, done);
        else
            return null;

        if(cursor == null) {
            return null;
        }


        if(cursor.moveToFirst()){
            categories = new HashMap<String, Double>();

            do{
                catType = cursor.getString(0);
                value = cursor.getDouble(1);
                Log.d(TAG, "CAT: "+catType);
                Log.d(TAG, "VALUE: "+value);
                categories.put(catType, value);

            }while(cursor.moveToNext());
            cursor.close();
        }
        return categories;
    }

    /**
     * Delete and element from database
     * @param element type of the element - "Transaction", "Category" or "User"
     * @param id ID of the element to delete. To delete user this should be null
     * @return Returns true id the element that was deleted and false if not
     */
    public boolean deleteElements(String element, String id){

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
