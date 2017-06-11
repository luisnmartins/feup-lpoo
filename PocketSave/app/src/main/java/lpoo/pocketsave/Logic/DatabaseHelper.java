package lpoo.pocketsave.Logic;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;



public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "Database";

    private static final String DATABASE_NAME = "PocketSave.db";

    //Tables
    public static final String TABLE_USER = "user_table";
    public static final String TABLE_TRANSACTION = "transaction_table";
    public static final String TABLE_CATEGORY = "category_table";
    public static final String TABLE_TYPE = "type_table";

    //User Table Columns
    public static final String USER_ID = "_id";
    private static final String USER_NAME = "Username";
    public static final String USER_EMAIL = "Email";
    public static final String USER_PASSWORD = "Password";
    public static final String USER_TOTALSAVED = "TotalSaved";
    public static final String USER_SINCE = "Since";

    //Transaction Table Columns
    public static final String TRANS_ID = "_id";
    public static final String TRANS_DESCRIPTION = "Description";
    public static final String TRANS_VALUE = "Value";
    public static final String TRANS_DATE = "Date";
    public static final String TRANS_DONE = "Done";
    public static final String TRANS_CATEGORY_ID = "Category_ID";
    public static final String TRANS_IMAGE = "Image";
    public static final String TRANS_CASH = "Cash";


    //Category Table Columns
    public static final String CAT_ID = "_id";
    public static final String CAT_TITLE = "Title";
    public static final String CAT_TYPE_ID = "Type_ID";
    public static final String CAT_USER_ID = "User_ID";
    public static  final String CAT_MAIN = "main_menu";
    public static final String CAT_COLOR = "color";

    //Type Table Columns
    public static final  String TYPE_ID = "_id";
    public static final String TYPE_NAME = "Name";

    private long userID;


    /**
     * Creates a new database instance
     * @param context Application Context
     */
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    static private DatabaseHelper instance = null;

    /**
     * Initialize database with the program context
     * @param context Application context
     */
    static public void startDB(Context context){
        if (instance == null){
            instance = new DatabaseHelper(context);
        }
    }

    /**
     * Singleton instance
     * @return Returns an instance of the DatabaseHelper
     */
    static public DatabaseHelper getInstance(){
            if(instance == null)
                throw new NullPointerException();
            return instance;
    }

    /**
     * Creates the database tables
     * @param db database instance
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_user = "create table " + TABLE_USER + " ("+USER_ID+" INTEGER PRIMARY KEY, "+
                USER_NAME+" STRING UNIQUE, "+ USER_EMAIL+" STRING UNIQUE, "+USER_SINCE+" STRING NOT NULL, "+USER_PASSWORD+" STRING NOT NULL, "+
                USER_TOTALSAVED+" REAL)";
        String create_category = "create table "+ TABLE_CATEGORY+" ("+CAT_ID+" INTEGER PRIMARY KEY, "+
                CAT_TITLE+" STRING, "+CAT_MAIN+" BOOLEAN NOT NULL, "+CAT_COLOR+" INTEGER NOT NULL, "+CAT_TYPE_ID+" INTEGER REFERENCES "+TABLE_TYPE+" ("+TYPE_ID+"), "+CAT_USER_ID+" INTEGER REFERENCES "+TABLE_USER+" ("+USER_ID+") ON DELETE CASCADE);";
        String create_type = "create table "+ TABLE_TYPE + " ("+TYPE_ID+" INTEGER PRIMARY KEY, "+TYPE_NAME+" STRING NOT NULL UNIQUE)";
        String create_transaction = "create table "+TABLE_TRANSACTION+" ("+TRANS_ID+" INTEGER PRIMARY KEY, "+
                TRANS_VALUE+" REAL NOT NULL, "+TRANS_DATE+" STRING NOT NULL, "+
                TRANS_DESCRIPTION+ " STRING, "+TRANS_DONE+" BOOLEAN NOT NULL, "+TRANS_IMAGE+" STRING, "+TRANS_CASH+" BOOLEAN NOT NULL, " +TRANS_CATEGORY_ID+" INTEGER REFERENCES "+TABLE_CATEGORY + " ("+CAT_ID+") ON DELETE CASCADE);";

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

    /**
     * Upgrades DB
     * @param db db instance
     * @param oldVersion oldVersion number
     * @param newVersion NewVersion number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TRANSACTION);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TYPE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CATEGORY);
        onCreate(db);
    }


    /**
     * Set user id variable to be used in queries
     * @param id User id
     */
    public void setUserID(long id){
        this.userID = id;
    }

    /**
     * Get the current user ID
     * @return Returns the user id
     */
    public String getUserID(){
        return Long.toString(userID);
    }



}