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
                USER_TOTALSAVED+" INTEGER)";
        String create_category = "create table "+ TABLE_CATEGORY+" ("+CAT_ID+" INTEGER PRIMARY KEY, "+
                CAT_TITLE+" STRING, "+CAT_MAIN+" BOOLEAN NOT NULL, "+CAT_TYPE_ID+" INTEGER REFERENCES "+TABLE_TYPE+" ("+TYPE_ID+"), "+CAT_USER_ID+" INTEGER REFERENCES "+TABLE_USER+" ("+USER_ID+"))";
        String create_type = "create table "+ TABLE_TYPE + " ("+TYPE_ID+" INTEGER PRIMARY KEY, "+TYPE_NAME+" STRING NOT NULL UNIQUE)";
        String create_transaction = "create table "+TABLE_TRANSACTION+" ("+TRANS_ID+" INTEGER PRIMARY KEY, "+
                TRANS_VALUE+" REAL NOT NULL, "+TRANS_DATE+" STRING NOT NULL, "+
                TRANS_DESCRIPTION+ " STRING, "+TRANS_DONE+" BOOLEAN NOT NULL, "+ TRANS_CATEGORY_ID+" INTEGER REFERENCES "+TABLE_CATEGORY + " ("+CAT_ID+"))";

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
     *
     * @param email
     * @param password
     * @return
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
        //Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_USER+" WHERE "+USER_EMAIL+" = '"+email+"' AND "+USER_PASSWORD+" = '"+password+"'", null);
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

    public boolean updateUser(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_EMAIL,email);
        contentValues.put(USER_PASSWORD, password);
        return db.update(TABLE_USER, contentValues, USER_ID+"= ?",new String[] { Long.toString(currUser.getID()) })>0;
    }


    public boolean deleteUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_USER, USER_ID+"=?",new String[]{Long.toString(currUser.getID())})>0;
    }



    public User getUser(){
        return this.currUser;
    }


    //TYPE FUNCTIONS


    /**
     * Add a new type
     * @param title name od the type
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
     * Get a instance of the category
     * @param name name of the category for which the instance should be returned
     * @return Returns and instance of the category. Returns null if was not possible to get data.
     */
    public Cursor getCategory(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        if(name == null){
            cursor = db.query(TABLE_CATEGORY, null, CAT_USER_ID + "=?", new String[]{Long.toString(currUser.getID())}, null, null, null);
        }else{
            cursor = db.query(TABLE_CATEGORY, null, CAT_USER_ID + "=? AND "+CAT_TITLE+"=?", new String[]{Long.toString(currUser.getID()), name}, null, null, null);
        }

        if(cursor == null || cursor.getCount()<1) {

            return null;
        }

        if(cursor.moveToFirst()){

            return cursor;
        }
        cursor.close();
        return null;

    }



    public boolean deleteCategory(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_CATEGORY, CAT_ID+"=?",new String[]{Long.toString(id)})>0;

    }


    /**
     *
     * @param toUpdate
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
    //TODO: get main categories
    public Cursor getMainCategories(){
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_CATEGORY+" WHERE "+CAT_MAIN+" = '"+Boolean.toString(true)+"' AND "+CAT)
        Cursor cursor = db.query(TABLE_CATEGORY, null, CAT_MAIN+"=? AND "+CAT_USER_ID+"=?", new String[]{Long.toString(1), Long.toString(currUser.getID())}, null, null, null);
        if(cursor == null || cursor.getCount()<1) {
            Log.d(TAG, "Cursor null");
            return null;
        }
        if(cursor.moveToFirst()){

            return cursor;
        }
        cursor.close();
        return null;
    }


    //TRANSACTION FUNCTIONS


    //TODO: verify return type of add functions

    /**
     *
     * @param newTransaction
     */
    public boolean addTransaction(Transaction newTransaction){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANS_DESCRIPTION, newTransaction.getDescription());
        contentValues.put(TRANS_VALUE, newTransaction.getValue());
        contentValues.put(TRANS_DATE, newTransaction.getValue());
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



    public boolean updateTransaction(Transaction toUpdate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANS_ID, toUpdate.getID());
        contentValues.put(TRANS_VALUE, toUpdate.getValue());
        contentValues.put(TRANS_DATE, toUpdate.getDateString());
        contentValues.put(TRANS_DESCRIPTION, toUpdate.getDescription());
        contentValues.put(TRANS_CATEGORY_ID, toUpdate.getCatID());
        contentValues.put(TRANS_DONE, toUpdate.getDone());
        return db.update(TABLE_TRANSACTION, contentValues, TRANS_ID+"= ?",new String[] { Long.toString(toUpdate.getID()) })>0;

    }

    public boolean deleteTransaction(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_TRANSACTION, TRANS_ID+"=?",new String[]{Long.toString(id)})>0;
    }



    /**
     * Get the transaction with the given id
     * @param id id of the transaction
     * @return Returns an instance of the requested transaction
     */
    public Cursor getTransaction(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        if(id == null){
            cursor = db.query(TABLE_TRANSACTION, null, null, null, null, null, null);
        }else {
            cursor = db.query(TABLE_TRANSACTION, null, " _id = ?", new String[]{id}, null, null, null);
        }
        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_ID));
        if(cursor == null || cursor.getCount()<1)
            return null;
        if(cursor.moveToFirst()) {

            return cursor;

        }
        cursor.close();
        return null;

    }




    public Cursor getTransactionsBetween(String d1, String d2, String catTitle){
        SQLiteDatabase db = this.getWritableDatabase();
        //SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");

        Cursor cursor;

        if(catTitle != null)
           cursor  = db.rawQuery("SELECT * FROM "+TABLE_TRANSACTION+" T, "+TABLE_CATEGORY+
                   " C WHERE T.Date BETWEEN '"+ d1+"' AND '"+d2+"' AND C."
                   + CAT_TITLE+ " = '"+catTitle+"' AND C."+CAT_ID+" = T."+TRANS_CATEGORY_ID+" ORDER BY Date", null);

        else
            cursor  = db.rawQuery("SELECT * FROM "+TABLE_TRANSACTION+" WHERE Date BETWEEN '"+ d2+"' AND '"+d2+"' ORDER BY Date", null);


        if(cursor == null || cursor.getCount()<1)
            return null;
        if(cursor.moveToFirst()) {


            return cursor;
        }
        cursor.close();
        return null;

    }



    public Cursor getTransactionsTotalValue(Date d1, Date d2, String typeTitle){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");

        Cursor cursor = db.rawQuery("SELECT "+ "C."+CAT_TITLE+", SUM(T."+TRANS_VALUE+") FROM "+
                TABLE_TRANSACTION+" T INNER JOIN "+TABLE_CATEGORY+" C ON"+
                "T."+TRANS_CATEGORY_ID+ " = C."+CAT_ID+
                " WHERE T."+ TRANS_DATE+" BETWEEN '"+ df1.format(d1)+"' AND '"+df1.format(d2)+
                "' AND C."+CAT_TYPE_ID+ " = "+typeTitle+ " GROUP BY C."+CAT_TITLE, null);

        if(cursor == null || cursor.getCount()<1)
            return null;
        if(cursor.moveToFirst()){
            return cursor;
        }
        else {
            cursor.close();
            return null;
        }
    }











}