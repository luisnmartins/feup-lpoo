package lpoo.pocketsave;

import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import lpoo.pocketsave.Logic.DatabaseHelper;
import lpoo.pocketsave.Logic.DatabaseSingleton;
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


}
