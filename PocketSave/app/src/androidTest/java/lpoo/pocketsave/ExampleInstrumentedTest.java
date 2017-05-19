package lpoo.pocketsave;

import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.Console;
import java.util.TreeSet;

import lpoo.pocketsave.Logic.DatabaseHelper;
import lpoo.pocketsave.Logic.DatabaseSingleton;
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
    public void testAddType(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseSingleton.getInstance().createDB(appContext);
        assertEquals(true, User.getInstance().addType("income"));
        assertEquals(false, User.getInstance().addType("income"));
    }

    @Test
    public void testAddCategory(){
        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseSingleton.getInstance().createDB(appContext);
        assertEquals(true, User.getInstance().addType("income"));
        assertEquals(true, User.getInstance().addCategory("ordenado", "income"));
        assertEquals(false, User.getInstance().addCategory("carro", "expense"));

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
        assertEquals(true, User.getInstance().addCategory("ordenado", "income"));
        assertEquals(true, User.getInstance().getCategory("ordenado").addTransaction(10, "1997/05/21", "teste", true));

    }

    @Test
    public void getDBTypeName(){

        try {
            useAppContext();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DatabaseSingleton.getInstance().createDB(appContext);
        assertEquals(true, User.getInstance().addType("income"));
        Cursor cursor = DatabaseSingleton.getInstance().getDB().getType(Integer.toString(1));
        assertEquals("income", cursor.getString(cursor.getColumnIndex(TYPE_NAME)));

    }

    @Test
    public void getTransBetweenDates(){

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
        DatabaseSingleton.getInstance().getDB().addTransaction(13, "1997/11/02", "baldaia", 0, true);
        TreeSet<Transaction> res;
        res = User.getInstance().getAllTransactionsBetween("1997/12/21", "1997/12/23");
        assertEquals(t1.getID(), res.first().getID());
        assertEquals(1, res.size());
        res = User.getInstance().getAllTransactionsBetween("1995/05/10", "1997/12/24");
        assertEquals(4, res.size());
        res = User.getInstance().getAllTransactionsBetween("1997/01/01", "1997/11/02");
        assertEquals(2, res.size());


    }

}
