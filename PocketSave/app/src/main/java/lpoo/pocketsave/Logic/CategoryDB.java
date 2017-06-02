package lpoo.pocketsave.Logic;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;




public class CategoryDB implements CRUDDB<Category> {


    private static final String TAG = "UserDB";

    private DatabaseHelper dbH = DatabaseHelper.getInstance();


    /**
     * Add a new Category to the dataBase
     * @param newCategory Category instance that should be to add
     * @return Returns true if category was added and false if not
     */
    public boolean add(Category newCategory){


        SQLiteDatabase db = dbH.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CAT_USER_ID, dbH.getUserID());
        contentValues.put(DatabaseHelper.CAT_TITLE, newCategory.getTitle());
        contentValues.put(DatabaseHelper.CAT_TYPE_ID, Long.toString(newCategory.getTypeID()));
        contentValues.put(DatabaseHelper.CAT_MAIN, newCategory.isMainMenu());
        long result = db.insert(DatabaseHelper.TABLE_CATEGORY,null, contentValues);
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
    public boolean update(Category toUpdate){
        SQLiteDatabase db = dbH.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CAT_TITLE, toUpdate.getTitle());
        contentValues.put(DatabaseHelper.CAT_TYPE_ID, toUpdate.getTypeID());
        contentValues.put(DatabaseHelper.CAT_MAIN, toUpdate.isMainMenu());
        int result =  db.update(DatabaseHelper.TABLE_CATEGORY, contentValues, DatabaseHelper.CAT_ID+"=?",new String[] { Long.toString(toUpdate.getID()) });
        return result>0;

    }



    /**
     * Delete the category with id equals to the param
     * @param id id of the category that should be deleted
     * @return Returns true if the category was deleted and false if not
     */
    public boolean delete(String id){
        SQLiteDatabase db = dbH.getWritableDatabase();
        return db.delete(DatabaseHelper.TABLE_CATEGORY, DatabaseHelper.CAT_ID+"=?",new String[]{id})>0;

    }


    /**
     * Get a instance of the category
     * @param name name of the category for which the instance should be returned
     * @return Returns and instance of the category. Returns null if was not possible to get data.
     */
    public Cursor getCategory(String name, String type){
        SQLiteDatabase db = dbH.getReadableDatabase();
        Cursor cursor;
        if(name == null){
            cursor = db.rawQuery("SELECT C."+DatabaseHelper.CAT_ID+", C."+DatabaseHelper.CAT_TITLE+", C."+DatabaseHelper.CAT_MAIN+", C."+DatabaseHelper.CAT_TYPE_ID+", C."+DatabaseHelper.CAT_USER_ID+" FROM "+
                    DatabaseHelper.TABLE_CATEGORY+" C, "+DatabaseHelper.TABLE_TYPE+" P WHERE C."+DatabaseHelper.CAT_TYPE_ID+" = P."+DatabaseHelper.TYPE_ID+" AND P."+DatabaseHelper.TYPE_NAME+" = '"+
                    type+ "' AND C."+DatabaseHelper.CAT_USER_ID+" = '"+dbH.getUserID()+ "'", null );
        }else{
            cursor = db.query(DatabaseHelper.TABLE_CATEGORY, null, DatabaseHelper.CAT_USER_ID + "=? AND "+DatabaseHelper.CAT_TITLE+"=?", new String[]{dbH.getUserID(), name}, null, null, null);
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
        SQLiteDatabase db = dbH.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT C."+DatabaseHelper.CAT_ID+", C."+DatabaseHelper.CAT_TITLE+", C."+DatabaseHelper.CAT_MAIN+", C."+DatabaseHelper.CAT_TYPE_ID+", C."+DatabaseHelper.CAT_USER_ID+" FROM "+
                DatabaseHelper.TABLE_CATEGORY+" C, "+DatabaseHelper.TABLE_TYPE+" P WHERE C."+DatabaseHelper.CAT_TYPE_ID+" = P."+DatabaseHelper.TYPE_ID+" AND P."+DatabaseHelper.TYPE_NAME+" = '"+
                type+ "' AND C."+DatabaseHelper.CAT_USER_ID+" = '"+dbH.getUserID()+ "' AND C."+DatabaseHelper.CAT_MAIN+" = '"+((main) ? 1 : 0)+"'", null );


        if(cursor == null || cursor.getCount()<1) {
            cursor.close();
            return null;
        }
        else
            return cursor;
    }
}
