package lpoo.pocketsave.Logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.prefs.PreferenceChangeEvent;


public class DataManager {

    static private UserDB user;
    static private CategoryDB category;
    static private TypeDB type;
    static private TransactionDB transaction;

    private static final String TAG = "DataManager";

    static private DataManager instance = null;

    /**
     * Initialize database Instance
     * @param context Program context
     */
    static public void startDB(Context context) {
        if (user == null) {
            DatabaseHelper.startDB(context);
            user = new UserDB();
            category = new CategoryDB();
            type = new TypeDB();
            transaction = new TransactionDB();
        }
    }

    /**
     * get singleton instance
     * @return Returns singleton instance
     */
    static public DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    /**
     * Add or update an user
     *
     * @param operation  name of the operation - "Add", "Open" or "Update"
     * @param email      new email of the user
     * @param password   new password of the user
     * @param since Date of the account creation. Null if is to update or open
     * @return Returns true if it was added/updated with success, and false if not
     */
    public boolean addOpenUpdateUser(String operation, String email, String password, String since) {

        switch (operation) {
            case "Add":
                return user.addUser(email, password, since);
            case "Update":
                return user.updateUser(email, password, getUser().getTotalSaved());
            case "Open":
                return user.openUser(email, password);
            default:
                return false;
        }

    }

