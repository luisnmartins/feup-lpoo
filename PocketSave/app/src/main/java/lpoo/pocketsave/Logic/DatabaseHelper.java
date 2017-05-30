package lpoo.pocketsave.Logic;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.RelativeDateTimeFormatter;
import android.provider.Settings;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "Database";

    public static final String DATABASE_NAME = "PocketSave.db";

    //Tables
    public static final String TABLE_USER = "user_table";
    public static final String TABLE_TRANSACTION = "transaction_table";
    public static final String TABLE_CATEGORY = "category_table";
    public static final String TABLE_TYPE = "type_table";

    //User Table Columns
    public static final String USER_ID = "_id";
    public static final String USER_NAME = "Username";
    public static final String USER_EMAIL = "Email";
    public static final String USER_PASSWORD = "Password";
    public static final String USER_TOTALSAVED = "TotalSaved";

    //Transaction Table Columns
    public static final String TRANS_ID = "_id";
    public static final String TRANS_DESCRIPTION = "Description";
    public static final String TRANS_VALUE = "Value";
    public static final String TRANS_DATE = "Date";
    public static final String TRANS_DONE = "Done";
    public static final String TRANS_CATEGORY_ID = "Category_ID";
    public static final String TRANS_IMAGE = "Image";

    //Category Table Columns
    public static final String CAT_ID = "_id";
    public static final String CAT_TITLE = "Title";
    public static final String CAT_TYPE_ID = "Type_ID";
    public static final String CAT_USER_ID = "User_ID";
    public static  final String CAT_MAIN = "main_menu";

    //Type Table Columns
    public static final  String TYPE_ID = "_id";
    public static final String TYPE_NAME = "Name";


    private User currUser;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //TODO: delete

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_user = "create table " + TABLE_USER + " ("+USER_ID+" INTEGER PRIMARY KEY, "+
                USER_NAME+" STRING UNIQUE, "+ USER_EMAIL+" STRING UNIQUE, "+USER_PASSWORD+" STRING NOT NULL, "+
                USER_TOTALSAVED+" REAL)";
        String create_category = "create table "+ TABLE_CATEGORY+" ("+CAT_ID+" INTEGER PRIMARY KEY, "+
                CAT_TITLE+" STRING, "+CAT_MAIN+" BOOLEAN NOT NULL, "+CAT_TYPE_ID+" INTEGER REFERENCES "+TABLE_TYPE+" ("+TYPE_ID+"), "+CAT_USER_ID+" INTEGER REFERENCES "+TABLE_USER+" ("+USER_ID+"))";
        String create_type = "create table "+ TABLE_TYPE + " ("+TYPE_ID+" INTEGER PRIMARY KEY, "+TYPE_NAME+" STRING NOT NULL UNIQUE)";
        String create_transaction = "create table "+TABLE_TRANSACTION+" ("+TRANS_ID+" INTEGER PRIMARY KEY, "+
                TRANS_VALUE+" REAL NOT NULL, "+TRANS_DATE+" STRING NOT NULL, "+
                TRANS_DESCRIPTION+ " STRING, "+TRANS_DONE+" BOOLEAN NOT NULL, "+TRANS_IMAGE+" STRING, " +TRANS_CATEGORY_ID+" INTEGER REFERENCES "+TABLE_CATEGORY + " ("+CAT_ID+"))";

        Log.d(TAG, create_category);

        System.out.println(create_user);
        Log.d(TAG, create_category);
        System.out.println(create_type);
        Log.d(TAG, create_transaction);


    try{
        db.execSQL(create_type);
        db.execSQL(create_user);
        db.execSQL(create_category);
        db.execSQL(create_transaction);
        System.out.println("Success querying\n");
    } catch (SQLException e){
        System.out.println("Error querying\n");
    }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        onCreate(db);
    }


    //USER FUNCTIONS

    /**
     * Add a new User to the db
     * @param email new User email
     * @param password new User password
     * @return Returns true if the user was added and false if not
     */
    public boolean addUser(String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_EMAIL,email);
        contentValues.put(USER_PASSWORD, password);
        contentValues.put(USER_TOTALSAVED, 0);
        long result = db.insert(TABLE_USER,null ,contentValues);
        if(result == -1){
            Log.d(TAG, "Error adding the new user\n");
            return false;
        }
        else
        {
            if(openUser(email, password))
                return true;
            else
                return false;
        }

    }

    /**
     * Get the new user from the database
     * @param email user email
     * @param password user password
     * @return Return a new instance of user
     */
    public boolean openUser(String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER, null, USER_EMAIL + "=? AND "+USER_PASSWORD+ "=?", new String[]{email, password}, null, null, null);
        if(cursor == null || cursor.getCount()<1)
            return false;
        if(cursor.moveToFirst()) {

            currUser = new User(cursor.getLong(cursor.getColumnIndex(USER_ID)),
                                cursor.getString(cursor.getColumnIndex(USER_EMAIL)),
                                cursor.getString(cursor.getColumnIndex(USER_PASSWORD)),
                                cursor.getDouble(cursor.getColumnIndex(USER_TOTALSAVED)));

            return true;
        }
        else{
            cursor.close();
            return false;
        }

    }

    /**
     * Update current user info
     * @param email email to be updated
     * @param password password to be updated
     * @return Returns true if user was updated and false if not
     */
    public boolean updateUser(String email, String password, double totalSaved){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_EMAIL,email);
        contentValues.put(USER_PASSWORD, password);
        contentValues.put(USER_TOTALSAVED, totalSaved);
        if(db.update(TABLE_USER, contentValues, USER_ID+"= ?",new String[] { Long.toString(currUser.getID()) })>0) {
            return openUser(email, password);
        }
        else
            return false;

    }

    /**
     * Delete the current user from the db
     * @return Returns true if it was deleted and false if not
     */
    public boolean deleteUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_USER, USER_ID+"=?",new String[]{Long.toString(currUser.getID())})>0;
    }


    /**
     *
     * @return Returns an instance of the current User
     */
    public User getUser(){
        return this.currUser;
    }


    //TYPE FUNCTIONS


    /**
     * Add a new type
     * @param title name of the type
     */
    public long addType(String title){

        Log.d(TAG, "USER: "+currUser.getID());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TYPE_NAME, title);
        Log.d(TAG, "Type added successfully\n");
        return db.insert(TABLE_TYPE,null, contentValues);



    }


    /**
     * get the type which name is the param
     * @param title Name of the type to get
     * @return Returns the requested type id
     */
    public long getTypeID(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_TYPE, new String[]{TYPE_ID}, TYPE_NAME + "=?", new String[]{title}, null, null, null);
        if(cursor == null || cursor.getCount()<1)
            return -1;
        if(cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndex(TYPE_ID));
        }
        cursor.close();
        return -1;

    }


    //CATEGORY FUNCTIONS


    /**
     * Add a new Category to the dataBase
     * @param newCategory Category instance that should be to add
     * @return Returns true if category was added and false if not
     */
    public boolean addCategory(Category newCategory){


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CAT_USER_ID, Long.toString(currUser.getID()));
        contentValues.put(CAT_TITLE, newCategory.getTitle());
        contentValues.put(CAT_TYPE_ID, Long.toString(newCategory.getTypeID()));
        contentValues.put(CAT_MAIN, newCategory.isMainMenu());
        long result = db.insert(TABLE_CATEGORY,null, contentValues);
        if(result == -1)
            return false;
        else
        {
            System.out.println("Category added successfully\n");
            newCategory.setID(result);
            return true;
        }

    }


    /**
     * Update a category info
     * @param toUpdate category instance with the updted info to update db
     */
    public boolean updateCategory(Category toUpdate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAT_TITLE, toUpdate.getTitle());
        contentValues.put(CAT_TYPE_ID, toUpdate.getTypeID());
        contentValues.put(CAT_MAIN, toUpdate.isMainMenu());
        int result =  db.update(TABLE_CATEGORY, contentValues, CAT_ID+"=?",new String[] { Long.toString(toUpdate.getID()) });
        return result>0;

    }



    /**
     * Delete the category with id equals to the param
     * @param id id of the category that should be deleted
     * @return Returns true if the category was deleted and false if not
     */
    public boolean deleteCategory(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_CATEGORY, CAT_ID+"=?",new String[]{id})>0;

    }


    /**
     * Get a instance of the category
     * @param name name of the category for which the instance should be returned
     * @return Returns and instance of the category. Returns null if was not possible to get data.
     */
    public Cursor getCategory(String name, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        if(name == null){
            cursor = db.rawQuery("SELECT C."+CAT_ID+", C."+CAT_TITLE+", C."+CAT_MAIN+", C."+CAT_TYPE_ID+", C."+CAT_USER_ID+" FROM "+
                    TABLE_CATEGORY+" C, "+TABLE_TYPE+" P WHERE C."+CAT_TYPE_ID+" = P."+TYPE_ID+" AND P."+TYPE_NAME+" = '"+
                    type+ "' AND C."+CAT_USER_ID+" = '"+currUser.getID()+ "'", null );
        }else{
            cursor = db.query(TABLE_CATEGORY, null, CAT_USER_ID + "=? AND "+CAT_TITLE+"=?", new String[]{Long.toString(currUser.getID()), name}, null, null, null);
        }

        if(cursor == null || cursor.getCount()<1) {

            return null;
        }
        else
            return cursor;

    }


    /**
     * Get categories to be shown on the main menu
     * @param main if is true it returns info of the 5 pricipal catgeories, and if false it returns the other ones
     * @return Returns a cursor with the asked categories info
     */
    public Cursor getMainCategories(boolean main, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT C."+CAT_ID+", C."+CAT_TITLE+", C."+CAT_MAIN+", C."+CAT_TYPE_ID+", C."+CAT_USER_ID+" FROM "+
                                TABLE_CATEGORY+" C, "+TABLE_TYPE+" P WHERE C."+CAT_TYPE_ID+" = P."+TYPE_ID+" AND P."+TYPE_NAME+" = '"+
                            type+ "' AND C."+CAT_USER_ID+" = '"+currUser.getID()+ "' AND C."+CAT_MAIN+" = '"+((main) ? 1 : 0)+"'", null );


        if(cursor == null || cursor.getCount()<1) {
            cursor.close();
            return null;
        }
        else
            return cursor;
    }


    //TRANSACTION FUNCTIONS



    /**
     * Add a new transaction to the db
     * @param newTransaction New transaction to be added
     * @return Returns true if the new transaction was added, and false if not
     */
    public boolean addTransaction(Transaction newTransaction){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANS_DESCRIPTION, newTransaction.getDescription());
        contentValues.put(TRANS_VALUE, newTransaction.getValue());
        contentValues.put(TRANS_DATE, newTransaction.getDate());
        contentValues.put(TRANS_CATEGORY_ID, newTransaction.getCatID());
        contentValues.put(TRANS_DONE, newTransaction.getDone());
        long result = db.insert(TABLE_TRANSACTION,null, contentValues);
        if(result == -1)
            return false;
        else
        {
            System.out.println("Category added successfully\n");
            newTransaction.setID(result);
            return true;
        }


    }


    /**
     * Update the values of a transaction with the values of the Transaction in param
     * @param toUpdate Transaction instance with the values updated to update db
     * @return Returns true if any transaction was updated and false if not
     */
    public boolean updateTransaction(Transaction toUpdate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANS_ID, toUpdate.getID());
        contentValues.put(TRANS_VALUE, toUpdate.getValue());
        contentValues.put(TRANS_DATE, toUpdate.getDate());
        contentValues.put(TRANS_DESCRIPTION, toUpdate.getDescription());
        contentValues.put(TRANS_CATEGORY_ID, toUpdate.getCatID());
        contentValues.put(TRANS_DONE, toUpdate.getDone());
        contentValues.put(TRANS_IMAGE, toUpdate.getImage());
        return db.update(TABLE_TRANSACTION, contentValues, TRANS_ID+"= ?",new String[] { Long.toString(toUpdate.getID()) })>0;

    }

    /**
     * Delete a transaction from the db
     * @param id Id of the transaction to be deleted
     * @return Returns true if transaction was deleted and false if not
     */
    public boolean deleteTransaction(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_TRANSACTION, TRANS_ID+"=?",new String[]{id})>0;
    }



    /**
     * Get the transaction of the given type
     * @param typeTitle Type of the transactions to get
     * @return Returns a curosr with the query result
     */
    public Cursor getTypeTransactions(String typeTitle){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        String query = "SELECT T."+TRANS_ID+", T."+TRANS_VALUE+", T."+TRANS_DATE+", T."+TRANS_DESCRIPTION+", T."+TRANS_CATEGORY_ID+", T."+
        TRANS_DONE+", T."+TRANS_IMAGE+" FROM "+TABLE_TRANSACTION+" T, "+TABLE_CATEGORY+" C WHERE C."+CAT_TYPE_ID+" = "+getTypeID(typeTitle)+ " AND C."+CAT_USER_ID+" = "+currUser.getID()+" ORDER BY T.Date";
        cursor = db.rawQuery(query, null);

        /*if(id == null){
            cursor = db.rawQuery("SELECT T."+TRANS_ID+", T."+TRANS_VALUE+", T."+
                                  TRANS_DATE+", T."+TRANS_DESCRIPTION+", T."+TRANS_CATEGORY_ID+
                                 ", T." + TRANS_DONE + " FROM "+TABLE_TRANSACTION+" T JOIN "+TABLE_CATEGORY+
                                 " C ON T."+TRANS_CATEGORY_ID+" = C."+CAT_ID+
                                 " WHERE C."+CAT_USER_ID+" = '"+currUser.getID()+"' AND T."+TRANS_DONE+" = "+1, null);


        }else {
            cursor = db.query(TABLE_TRANSACTION, null, " _id = ? AND "+TRANS_DONE, new String[]{id, Integer.toString(1)},null, null, TRANS_DATE);
        }*/
        if(cursor == null || cursor.getCount()<1){
            cursor.close();
            return null;
        }

        else{

            return cursor;

        }


    }


    /**
     * Get all transactions between d1 and d2 from catTitle Category
     * @param catTitle category name of the transactions
     * @param d1 initial date
     * @param d2 final date
     * @param done variable to define if it is to get transactions that was already done or the ones that will happen in the future
     * @return Returns a cursor to the query result
     */
    public Cursor getCatTransactionsBetweenDates(String catTitle, String d1, String d2, boolean done){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT T."+TRANS_ID+", T."+TRANS_VALUE+", T."+TRANS_DATE+", T."+TRANS_DESCRIPTION+", T."+TRANS_CATEGORY_ID+", T."+
                TRANS_DONE+", T."+TRANS_IMAGE+" FROM "+TABLE_TRANSACTION+" T, "+TABLE_CATEGORY+
                " C WHERE T.Date BETWEEN '"+ d1+"' AND '"+d2+"' AND C."
                + CAT_TITLE+ " = '"+catTitle+"' AND C."+CAT_ID+" = T."+TRANS_CATEGORY_ID+" AND T."+TRANS_DONE+" = "+((done) ? 1 : 0)+" AND C."+CAT_USER_ID+" = "+currUser.getID()+" ORDER BY Date";
        Cursor cursor  = db.rawQuery(query, null);


        if(cursor == null || cursor.getCount()<1)
            return null;
        else
            return cursor;

    }


    /**
     * Get all transactions between d1 and d2 from typeTitle Type
     * @param typeTitle type of the transactions
     * @param d1 initial date
     * @param d2 final date
     * @param done variable to define if it is to get transactions that was already done or the ones that will happen in the future
     * @return Returns a cursor to the query result
     */
    public Cursor getTypeTransactionsBetweenDates( String typeTitle, String d1, String d2, boolean done){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT T."+TRANS_ID+", T."+TRANS_VALUE+", T."+TRANS_DATE+", T."+TRANS_DESCRIPTION+", T."+TRANS_CATEGORY_ID+", T."+
                TRANS_DONE+", T."+TRANS_IMAGE+" FROM "+TABLE_TRANSACTION+" T, "+TABLE_CATEGORY+" C WHERE T.Date BETWEEN '"+
                d1+"' AND '"+d2+"' AND C."+CAT_TYPE_ID+" = "+getTypeID(typeTitle)+ " AND C."+CAT_USER_ID+" = "+currUser.getID()+" AND T."+TRANS_DONE+" = "+((done) ? 1 : 0)+" ORDER BY T.Date";
        Log.d(TAG, query);
        Cursor cursor = db.rawQuery(query, null);
        if(cursor == null || cursor.getCount()<1){
            cursor.close();
            return null;
        }
        else
            return cursor;

    }


    /**
     * Get total value spent in transactions of the category which name is catTitle
     * @param catTitle title of the category to get total spent value
     * @param d1 initial date
     * @param d2 final date
     * @param done variable to define if it is to get values of the transactions that was already done or the ones that will happen in the future
     * @return Returns a cursor with the name of the category and total value spent
     */
    public Cursor getCategoryTotalValueSpent(String catTitle, String d1, String d2,boolean done){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        if(catTitle == null){
            String query = "SELECT C."+CAT_TITLE+", SUM(T."+TRANS_VALUE+") FROM "+
                    TABLE_TRANSACTION+" T, "+TABLE_CATEGORY+" C WHERE "+
                    "T."+TRANS_CATEGORY_ID+ " = C."+CAT_ID+
                    " AND T."+ TRANS_DATE+" BETWEEN '"+ d1+"' AND '"+d2+"' AND T."+TRANS_DONE+" = "+((done) ? 1 : 0)+
                    " GROUP BY C."+CAT_TITLE;
            cursor = db.rawQuery(query, null);
        }
        else
            cursor = db.rawQuery("SELECT C."+CAT_TITLE+", SUM(T."+TRANS_VALUE+") FROM "+
                TABLE_TRANSACTION+" T, "+TABLE_CATEGORY+" C WHERE "+
                "T."+TRANS_CATEGORY_ID+ " = C."+CAT_ID+
                " AND T."+ TRANS_DATE+" BETWEEN '"+ d1+"' AND '"+d2+
                "' AND C."+CAT_TITLE+ " = "+catTitle+" AND T."+TRANS_DONE+" = "+((done) ? 1 : 0)+ " GROUP BY C."+CAT_TITLE, null);

        if(cursor == null || cursor.getCount()<1){
            cursor.close();
            return null;
        }
        else {

            if(cursor.moveToFirst()){

                return cursor;
            }else{

                return null;
            }

        }
    }


    /**
     * Get total value spent in transactions of the type which name id typeTitle
     * @param typeTitle title of the type to get total spent value
     * @param d1 Initial Date
     * @param d2 Final Date
     * @param done variable to define if it is to get values of the transactions that was already done or the ones that will happen in the future
     * @return Returns a cursor with the name of the category and total value spent
     */
    public Cursor getTypeTotalValueSpent(String typeTitle, String d1, String d2, boolean done){

        SQLiteDatabase db = this.getReadableDatabase();
        Double value;
        Cursor cursor= db.rawQuery("SELECT P."+TYPE_NAME+", SUM(T."+TRANS_VALUE+") FROM "+
                                    TABLE_TRANSACTION+" T, "+TABLE_CATEGORY+" C, "+TABLE_TYPE+" P " +
                                    "WHERE P."+TYPE_ID+ " = C."+CAT_TYPE_ID+ " AND T."+TRANS_CATEGORY_ID +" = "+
                                    "C."+CAT_ID+ " AND P."+TYPE_NAME+" = '"+typeTitle+ "' AND C."+CAT_USER_ID+ " = "+currUser.getID()+" AND T."+TRANS_DONE+" = "+((done) ? 1 : 0)+
                                    " GROUP BY P."+TYPE_NAME,null);

        if(cursor == null || cursor.getCount()<1){
            cursor.close();
            return null;
        }
        else {

            if(cursor.moveToFirst()) {
                return cursor;
            }else
                return null;
        }

    }





}