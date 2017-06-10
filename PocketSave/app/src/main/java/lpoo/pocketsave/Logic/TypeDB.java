package lpoo.pocketsave.Logic;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



public class TypeDB {

    private static final String TAG = "UserDB";

    private DatabaseHelper dbH = DatabaseHelper.getInstance();


    /**
     * Add a new type
     * @param title name of the type
     * @return Returns the ID of the type added
     */
    public long addType(String title){

        Log.d(TAG, "USER: "+dbH.getUserID());
        SQLiteDatabase db = dbH.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TYPE_NAME, title);
        Log.d(TAG, "Type added successfully\n");
        return db.insert(DatabaseHelper.TABLE_TYPE,null, contentValues);

    }


    /**
     * get the type which name is the param
     * @param title Name of the type to get
     * @return Returns the requested type id
     */
    public long getTypeID(String title){
        SQLiteDatabase db = dbH.getWritableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_TYPE, new String[]{DatabaseHelper.TYPE_ID}, DatabaseHelper.TYPE_NAME + "=?", new String[]{title}, null, null, null);
        if(cursor == null || cursor.getCount()<1)
            return -1;
        if(cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TYPE_ID));
        }
        cursor.close();
        return -1;

    }

    /**
     * Get a type title with the ID
     * @param id ID of the type to get
     * @return Returns the asked type name
     */
    public String getTypeTitle(long id){
        SQLiteDatabase db = dbH.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_TYPE, new String[]{DatabaseHelper.TYPE_NAME}, DatabaseHelper.TYPE_ID + "=?", new String[]{Long.toString(id)}, null, null, null);
        if(cursor == null || cursor.getCount()<1)
            return null;
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(DatabaseHelper.TYPE_NAME));
        }
        cursor.close();
        return null;
    }
}
