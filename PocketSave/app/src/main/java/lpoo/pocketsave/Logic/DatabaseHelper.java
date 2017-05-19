package lpoo.pocketsave.Logic;

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
import java.util.Date;


public class DatabaseHelper extends SQLiteOpenHelper {
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
    public static final String TRANS_USER_ID = "User_ID";
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

    //Type Table Columns
    public static final  String TYPE_ID = "_id";
    public static final String TYPE_NAME = "Name";


    String [] USER_COLUMNS = {USER_ID, USER_EMAIL, USER_PASSWORD, USER_TOTALSAVED};

    static private DatabaseHelper instance = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_user = "create table " + TABLE_USER + " ("+USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                USER_NAME+" VARCHAR(30), "+ USER_EMAIL+" VARCHAR(30) UNIQUE, "+USER_PASSWORD+" VARCHAR(30) NOT NULL, "+
                USER_TOTALSAVED+" INTEGER)";
        String create_category = "create table "+ TABLE_CATEGORY+" ("+CAT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                CAT_TITLE+" VARCHAR(30) UNIQUE, "+CAT_TYPE_ID+" INTEGER REFERENCES "+TABLE_TYPE+" ("+TYPE_ID+"))";
        String create_type = "create table "+ TABLE_TYPE + " ("+TYPE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TYPE_NAME+" VARCHAR(30) NOT NULL UNIQUE)";
        String create_transaction = "create table "+TABLE_TRANSACTION+" ("+TRANS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TRANS_VALUE+" INTEGER NOT NULL, "+TRANS_DATE+" STRING NOT NULL, "+
                TRANS_DESCRIPTION+ " VARCHAR(100), "+TRANS_DONE+" BOOLEAN NOT NULL, "+
                TRANS_USER_ID+" INTEGER REFERENCES "+TABLE_USER+" ("+USER_ID+"), "+TRANS_CATEGORY_ID+" INTEGER REFERENCES "+TABLE_CATEGORY + " ("+CAT_ID+"))";


        /*String create_transaction = "create table "+TABLE_TRANSACTION+" ("+TRANS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TRANS_VALUE+" INTEGER NOT NULL, "+TRANS_DATE+" DATE NOT NULL, "+
                TRANS_DESCRIPTION+ " VARCHAR(100), "+TRANS_DONE+" BOOLEAN NOT NULL)";*/

    try{
        db.execSQL(create_user);
        db.execSQL(create_category);
        db.execSQL(create_type);
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

    public boolean addUser(String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_EMAIL,email);
        contentValues.put(USER_PASSWORD, password);
        long result = db.insert(TABLE_USER,null ,contentValues);
        if(result == -1)
            return false;
        else
        {
            System.out.println("User added to db\n");
            return true;
        }

    }

    public Cursor openUser(String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER,null," email = ?", new String[]{email}, null, null, null);
        if(cursor.getCount()<1){ // UserName Not Exist

            cursor.close();
            return null;
        }
        cursor.moveToFirst();

            if(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)).equals(password)){
                return cursor;
            }
        cursor.close();
        return null;

    }

    public int addTransaction(int value, String date, String description, int categoryID, boolean done){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANS_DESCRIPTION, description);
        contentValues.put(TRANS_VALUE, value);
        contentValues.put(TRANS_DATE, date);
        contentValues.put(TRANS_CATEGORY_ID, categoryID);
        contentValues.put(TRANS_DONE, done);
        long result = db.insert(TABLE_TRANSACTION,null, contentValues);
        if(result == -1)
            return -1;
        else
        {
            System.out.println("Transaction added successfully\n");
            return (int)result;
        }


    }

    public int addType(String title){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TYPE_NAME, title);
        long result = db.insert(TABLE_TYPE,null, contentValues);
        if(result == -1)
            return -1;
        else
        {
            System.out.println("Type added successfully\n");
            return (int) result;
        }

    }

    /*public int getTypeID(String title){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query(TABLE_TYPE, null, " Name = ?", new String[]{title}, null, null, null);
        if(res.moveToFirst()) {
            return Integer.parseInt(res.getString(res.getColumnIndex(DatabaseHelper.TYPE_NAME)));
        }
        else{
            res.close();
            return -1;
        }

    }*/

    public int addCategory(String title, int typeID){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAT_TITLE, title);
        contentValues.put(CAT_TYPE_ID, typeID);
        long result = db.insert(TABLE_CATEGORY,null, contentValues);
        if(result == -1)
            return -1;
        else
        {
            System.out.println("Category added successfully\n");
            return (int)result;
        }

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_TRANSACTION,null);
        if(res == null)
            System.out.println("Error getting data\n");
        return res;
    }


    public Cursor getType(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_TYPE,null," _id = ?", new String[]{id}, null, null, null);
        //cursor.getString(cursor.getColumnIndex(DatabaseHelper.TYPE_ID));
        if(cursor.getCount()<1){
            cursor.close();
            return null;
        }
        if(cursor.moveToFirst()){
            return cursor;
        }
        cursor.close();
        return null;

    }

    public Cursor getTransaction(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_TRANSACTION,null," _id = ?", new String[]{id}, null, null, null);
        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_ID));
        if(cursor.getCount()<1){ // Transaction Not Exist

            cursor.close();
            return null;
        }
        if(cursor.moveToFirst()) {

            return cursor;
        }
        cursor.close();
        return null;

    }

    public Cursor getTransactionsBetween(Date d1, Date d2){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_TRANSACTION+" WHERE Date BETWEEN '"+ df1.format(d1)+"' AND '"+df1.format(d2)+"' ORDER BY Date", null);

        Log.d("test: ", df1.format(d1));
        if(cursor.getCount()<1){ // Transaction Not Exist

            cursor.close();
            return null;
        }
        if(cursor.moveToFirst()) {

            return cursor;
        }
        cursor.close();
        return null;

    }

    /*public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }*/

    /*public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }*/
}