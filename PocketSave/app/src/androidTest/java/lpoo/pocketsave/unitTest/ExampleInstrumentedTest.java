package lpoo.pocketsave.unitTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.ArrayList;
import java.util.HashMap;

import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.Logic.DatabaseHelper;
import lpoo.pocketsave.Logic.Suggestions;
import lpoo.pocketsave.Logic.Transaction;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private Context appContext;

    @Before
    public void cleanDB(){
        //DatabaseHelper.
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("lpoo.pocketsave", appContext.getPackageName());
    }

    @Test
    public void testSignUp(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(false, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "syfi", "2001-01-01"));
        assertEquals(false, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));

    }

    @Test
    public void testSignIn(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola2@ola.pt", "1234", "2001-01-01"));
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Open", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals("ola@ola.pt", DataManager.getInstance().getUser().getEmail());



    }


    @Test
    public void updateUser(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Update","olaa@ola.pt", "1234", "2001-01-01"));
        assertEquals("olaa@ola.pt", DataManager.getInstance().getUser().getEmail());
    }


    @Test
    public void deleteUser(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola2@ola.pt", "1234", "2001-01-01"));
        assertEquals(true, DataManager.getInstance().deleteElements("User", null));
    }

    @Test
    public void testAddType(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(-1, DataManager.getInstance().addGetType("Add", "income"));
    }

    @Test
    public void testGetType(){


        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(2, DataManager.getInstance().addGetType("Add", "expenses"));
        assertEquals(2, DataManager.getInstance().addGetType("Get", "expenses"));

    }


   @Test
    public void testAddCategory(){
       try {
           useAppContext();
       } catch (Exception e) {
           e.printStackTrace();
       }
       appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
       DataManager.getInstance().startDB(appContext);
       assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
       assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "food", "income", true, 1));


    }

    @Test
    public void getCategory(){
        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "food", "income", true, 1));
        assertEquals("food", DataManager.getInstance().getCategory("food", true, "income").get(0).getTitle());
    }


    @Test
    public void getMainCategories(){
        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "food", "income", true, 1));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "salary", "income", false, 2));
        assertEquals("food", DataManager.getInstance().getCategory("mainMenuCategories", true, "income").get(0).getTitle());
    }

    @Test
    public void updateCategory(){
        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "food", "income", true, 1));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Update", "food", "income", false, 2));


    }

    @Test
    public void deleteCategory(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "food", "income", true, 1));
        assertEquals(true, DataManager.getInstance().deleteElements("Category", "1"));
    }



    @Test
    public void addTransactions(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "food", "income", true, 1));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, "1997-05-01", "teste", 1, true, null, true));

    }

    @Test
    public void getTypeTransaction(){


        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "food", "income", true, 1));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, "1997-02-01", "teste", 1, true, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, "1997-05-01", "teste", 1, true, null, true));
        HashMap<Transaction, ArrayList<Integer>> b =  DataManager.getInstance().getTypeTransaction("income");
        for(HashMap.Entry<Transaction, ArrayList<Integer> > it : b.entrySet()){
            ArrayList<Integer> a = it.getValue();
            assertEquals(2, a.size());
        }

    }


    @Test
    public void updateTransaction(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "food", "income", true, 1));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, "1997-05-01", "teste", 1, true, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Update", 1, 12.0, "1997-12-02", "teste", 1, true, null, false));
        assertEquals("1997-12-02", DataManager.getInstance().getTransactionsBetweenDates("Category", "food", "1997-04-27", "1997-12-15",true).get(0).getDate());
        Double a = 12.0;
        assertEquals(a, DataManager.getInstance().getTotalSpentValues("Category", null,"1997-04-27", "1997-12-21",true).get("food"));
    }

    @Test
    public void deleteTransaction(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "food", "income", true, 1));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, "1997-05-01", "teste", 1, true, null, true));
        assertEquals(true, DataManager.getInstance().deleteElements("Transaction", "1"));
        assertEquals(false, DataManager.getInstance().deleteElements("Transaction", "1"));

    }



    /*@Test
    public void testAddChangeDeleteTransaction(){
        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenChangeUser("Add", "ola@ola.pt", "1234"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(true, DataManager.getInstance().addChangeCategory("Add",-1,"carro","income",false));
        assertEquals(true, DataManager.getInstance().addChangeTransaction("Add", -1, 10.2, "1997-12-21", "test", 1, false));
        assertEquals("1997-12-21", DataManager.getInstance().getTransactions(null, null, null).get(0).getDate());
        assertEquals(true, DataManager.getInstance().addChangeTransaction("Update", 1, 21, "1997-12-21", "mudar", 1, true));
        Transaction a = DataManager.getInstance().getTransactions("carro", "1997-12-20", "1997-12-21").get(0);
        assertEquals(true, a.getDone());
        assertEquals(true, DataManager.getInstance().DeleteElements("Transaction", a.getID()));

    }

    /*@Test
    public void testgetDBTypeName(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DatabaseSingleton.getInstance().createDB(appContext);
        assertEquals(true, User.getInstance().signup("ola@ola.pt", "1234"));
        Cursor cursor = DatabaseSingleton.getInstance().getDB().getType(Integer.toString(1));
        assertEquals("income", cursor.getString(cursor.getColumnIndex(TYPE_NAME)));

    }

    /*@Test
    public void testgetTransBetweenDates(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseSingleton.getInstance().createDB(appContext);
        assertEquals(true, User.getInstance().addType("income"));
        assertEquals(true, User.getInstance().addCategory("carro", "income"));
        //add to category transaction vector and db
        Transaction t1 = User.getInstance().getCategory("carro").addTransaction(10, "1997/12/21", "ola",true);
        assertNotNull(User.getInstance().getCategory("carro").addTransaction(11, "1997/12/24", "ola2",true));
        //only add db to test get db values between dates
        DatabaseSingleton.getInstance().getDB().addTransaction(12, "1997/02/17", "JulietaQueChunga", 0, true);
        DatabaseSingleton.getInstance().getDB().addTransaction(13, "1997/11/02", "baldaia", 1, true);
        Map<Category,TreeSet<Transaction>> res;
        res = User.getInstance().getAllTransactionsBetween("1997/12/21", "1997/12/23");
        assertEquals(1, res.get(User.getInstance().getCategory("carro")).size());
        res = User.getInstance().getAllTransactionsBetween("1995/05/10", "1997/12/24");
        assertEquals(3, res.get(User.getInstance().getCategory("carro")).size());
        res = User.getInstance().getAllTransactionsBetween("1997/01/01", "1997/11/02");
        assertEquals(1, res.get(User.getInstance().getCategory("carro")).size());


    }

*/



   @Test
    public void getTypeBetweenDates(){
       try {
           useAppContext();
       } catch (Exception e) {
           e.printStackTrace();
       }
       appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
       DataManager.getInstance().startDB(appContext);
       assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
       assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
       assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add",  "ordenado", "income",false, 1));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "carro", "expense", true, 2));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "compras", "expense", true, 3));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, "2017-02-23", "teste", 2, false, null, true));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-25", "teste", 2, true, null, false));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-22", "teste", 3, true, null, true));
       assertEquals("2017-02-22", DataManager.getInstance().getTransactionsBetweenDates("Type", "expense", "2017-02-01", "2017-02-31", true).get(0).getDate());
   }

   @Test
    public void getCatBetweenDates(){

       try {
           useAppContext();
       } catch (Exception e) {
           e.printStackTrace();
       }
       appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
       DataManager.getInstance().startDB(appContext);
       assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
       assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
       assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "ordenado", "income",false, 1));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "carro", "expense", true, 2));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "compras", "expense", true,3));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, "2017-02-23", "teste", 2, false, null, true));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-25", "teste", 2, true, null, false));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-22", "teste", 3, true, null, false));
       assertEquals("2017-02-25", DataManager.getInstance().getTransactionsBetweenDates("Category", "carro", "2017-02-01", "2017-02-28", true).get(0).getDate());

   }

   @Test
    public void getCatTotalSpent(){

       try {
           useAppContext();
       } catch (Exception e) {
           e.printStackTrace();
       }
       appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
       DataManager.getInstance().startDB(appContext);
       assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
       assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
       assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "carro", "expense", true, 1));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "compras", "expense", true, 2));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, "2017-02-23", "teste", 2, false, null, true));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-25", "teste", 2, true, null, true));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-22", "teste", 2, true, null, false));
       Double v = 204.0;
       assertEquals(v, DataManager.getInstance().getTotalSpentValues("Category", null, "2017-02-01", "2017-02-28", true).get("compras"));

   }

    @Test
    public void getTypeTotalSpent(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "carro", "expense", true, 1));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "compras", "expense", true, 2));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, "2017-02-23", "teste", 2, false, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-25", "teste", 2, true, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-22", "teste", 2, false, null, false));
        Double v = 112.0;
        assertEquals(v, DataManager.getInstance().getTotalSpentValues("Type", "expense", "2017-02-01", "2017-02-28", false).get("expense"));

    }

    @Test
    public void onlimitCategory(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "carro", "expense", true, 1));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "compras", "expense", true, 2));
        Suggestions sug = new Suggestions();
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, sug.getInitialDate(true, "current"), "expected", 1, false, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 7.0, sug.getInitialDate(true, "current"), "expected", 2, false, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, sug.getInitialDate(true, "currentDate"), "expected", 1, true, null, true));
        assertEquals("carro", sug.onlimitCategory().get(0));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 2.0, sug.getInitialDate(true, "currentDate"), "expected", 2, true, null, true));
        assertEquals(2, sug.onlimitCategory().size());

    }

    @Test
    public void cashLimit(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "carro", "expense", true, 1));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "compras", "expense", true, 2));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "restauracao", "expense", true, 3));
        Suggestions sug = new Suggestions();
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 500.0, sug.getInitialDate(true, "current"), "expected", 1, false, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1,35.0, sug.getInitialDate(true, "current"), "expected", 2, false, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 50.0, sug.getInitialDate(true, "current"), "expected", 3, false, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 2.0, sug.getInitialDate(true, "currentDate"), "expected", 1, true, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 250.0, sug.getInitialDate(true, "currentDate"), "expected", 1, true, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 1.0, sug.getInitialDate(true, "currentDate"), "expected", 1, true, null, false));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 7.0, sug.getInitialDate(true, "currentDate"), "expected", 2, true, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, sug.getInitialDate(true, "currentDate"), "expected", 2, true, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 5.0, sug.getInitialDate(true, "currentDate"), "expected", 2, true, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 15.0, sug.getInitialDate(true, "currentDate"), "expected", 3, true, null, false));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 30.0, sug.getInitialDate(true, "currentDate"), "expected", 3, true, null, false));

        assertEquals("compras", sug.limitCashMethodCategory().get(0));

    }


    @Test
    public void lastmonthCompare(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        DataManager.getInstance().startDB(appContext);
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
        assertEquals(3, DataManager.getInstance().addGetType("Add", "Fixed expense"));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "carro", "Fixed Expense", true, 1));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "compras", "Fixed Expense", true, 2));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "restauracao", "Fixed Expense", true, 3));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", "ordenado", "Income", true, 4));
        Suggestions sug = new Suggestions();
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1,500.0, sug.getInitialDate(true, "last"), "expected", 1, false, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1,35.0, sug.getInitialDate(true, "last"), "expected", 2, false, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1,50.0, sug.getInitialDate(true, "last"), "expected", 3, false, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1,25.0, sug.getInitialDate(true, "last"), "expected", 4, true, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1,450.0, sug.getInitialDate(false, "last"), "expected", 1, true, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1,10.0, sug.getInitialDate(false, "last"), "expected", 2, true, null, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1,26.0, sug.getInitialDate(false, "last"), "expected", 2, true, null, true));
        ArrayList<String> aux = sug.compareCatValuesBefore();
        assertEquals("compras", aux.get(0));

    }

    @Test
    public void getCatSuggestedValues(){


    }




}
