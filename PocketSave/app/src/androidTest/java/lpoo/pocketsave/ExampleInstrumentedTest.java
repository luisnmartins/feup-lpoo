package lpoo.pocketsave;

import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;
import java.util.TreeSet;

import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.Logic.DatabaseHelper;
import lpoo.pocketsave.Logic.DatabaseSingleton;
import lpoo.pocketsave.Logic.PocketSave;
import lpoo.pocketsave.Logic.Transaction;
import lpoo.pocketsave.Logic.User;
import lpoo.pocketsave.View.MainActivity;

import static lpoo.pocketsave.Logic.DatabaseHelper.TYPE_NAME;
import static org.junit.Assert.*;

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
        PocketSave.getInstance().createDB(appContext);
        assertEquals(true, PocketSave.getInstance().signup("ola@ola.pt", "1234"));
        assertEquals(false, PocketSave.getInstance().signup("ola@ola.pt", "1234"));

    }

    @Test
    public  void testSignIn(){
        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PocketSave.getInstance().createDB(appContext);
        assertEquals(true, PocketSave.getInstance().signup("ola@ola.pt", "1234"));
        assertEquals(true, PocketSave.getInstance().signin("ola@ola.pt", "1234"));
        assertEquals(false, PocketSave.getInstance().signin("ola@ola.pt", "123") );
    }

    @Test
    public void testAddType(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PocketSave.getInstance().createDB(appContext);
        assertEquals(true, PocketSave.getInstance().signup("ola@ola.pt", "1234"));
        assertEquals(true, PocketSave.getInstance().addType("income"));
        assertEquals(true, PocketSave.getInstance().addType("fixed expense"));
        assertEquals(false, PocketSave.getInstance().addType("income"));
    }

   @Test
    public void testAddCategory(){
        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PocketSave.getInstance().createDB(appContext);
        assertEquals(true, PocketSave.getInstance().signup("ola@ola.pt", "1234"));
        assertEquals(true, PocketSave.getInstance().addType("income"));
        assertEquals(true, PocketSave.getInstance().addCategory("carro", "income"));
        assertEquals(true, PocketSave.getInstance().addCategory("compras", "income"));
        assertEquals(false, PocketSave.getInstance().addCategory("compras", "income"));
        assertEquals(false, PocketSave.getInstance().addCategory("compras", "expenses"));


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

    @Test
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

}
