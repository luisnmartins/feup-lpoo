package lpoo.pocketsave.Logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    //General DB info
    public static final String DATABASE_NAME = "BALANCE";

    //Tables
    public static final String TABLE_USER = "User";
    public static final String TABLE_TRANSACTION = "Transaction";
    public static final String TABLE_CATEGORY = "Category";
    public static final String TABLE_TYPE = "Type";

    //User Table Columns
    public static final String USER_ID = "ID";
    public static final String USER_EMAIL = "Email";
    public static final String USER_PASSWORD = "Password";
    public static final String USER_TOTALSAVED = "TotalSaved";
    public static final String USER_ID_TRANSACTIONS = "ID_Transactions";

    //Transaction Table Columns
    public static final String TRANS_ID = "ID";
    public static final String TRANS_DESCRIPTION = "Description";
    public static final String TRANS_VALUE = "Value";
    public static final String TRANS_DATE = "Date";
    public static final String TRANS_ID_CATEGORY = "ID_Category";
    public static final String TRANS_DONE = "Done";

    //Category Table Columns
    public static final String CAT_ID = "ID";
    public static final String CAT_TITLE = "Title";
    public static final String CAT_TYPE = "Type";

    //Type Table Columns
    public static final String TYPE_NAME = "Name";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("myTag", "This is my message");

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + USER_ID + " INTEGER PRIMARY KEY, "+
                USER_EMAIL+ " STRING NOT NULL, "+USER_PASSWORD+" STRING NOT NULL, "+
                USER_TOTALSAVED+" INTEGER, "+ USER_ID_TRANSACTIONS+ " INTEGER REFERENCES " +
                TABLE_TRANSACTION+" ("+TRANS_ID+"))";

        String CREATE_TRANSACTION_TABLE = "CREATE TABLE "+TABLE_TRANSACTION + "("+TRANS_ID+" INTEGER PRIMARY KEY, "+
                TRANS_VALUE+" INTEGER NOT NULL, " + TRANS_DESCRIPTION + " STRING, " + TRANS_DATE +" DATE NOT NULL, "+
                TRANS_ID_CATEGORY + " INTEGER REFERENCES "+ TABLE_CATEGORY+" ("+CAT_ID+"), "+TRANS_DONE+" INTEGER NOT NULL)";

        String CREATE_CATEGORY_TABLE = "CREATE TABLE "+TABLE_CATEGORY + "("+CAT_ID+" INTEGER PRIMARY KEY, "+
                CAT_TITLE+" STRING NOT NULL, "+CAT_TYPE+" STRING REFERENCES "+TABLE_TYPE+"("+TYPE_NAME+"))";

        String CREATE_TYPE_TABLE = "CREATE TABLE "+ TABLE_TYPE + "(" +TYPE_NAME+" STRING PRIMARY KEY)";


        db.execSQL(CREATE_TYPE_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_TRANSACTION_TABLE);
        db.execSQL(CREATE_USER_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TRANSACTION);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TYPE);
        onCreate(db);
    }

    public boolean insertData(int value, String type){

        /*SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, value);
        contentValues.put(COL_3, type);
        long insertResult = db.insert(TABLE_NAME, null, contentValues);
        if(insertResult == -1)
            return false;
        else
            return true;*/
        return true;
    }

    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_USER, null);
        return res;
    }



}
