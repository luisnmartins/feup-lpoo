package lpoo.pocketsave.Logic;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class TransactionDB implements CRUDDB<Transaction> {

    private static final String TAG = "UserDB";

    private DatabaseHelper dbH = DatabaseHelper.getInstance();


    /**
     * Add a new transaction to the db
     * @param newTransaction New transaction to be added
     * @return Returns true if the new transaction was added, and false if not
     */
    public boolean add(Transaction newTransaction){

        SQLiteDatabase db = dbH.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TRANS_DESCRIPTION, newTransaction.getDescription());
        contentValues.put(DatabaseHelper.TRANS_VALUE, newTransaction.getValue());
        contentValues.put(DatabaseHelper.TRANS_DATE, newTransaction.getDate());
        contentValues.put(DatabaseHelper.TRANS_CATEGORY_ID, newTransaction.getCatID());
        contentValues.put(DatabaseHelper.TRANS_DONE, newTransaction.getDone());
        contentValues.put(DatabaseHelper.TRANS_IMAGE, newTransaction.getImage());
        contentValues.put(DatabaseHelper.TRANS_CASH, newTransaction.isCashMethod() );
        long result = db.insert(DatabaseHelper.TABLE_TRANSACTION,null, contentValues);
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
    public boolean update(Transaction toUpdate){
        SQLiteDatabase db = dbH.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d(TAG, "ID toUdate: "+toUpdate.getID());
        Log.d(TAG, "ID CAT: "+toUpdate.getCatID());
        contentValues.put(DatabaseHelper.TRANS_ID, toUpdate.getID());
        contentValues.put(DatabaseHelper.TRANS_VALUE, toUpdate.getValue());
        contentValues.put(DatabaseHelper.TRANS_DATE, toUpdate.getDate());
        contentValues.put(DatabaseHelper.TRANS_DESCRIPTION, toUpdate.getDescription());
        contentValues.put(DatabaseHelper.TRANS_CATEGORY_ID, toUpdate.getCatID());
        contentValues.put(DatabaseHelper.TRANS_DONE, toUpdate.getDone());
        contentValues.put(DatabaseHelper.TRANS_IMAGE, toUpdate.getImage());
        contentValues.put(DatabaseHelper.TRANS_CASH, toUpdate.isCashMethod());
        return db.update(DatabaseHelper.TABLE_TRANSACTION, contentValues, DatabaseHelper.TRANS_ID+"= ?",new String[] { Long.toString(toUpdate.getID()) })>0;

    }

    /**
     * Delete a transaction from the db
     * @param id Id of the transaction to be deleted
     * @return Returns true if transaction was deleted and false if not
     */
    public boolean delete(String id){
        SQLiteDatabase db = dbH.getWritableDatabase();
        return db.delete(DatabaseHelper.TABLE_TRANSACTION, DatabaseHelper.TRANS_ID+"=?",new String[]{id})>0;
    }





    /**
     * Get the transaction of the given type
     * @param typeID Type ID of the transactions to get
     * @return Returns a cursor with the query result
     */
    public Cursor getTypeTransactions(String typeID){
        SQLiteDatabase db = dbH.getReadableDatabase();
        Cursor cursor;
        Log.d(TAG,"ID: "+ dbH.getUserID());
        String query = "SELECT T."+DatabaseHelper.TRANS_ID+", T."+DatabaseHelper.TRANS_VALUE+", T."+DatabaseHelper.TRANS_DATE+", T."+DatabaseHelper.TRANS_DESCRIPTION+", T."+DatabaseHelper.TRANS_CATEGORY_ID+", T."+
                DatabaseHelper.TRANS_DONE+", T."+DatabaseHelper.TRANS_IMAGE+", T."+DatabaseHelper.TRANS_CASH+" FROM "+DatabaseHelper.TABLE_TRANSACTION+" T, "+DatabaseHelper.TABLE_CATEGORY+" C WHERE C."+DatabaseHelper.CAT_TYPE_ID+" = "+typeID+" AND T."+DatabaseHelper.TRANS_CATEGORY_ID+" = C."+DatabaseHelper.CAT_ID +" AND C."+DatabaseHelper.CAT_USER_ID+" = "+dbH.getUserID()+" ORDER BY T.Date";
        cursor = db.rawQuery(query, null);

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

        SQLiteDatabase db = dbH.getReadableDatabase();
        String query = "SELECT T."+DatabaseHelper.TRANS_ID+", T."+DatabaseHelper.TRANS_VALUE+", T."+DatabaseHelper.TRANS_DATE+", T."+DatabaseHelper.TRANS_DESCRIPTION+", T."+DatabaseHelper.TRANS_CATEGORY_ID+", T."+
                DatabaseHelper.TRANS_DONE+", T."+DatabaseHelper.TRANS_IMAGE+", T."+DatabaseHelper.TRANS_CASH+" FROM "+DatabaseHelper.TABLE_TRANSACTION+" T, "+DatabaseHelper.TABLE_CATEGORY+
                " C WHERE T.Date BETWEEN '"+ d1+"' AND '"+d2+"' AND C."
                + DatabaseHelper.CAT_TITLE+ " = '"+catTitle+"' AND C."+DatabaseHelper.CAT_ID+" = T."+DatabaseHelper.TRANS_CATEGORY_ID+" AND T."+DatabaseHelper.TRANS_DONE+" = "+((done) ? 1 : 0)+" AND C."+DatabaseHelper.CAT_USER_ID+" = "+dbH.getUserID()+" ORDER BY Date";
        Cursor cursor  = db.rawQuery(query, null);


        if(cursor == null || cursor.getCount()<1)
            return null;
        else
            return cursor;

    }



    /**
     * Get all transactions between d1 and d2 from typeTitle Type
     * @param typeID type ID of the transactions
     * @param d1 initial date
     * @param d2 final date
     * @param done variable to define if it is to get transactions that was already done or the ones that will happen in the future
     * @return Returns a cursor to the query result
     */
    public Cursor getTypeTransactionsBetweenDates( String typeID, String d1, String d2, boolean done){

        SQLiteDatabase db = dbH.getReadableDatabase();
        String query = "SELECT T."+DatabaseHelper.TRANS_ID+", T."+DatabaseHelper.TRANS_VALUE+", T."+DatabaseHelper.TRANS_DATE+", T."+DatabaseHelper.TRANS_DESCRIPTION+", T."+DatabaseHelper.TRANS_CATEGORY_ID+", T."+
                DatabaseHelper.TRANS_DONE+", T."+DatabaseHelper.TRANS_IMAGE+", T."+DatabaseHelper.TRANS_CASH+" FROM "+DatabaseHelper.TABLE_TRANSACTION+" T, "+DatabaseHelper.TABLE_CATEGORY+" C WHERE T.Date BETWEEN '"+
                d1+"' AND '"+d2+"' AND C."+DatabaseHelper.CAT_TYPE_ID+" = "+typeID+ " AND C."+DatabaseHelper.CAT_USER_ID+" = "+dbH.getUserID()+" AND T."+DatabaseHelper.TRANS_DONE+" = "+((done) ? 1 : 0)+" ORDER BY T.Date";

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

        SQLiteDatabase db = dbH.getReadableDatabase();
        Cursor cursor;
        if(catTitle == null){

            String query = "SELECT C."+DatabaseHelper.CAT_TITLE+", SUM(T."+DatabaseHelper.TRANS_VALUE+") FROM "+
                    DatabaseHelper.TABLE_TRANSACTION+" T, "+DatabaseHelper.TABLE_CATEGORY+" C WHERE "+
                    "T."+DatabaseHelper.TRANS_CATEGORY_ID+ " = C."+DatabaseHelper.CAT_ID+
                    " AND T."+ DatabaseHelper.TRANS_DATE+" BETWEEN '"+ d1+"' AND '"+d2+"' AND T."+DatabaseHelper.TRANS_DONE+" = "+((done) ? 1 : 0)+
                    " GROUP BY C."+DatabaseHelper.CAT_TITLE;
            cursor = db.rawQuery(query, null);
        }
        else {
            String query = "SELECT C." + DatabaseHelper.CAT_TITLE + ", SUM(T." + DatabaseHelper.TRANS_VALUE + ") FROM " +
                    DatabaseHelper.TABLE_TRANSACTION + " T, " + DatabaseHelper.TABLE_CATEGORY + " C WHERE " +
                    "T." + DatabaseHelper.TRANS_CATEGORY_ID + " = C." + DatabaseHelper.CAT_ID +
                    " AND T." + DatabaseHelper.TRANS_DATE + " BETWEEN '" + d1 + "' AND '" + d2 +
                    "' AND C." + DatabaseHelper.CAT_TITLE + " = " + catTitle + " AND T." +DatabaseHelper.TRANS_DONE + " = " + ((done) ? 1 : 0) + " GROUP BY C." + DatabaseHelper.CAT_TITLE;

            cursor = db.rawQuery(query, null);

        }
        if(cursor == null || cursor.getCount()<1){

            cursor.close();
            return null;
        }
        else {
            return cursor;
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

        SQLiteDatabase db = dbH.getReadableDatabase();
        Double value;
        Cursor cursor;
        if(typeTitle == null){

            cursor = db.rawQuery("SELECT P." + DatabaseHelper.TYPE_NAME + ", SUM(T." + DatabaseHelper.TRANS_VALUE + ") FROM " +
                    DatabaseHelper.TABLE_TRANSACTION + " T, " + DatabaseHelper.TABLE_CATEGORY + " C, " + DatabaseHelper.TABLE_TYPE + " P " +
                    "WHERE P." + DatabaseHelper.TYPE_ID + " = C." + DatabaseHelper.CAT_TYPE_ID + " AND T." + DatabaseHelper.TRANS_CATEGORY_ID + " = " +
                    "C." + DatabaseHelper.CAT_ID + " AND T." + DatabaseHelper.TRANS_DATE + " BETWEEN '" + d1 + "' AND '" + d2+"' AND C." + DatabaseHelper.CAT_USER_ID + " = '" + dbH.getUserID() + "' AND T." + DatabaseHelper.TRANS_DONE + " = " + ((done) ? 1 : 0) +
                    " GROUP BY P." + DatabaseHelper.TYPE_NAME, null);

        }else {
            cursor = db.rawQuery("SELECT P." + DatabaseHelper.TYPE_NAME + ", SUM(T." + DatabaseHelper.TRANS_VALUE + ") FROM " +
                    DatabaseHelper.TABLE_TRANSACTION + " T, " + DatabaseHelper.TABLE_CATEGORY + " C, " + DatabaseHelper.TABLE_TYPE + " P " +
                    "WHERE P." + DatabaseHelper.TYPE_ID + " = C." + DatabaseHelper.CAT_TYPE_ID + " AND T." + DatabaseHelper.TRANS_CATEGORY_ID + " = " +
                    "C." + DatabaseHelper.CAT_ID + " AND T." + DatabaseHelper.TRANS_DATE + " BETWEEN '" + d1 + "' AND '" + d2+"' AND P." + DatabaseHelper.TYPE_NAME + " = '" + typeTitle + "' AND C." + DatabaseHelper.CAT_USER_ID + " = '" + dbH.getUserID() + "' AND T." + DatabaseHelper.TRANS_DONE + " = " + ((done) ? 1 : 0) +
                    " GROUP BY P." + DatabaseHelper.TYPE_NAME, null);
        }

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
