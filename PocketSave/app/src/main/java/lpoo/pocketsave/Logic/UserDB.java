package lpoo.pocketsave.Logic;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



public class UserDB {

    private static final String TAG = "UserDB";

    private DatabaseHelper dbH = DatabaseHelper.getInstance();

    //USER FUNCTIONS

    /**
     * Add a new User to the db
     * @param email new User email
     * @param password new User password
     * @return Returns true if the user was added and false if not
     */
    public boolean addUser(String email, String password, String since) {

        SQLiteDatabase db = dbH.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_EMAIL,email);
        contentValues.put(DatabaseHelper.USER_PASSWORD, password);
        contentValues.put(DatabaseHelper.USER_TOTALSAVED, 0);
        contentValues.put(DatabaseHelper.USER_SINCE, since);
        long result = db.insert(DatabaseHelper.TABLE_USER,null ,contentValues);
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
     *
     * @return Returns a cursor with the current User's info
     */
    public Cursor getUser(){

        SQLiteDatabase db = dbH.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_USER, null, DatabaseHelper.USER_ID+"=?", new String[]{dbH.getUserID()}, null, null, null);
        if(cursor == null || cursor.getCount()<1)
            return null;
        else
            return cursor;

    }


    /**
     * Get the new user from the database
     * @param email user email
     * @param password user password
     * @return Return a new instance of user
     */
    public boolean openUser(String email, String password){

        SQLiteDatabase db = dbH.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_USER, new String[]{DatabaseHelper.USER_ID}, DatabaseHelper.USER_EMAIL + "=? AND "+DatabaseHelper.USER_PASSWORD+ "=?", new String[]{email, password}, null, null, null);
        if(cursor == null || cursor.getCount()<1)
            return false;
        if(cursor.moveToFirst()) {

            dbH.setUserID(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.USER_ID)));

            cursor.close();
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
    public boolean updateUser(String email, String password, Double totalSaved){

        SQLiteDatabase db = dbH.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_EMAIL,email);
        contentValues.put(DatabaseHelper.USER_PASSWORD, password);
        contentValues.put(DatabaseHelper.USER_TOTALSAVED, totalSaved);
        if(db.update(DatabaseHelper.TABLE_USER, contentValues, DatabaseHelper.USER_ID+"= ?",new String[] { dbH.getUserID()})>0) {
            return openUser(email, password);
        }
        else
            return false;

    }

    /**
     * Delete the current user from the db
     * @return Returns true if it was deleted and false if not
     */
    public boolean delete(){
        SQLiteDatabase db = dbH.getWritableDatabase();
        return db.delete(DatabaseHelper.TABLE_USER, DatabaseHelper.USER_ID+"=?",new String[]{dbH.getUserID()})>0;
    }


    /**
     * Delete all users from db
     */
    public void deleteAllUsers() {
        SQLiteDatabase db = dbH.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_USER, null, null);
        db.close();
    }


}
