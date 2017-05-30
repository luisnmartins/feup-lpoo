package lpoo.pocketsave.unitTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;
import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.Logic.DatabaseHelper;
import lpoo.pocketsave.Logic.Transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private Context appContext;

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
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", 0));
        assertEquals(false, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "syfi", 0));
        assertEquals(false, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", 0));

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
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", 0));
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola2@ola.pt", "1234", 0));
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Open", "ola@ola.pt", "1234", 0));
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
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", 0));
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Update","olaa@ola.pt", "1234", 0));
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
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", 0));
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola2@ola.pt", "1234", 0));
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
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", 0));
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
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", 0));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(2, DataManager.getInstance().addGetType("Add", "expenses"));
        assertEquals(2, DataManager.getInstance().addGetType("Get", "expenses"));

    }


   /*@Test
    public void testAddCategory(){
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
       assertEquals(true, DataManager.getInstance().addChangeCategory("Add",-1,"compras","income",true));
       assertEquals(false, DataManager.getInstance().addChangeCategory("Add",-1,"carro","income",false));
       Category a = DataManager.getInstance().getCategory("carro", null).get(0);
       assertEquals(true, DataManager.getInstance().addChangeCategory("Update", a.getID(), "bolachas", Long.toString(a.getTypeID()), true));
       ArrayList<Category> mainCategories = DataManager.getInstance().getCategory("mainMenuCategories", true);
       assertEquals(1, mainCategories.get(0).getID());
       assertEquals(2, mainCategories.get(1).getID());
       assertEquals(true, DataManager.getInstance().DeleteElements("Category", a.getID()));

    }

    @Test
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


    @Test
    public void testgetTotalSpentsBetweenDates(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseSingleton.getInstance().createDB(appContext);
        assertEquals(true, User.getInstance().addType("income"));
        assertEquals(true, User.getInstance().addCategory("carro", "income"));
        assertEquals(true, User.getInstance().addCategory("comida", "income"));
        assertNotNull(User.getInstance().getCategory("carro").addTransaction(10, "1997/12/21", "ola",true));
        assertNotNull(User.getInstance().getCategory("carro").addTransaction(11, "1997/12/24", "ola2",true));
        assertNotNull(User.getInstance().getCategory("comida").addTransaction(20, "1997/12/24", "teste",true));
        Category a = User.getInstance().getCategory("carro");
        Double b = User.getInstance().getCategoriesSpents("1997/12/20", "1997/12/25").get(a);
        Double c = 21.0;
        assertEquals(c,b);

    }

    @Test
    public void testAddTransaction(){
        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseSingleton.getInstance().createDB(appContext);
        assertEquals(true, User.getInstance().addType("income"));
        assertEquals(true, User.getInstance().addCategory("carro", "income"));
        assertNotNull(User.getInstance().getCategory("carro").addTransaction(10, "1997/12/21", "ola",true));

    }*/


   @Test
    public void getTypeBetweenDates(){
       try {
           useAppContext();
       } catch (Exception e) {
           e.printStackTrace();
       }
       appContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
       DataManager.getInstance().startDB(appContext);
       assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", 0));
       assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
       assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", -1, "ordenado", "income",false));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", -1, "carro", "expense", true));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", -1, "compras", "expense", true));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, "2017-02-23", "teste", 2, false));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-25", "teste", 2, true));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-22", "teste", 3, true));
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
       assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", 0));
       assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
       assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", -1, "ordenado", "income",false));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", -1, "carro", "expense", true));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", -1, "compras", "expense", true));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, "2017-02-23", "teste", 2, false));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-25", "teste", 2, true));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-22", "teste", 3, true));
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
       assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", 0));
       assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
       assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", -1, "carro", "expense", true));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", -1, "compras", "expense", true));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, "2017-02-23", "teste", 2, false));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-25", "teste", 2, true));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-22", "teste", 2, true));
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
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", 0));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", -1, "carro", "expense", true));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", -1, "compras", "expense", true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 10.0, "2017-02-23", "teste", 2, false));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-25", "teste", 2, true));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", -1, 102.0, "2017-02-22", "teste", 2, false));
        Double v = 112.0;
        assertEquals(v, DataManager.getInstance().getTotalSpentValues("Type", "expense", "2017-02-01", "2017-02-28", false).get("expense"));

    }

}