    /**
     * Get instance of user
     *
     * @return Returns an instance of the user that is logged in
     */
    public User getUser() {

        Cursor cursor = user.getUser();
        if (cursor == null)
            return null;
        else if (cursor.moveToFirst()) {
            User currUser = new User(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.USER_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_PASSWORD)));
            currUser.setSince(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_SINCE)));
            Log.d(TAG, "SINCE: "+currUser.getSince());
            cursor.close();
            return currUser;
        }
        return null;
    }



    //TYPE FUNCTIONS


    /**
     * Add a new type and returns the id of the type
     *
     * @param operation name of the operation - "Add" or "Update"
     * @param title     Name of the new type/ name of the type to get ID
     * @return Returns type ID
     */
    public long addGetType(String operation, String title) {

        long id = -1;
        if (operation.equals("Add")) {
            id = type.addType(title);
        } else if (operation.equals("Get")) {
            id = type.getTypeID(title);
        }
        return id;

    }


    //CATEGORY FUNCTIONS

    /**
     * Add a new category or update one that already exists
     *
     * @param operation String to know which operation should be made - "Add" or "Update"
     * @param newCategory Catgeory instance of the category to add
     * @return Returns true if the catgeory was added or updated and false if not
     */
    public boolean addUpdateCategory(String operation, Category newCategory) {

        if (operation.equals("Add")) {
            if (getCategory(newCategory.getTitle(), false, type.getTypeTitle(newCategory.getTypeID())) == null)
                return category.add(newCategory);
            else
                return false;
        } else if (operation.equals("Update")) {
            newCategory.setID(getCategory(newCategory.getTitle(), newCategory.isMainMenu(), type.getTypeTitle(newCategory.getTypeID())).get(0).getID());
            return category.update(newCategory);
        }
        return false;
    }

    /**
     * Get all or only one category. If is called without parameters (null) it will return all user categories
     *
     * @param catTitle Title of the category that should be returned. To get categories to be shown on the main menu,
     *                 this variable should be "mainMenuCategories". If is null it will return all current user's categories
     * @param main     If is a main category then this represents if is to get the 5 visible categories or the other ones.
     *                 If null, it will return return the categories considering only catTitle value.
     * @return Returns an array containing the categories
     */
    public ArrayList<Category> getCategory(String catTitle, Boolean main, String type) {
        Cursor cursor;
        ArrayList<Category> categories = null;
        Category newCategory;
        boolean mainMenu;
        if (catTitle == "mainMenuCategories") {
            cursor = category.getMainCategories(main, type);
        } else
            cursor = category.getCategory(catTitle, type);
        if (cursor == null) {

            return null;
        }
        if (cursor.moveToFirst()) {
            categories = new ArrayList<>();
            do {

                mainMenu = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.CAT_MAIN)) > 0;
                newCategory = new Category(cursor.getString(cursor.getColumnIndex(DatabaseHelper.CAT_TITLE)),
                        cursor.getLong(cursor.getColumnIndex(DatabaseHelper.CAT_TYPE_ID)),
                        mainMenu,
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.CAT_COLOR)));
                newCategory.setID(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.CAT_ID)));
                categories.add(newCategory);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return categories;
    }

    /**
     * Add a new category or update one that already exists
     *
     * @param operation   String to know which operation should be made - "Add" or "Update"
     * @param newTransaction Transaction instance to add or update
     * @return Returns true if the transaction was added or updated and false if not
     */
    public boolean addUpdateTransaction(String operation, Transaction newTransaction){
        if (operation.equals("Add")) {
            return transaction.add(newTransaction);
        } else if (operation.equals("Update")) {
            return transaction.update(newTransaction);
        }
        return false;
    }


    /**
     * get an array with one or all transactions of the asked type
     *
     * @param typeName Name of the type to get transactions
     * @return Returns an hashmap with key equals to the asked transactions and value as the months of that type transaction or null if there is any error
     */
    public HashMap<Transaction, ArrayList<Integer>> getTypeTransaction(String typeName) {


        HashMap<Transaction, ArrayList<Integer>> transactions = null;
        Transaction newTransaction;
        ArrayList<Integer> months;
        boolean flag = false;

        Cursor cursor = transaction.getTypeTransactions(Long.toString(type.getTypeID(typeName)));
        if (cursor == null) {

            return null;
        }
        if (cursor.moveToFirst()) {

            transactions = new HashMap<>();
            do {
                String month = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_DATE));

                month = month.substring(month.indexOf('-') + 1, month.indexOf('-') + 3);
                newTransaction = new Transaction(
                        cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.TRANS_VALUE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_DATE)),
                        cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TRANS_CATEGORY_ID)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TRANS_DONE)) > 0,
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TRANS_CASH)) > 0);
                newTransaction.setID(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TRANS_ID)));
                newTransaction.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_DESCRIPTION)));
                newTransaction.setImage(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_IMAGE)));

                for (HashMap.Entry<Transaction, ArrayList<Integer>> it : transactions.entrySet()) {
                    if (it.getKey().equals(newTransaction)) {
                        it.getValue().add(Integer.valueOf(month));
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                        Log.d(TAG, "È NULL");
                        months = new ArrayList<>();
                        months.add(Integer.valueOf(month));
                        transactions.put(newTransaction, months);

                }
                flag = false;

            } while (cursor.moveToNext());
            cursor.close();

        }
        return transactions;

    }


    /**
     * Get transactions between dates of a specific category or a specific type
     *
     * @param structure          Variable to identify if is to get transactions of a category ("Category") or of a type ("Type")
     * @param catTitle_typeTitle category title or type title to get transactions
     * @param d1                 initial date
     * @param d2                 final date
     * @param done               variable to define if it is to get transactions that was already done or the ones that will happen in the future
     * @return Returns an array containing the transactions
     */
    public ArrayList<Transaction> getTransactionsBetweenDates(String structure, String catTitle_typeTitle, String d1, String d2, boolean done) {

        ArrayList<Transaction> transactions = null;
        Transaction newTransaction;
        Cursor cursor = null;
        if (structure.equals("Category"))
            cursor = transaction.getCatTransactionsBetweenDates(catTitle_typeTitle, d1, d2, done);
        else if (structure.equals("Type"))
            cursor = transaction.getTypeTransactionsBetweenDates(Long.toString(type.getTypeID(catTitle_typeTitle)), d1, d2, done);

        if (cursor == null) {

            Log.d(TAG, "CUROSR NULL");
            return null;
        }

        if (cursor.moveToFirst()) {

            transactions = new ArrayList<Transaction>();
            do {
                newTransaction = new Transaction(
                        cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.TRANS_VALUE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_DATE)),
                        cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TRANS_CATEGORY_ID)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TRANS_DONE)) > 0,
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TRANS_CASH)) > 0);
                newTransaction.setID(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TRANS_ID)));
                newTransaction.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_DESCRIPTION)));
                newTransaction.setImage(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_IMAGE)));

                transactions.add(newTransaction);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return transactions;

    }


    /**
     * Get total values spent of all categories or a specific category or a specific type
     *
     * @param structure          Variable to identify if is to get transactions of a category ("Category") or of a type ("Type")
     * @param catTitle_typeTitle category title or type title to get spent values
     * @param d1                 initial date
     * @param d2                 final date
     * @param done               variable to define if it is to get transactions that was already done or the ones that will happen in the future
     * @return Returns an hashmap with the category/type name as key and the spent value as value
     */
    public HashMap<String, Double> getTotalSpentValues(String structure, String catTitle_typeTitle, String d1, String d2, Boolean done) {

        HashMap<String, Double> categories = null;
        String catType;
        double value;
        Cursor cursor;

        if (structure.equals("Category"))
            cursor = transaction.getCategoryTotalValueSpent(catTitle_typeTitle, d1, d2, done);
        else if (structure.equals("Type"))
            cursor = transaction.getTypeTotalValueSpent(catTitle_typeTitle, d1, d2, done);
        else
            return null;

        if (cursor == null) {
            Log.d(TAG, "CURSOS NULL");
            return null;
        }

        if (cursor.moveToFirst()) {
            categories = new HashMap<String, Double>();

            do {
                catType = cursor.getString(0);
                value = cursor.getDouble(1);
                Log.d(TAG, "CAT: " + catType);
                Log.d(TAG, "VALUE: " + value);
                categories.put(catType, value);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return categories;
    }

    /**
     * Delete and element from database
     *
     * @param element type of the element - "Transaction", "Category" or "User"
     * @param id      ID of the element to delete. To delete user this should be null
     * @return Returns true id the element that was deleted and false if not
     */
    public boolean deleteElements(String element, String id) {

        switch (element) {
            case "Transaction":
                return transaction.delete(id);
            case "Category":
                return category.delete(id);
            case "User":
                return user.delete();
            default:
                return false;

        }
    }

    /**
     * Delete all data from DataBase
     */
    public void clearDB(){
        transaction.deleteAllTransactions();
        category.deleteAllCategories();
        user.deleteAllUsers();
    }


}
