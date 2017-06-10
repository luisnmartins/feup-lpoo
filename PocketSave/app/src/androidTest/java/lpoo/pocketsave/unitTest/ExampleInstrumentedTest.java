package lpoo.pocketsave.unitTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.HashMap;

import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.Logic.DatabaseHelper;
import lpoo.pocketsave.Logic.Date;
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

    private Context appContext = InstrumentationRegistry.getTargetContext();


    @Test
    public void testSignUp(){


        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(false, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "syfi", "2001-01-01"));
        assertEquals(false, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));

    }

    @Test
    public void testSignIn(){


        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola2@ola.pt", "1234", "2001-01-01"));
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Open", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals("ola@ola.pt", DataManager.getInstance().getUser().getEmail());



    }


    @Test
    public void updateUser(){



        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Update","olaa@ola.pt", "1234", "2001-01-01"));
        assertEquals("olaa@ola.pt", DataManager.getInstance().getUser().getEmail());
    }


    @Test
    public void deleteUser(){



        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola2@ola.pt", "1234", "2001-01-01"));
        assertEquals(true, DataManager.getInstance().deleteElements("User", null));
    }

    @Test
    public void testAddType(){


        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(-1, DataManager.getInstance().addGetType("Add", "income"));
    }

    @Test
    public void testGetType(){


        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(2, DataManager.getInstance().addGetType("Add", "expenses"));
        assertEquals(2, DataManager.getInstance().addGetType("Get", "expenses"));

    }


   @Test
    public void testAddCategory(){

       DataManager.getInstance().startDB(appContext);
       DataManager.getInstance().setContext(appContext);
       DataManager.getInstance().clearDB();
       assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
       assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
       Category newCategory = new Category("food", DataManager.getInstance().addGetType("Get", "income"), true, 1);
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", newCategory));


    }

    @Test
    public void getCategory(){

        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        Category newCategory = new Category("food", DataManager.getInstance().addGetType("Get", "income"), true, 1);
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", newCategory));
        assertEquals("food", DataManager.getInstance().getCategory("food", true, "income").get(0).getTitle());
    }


    @Test
    public void getMainCategories(){

        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        Category newCategory = new Category("food", DataManager.getInstance().addGetType("Get", "income"), true, 1);
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", newCategory));
        Category cat = new Category("salary", DataManager.getInstance().addGetType("Get", "income"), false, 2);
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat));
        assertEquals("food", DataManager.getInstance().getCategory("mainMenuCategories", true, "income").get(0).getTitle());
    }

    @Test
    public void updateCategory(){

        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        Category newCategory = new Category("food", DataManager.getInstance().addGetType("Get", "income"), true, 1);
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", newCategory));
        newCategory.setMainMenu(false);
        newCategory.setColor(2);
        newCategory.setTitle("teste");
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Update", newCategory));


    }

    @Test
    public void deleteCategory(){


        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        Category newCategory = new Category("food", DataManager.getInstance().addGetType("Get", "income"), true, 1);
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", newCategory));
        assertEquals(true, DataManager.getInstance().deleteElements("Category", "1"));
    }



    @Test
    public void addTransactions(){


        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        Category newCategory = new Category("food", DataManager.getInstance().addGetType("Get", "income"), true, 1);
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", newCategory));
        Transaction t = new Transaction(10.0, "1997-05-01", 1, true, true);
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t));

    }

    @Test
    public void getTypeTransaction(){


        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        Category newCategory = new Category("food", DataManager.getInstance().addGetType("Get", "income"), true, 1);
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", newCategory));
        Transaction t = new Transaction(10.0, "1997-02-01", 1, false, true);
        t.setDescription("ordenado");
        Transaction t1 = new Transaction(10.0, "1997-05-01", 1, false, true);
        t1.setDescription("ordenado");
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t1));
        HashMap<Transaction, ArrayList<Integer>> b =  DataManager.getInstance().getTypeTransaction("income", false);
        for(HashMap.Entry<Transaction, ArrayList<Integer> > it : b.entrySet()){
            ArrayList<Integer> a = it.getValue();
            assertEquals(2, a.size());
        }

    }


    @Test
    public void updateTransaction(){


        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        Category newCategory = new Category("food", DataManager.getInstance().addGetType("Get", "income"), true, 1);
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", newCategory));
        Transaction t1 = new Transaction(10.0, "1997-05-01", 1, true, true);
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add",t1));
        t1.setDate("1997-12-02");
        t1.setValue(12.0);
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Update", t1));
        assertEquals("1997-12-02", DataManager.getInstance().getTransactionsBetweenDates("Category", "food", "1997-04-27", "1997-12-15",true).get(0).getDate());
        Double a = 12.0;
        assertEquals(a, DataManager.getInstance().getTotalSpentValues("Category", null,"1997-04-27", "1997-12-21",true).get("food"));
    }

    @Test
    public void deleteTransaction(){


        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        Category newCategory = new Category("food", DataManager.getInstance().addGetType("Get", "income"), true, 1);
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", newCategory));
        Transaction t1 = new Transaction(10.0, "1997-05-01", 1, true, true);
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t1));
        assertEquals(true, DataManager.getInstance().deleteElements("Transaction", "1"));
        assertEquals(false, DataManager.getInstance().deleteElements("Transaction", "1"));

    }





   @Test
    public void getTypeBetweenDates(){


       DataManager.getInstance().startDB(appContext);
       DataManager.getInstance().setContext(appContext);
       DataManager.getInstance().clearDB();
       assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
       assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
       assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
       Category newCategory = new Category("ordenado", DataManager.getInstance().addGetType("Get", "income"), false, 1);
       Category cat = new Category("carro", DataManager.getInstance().addGetType("Get", "expense"), true, 2);
       Category cat2 = new Category("compras", DataManager.getInstance().addGetType("Get", "expense"), true, 3);
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", newCategory));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat2));
       Transaction t1 = new Transaction(10.0, "1997-02-23", 2, false, true);
       Transaction t2 = new Transaction(102.0, "2017-02-25", 2, true, false);
       Transaction t3 = new Transaction(102.0, "2017-02-22", 3, true, true);
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t1));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t2));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add",t3));
       assertEquals("2017-02-22", DataManager.getInstance().getTransactionsBetweenDates("Type", "expense", "2017-02-01", "2017-02-31", true).get(0).getDate());
   }

   @Test
    public void getCatBetweenDates(){


       DataManager.getInstance().startDB(appContext);
       DataManager.getInstance().setContext(appContext);
       DataManager.getInstance().clearDB();
       assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
       assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
       assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
       Category newCategory = new Category("ordenado", DataManager.getInstance().addGetType("Get", "income"), false, 1);
       Category cat = new Category("carro", DataManager.getInstance().addGetType("Get", "expense"), true, 2);
       Category cat2 = new Category("compras", DataManager.getInstance().addGetType("Get", "expense"), true, 3);
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", newCategory));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat2));
       Transaction t1 = new Transaction(10.0, "2017-02-23", 2, false, true);
       Transaction t2 = new Transaction(102.0, "2017-02-25", 2, true, false);
       Transaction t3 = new Transaction(102.0, "2017-02-22", 3, true, false);
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t1));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t2));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t3));
       assertEquals("2017-02-25", DataManager.getInstance().getTransactionsBetweenDates("Category", "carro", "2017-02-01", "2017-02-28", true).get(0).getDate());

   }

   @Test
    public void getCatTotalSpent(){


       DataManager.getInstance().startDB(appContext);
       DataManager.getInstance().setContext(appContext);
       DataManager.getInstance().clearDB();
       assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
       assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
       assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
       Category cat = new Category("carro", DataManager.getInstance().addGetType("Get", "expense"), true, 2);
       Category cat2 = new Category("compras", DataManager.getInstance().addGetType("Get", "expense"), true, 3);
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat2));
       Transaction t1 = new Transaction(10.0, "2017-02-23", 2, false, true);
       Transaction t2 = new Transaction(102.0, "2017-02-25", 2, true, true);
       Transaction t3 = new Transaction(102.0, "2017-02-22", 2, true, false);
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t1));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t2));
       assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t3));
       Double v = 204.0;
       assertEquals(v, DataManager.getInstance().getTotalSpentValues("Category", null, "2017-02-01", "2017-02-28", true).get("compras"));

   }

   @Test
    public void getTypeTotalSpent(){


        DataManager.getInstance().startDB(appContext);
       DataManager.getInstance().setContext(appContext);
       DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
       Category cat = new Category("carro", DataManager.getInstance().addGetType("Get", "expense"), true, 2);
       Category cat2 = new Category("compras", DataManager.getInstance().addGetType("Get", "expense"), true, 3);
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat));
       assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat2));
        Transaction t1 = new Transaction(10.0, "2017-02-23", 2, false, true);
        Transaction t2 = new Transaction(102.0, "2017-02-25",2, true, true);
        Transaction t3 = new Transaction(102.0, "2017-02-22", 2, false, false);
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t1));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t2));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t3));
        Double v = 112.0;
        assertEquals(v, DataManager.getInstance().getTotalSpentValues("Type", "expense", "2017-02-01", "2017-02-28", false).get("expense"));

    }

    @Test
    public void onlimitCategory(){

        Date d = new Date();

        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
        Category cat = new Category("carro", DataManager.getInstance().addGetType("Get", "expense"), true, 2);
        Category cat2 = new Category("compras", DataManager.getInstance().addGetType("Get", "expense"), true, 3);
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat2));
        Suggestions sug = new Suggestions();
        Transaction t1 = new Transaction(10.0, d.getInitialDate(true, "current"), 1, false,true);
        Transaction t2 = new Transaction(7.0, d.getInitialDate(true, "current"), 2, false, true);
        Transaction t3 = new Transaction(10.0, d.getInitialDate(true, "currentDate"), 1, true, true);
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t1));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t2));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t3));
        assertEquals("carro", sug.onlimitCategory().get(0));
        Transaction t4 = new Transaction( 6.0, d.getInitialDate(true, "currentDate"), 2, true, true);
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t4));
        assertEquals(2, sug.onlimitCategory().size());

    }

    @Test
    public void cashLimit(){

        Date d = new Date();

        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
        Category cat = new Category("carro", DataManager.getInstance().addGetType("Get", "expense"), true, 2);
        Category cat2 = new Category("compras", DataManager.getInstance().addGetType("Get", "expense"), true, 3);
        Category cat3 = new Category("restauracao", DataManager.getInstance().addGetType("Get", "expense"), true, 3);
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat2));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat3));
        Suggestions sug = new Suggestions();
        Transaction t1 = new Transaction(500.0, d.getInitialDate(true, "current"),1, false, true);
        Transaction t2 = new Transaction(35.0, d.getInitialDate(true, "current"), 2, false, true);
        Transaction t3 = new Transaction(50.0, d.getInitialDate(true, "current"), 3, false, true);
        Transaction t4 = new Transaction(2.0, d.getInitialDate(true, "currentDate"), 1, true, true);
        Transaction t5 = new Transaction(250.0, d.getInitialDate(true, "currentDate"), 1, true, true);
        Transaction t6 = new Transaction( 1.0, d.getInitialDate(true, "currentDate"), 1, true, false);
        Transaction t7 = new Transaction(7.0, d.getInitialDate(true, "currentDate"),  2, true, true);
        Transaction t8 = new Transaction(10.0, d.getInitialDate(true, "currentDate"),  2, true, true);
        Transaction t9 = new Transaction(5.0, d.getInitialDate(true, "currentDate"),  2, true,true);
        Transaction t10 = new Transaction(15.0, d.getInitialDate(true, "currentDate"), 3, true, false);
        Transaction t11 = new Transaction(30.0, d.getInitialDate(true, "currentDate"), 3, true, false);
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t1));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t2));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t3));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t4));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t5));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t6));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t7));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t8));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t9));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t10));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t11));

        assertEquals("compras", sug.limitCashMethodCategory().get(0));

    }


    @Test
    public void lastmonthCompare(){

        Date d = new Date();

        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "income"));
        assertEquals(2, DataManager.getInstance().addGetType("Add", "expense"));
        assertEquals(3, DataManager.getInstance().addGetType("Add", "Fixed expense"));
        Category cat = new Category("carro", DataManager.getInstance().addGetType("Get", "Fixed Expense"), true, 2);
        Category cat2 = new Category("compras", DataManager.getInstance().addGetType("Get", "Fixed Expense"), true, 3);
        Category cat3 = new Category("restauracao", DataManager.getInstance().addGetType("Get", "Fixed Expense"), true, 3);
        Category cat4 = new Category("ordenado", DataManager.getInstance().addGetType("Get", "Income"), true, 3);
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat2));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat3));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat4));
        Suggestions sug = new Suggestions();
        Transaction t1 = new Transaction(500.0, d.getInitialDate(true, "last"), 1, false, true);
        Transaction t2 = new Transaction(35.0, d.getInitialDate(true, "last"),2, false,true);
        Transaction t3 = new Transaction(50.0, d.getInitialDate(true, "last"), 3, false,true);
        Transaction t4 = new Transaction(25.0, d.getInitialDate(true, "last"), 4, true, true);
        Transaction t5 = new Transaction(450.0, d.getInitialDate(false, "last"),  1, true, true);
        Transaction t6 = new Transaction(10.0, d.getInitialDate(false, "last"), 2, true, true);
        Transaction t7 = new Transaction(26.0, d.getInitialDate(false, "last"),  2, true,  true);
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t1));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t2));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t3));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t4));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t5));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t6));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t7));
        ArrayList<String> aux = sug.compareCatValuesBefore();
        assertEquals("compras", aux.get(0));

    }

    @Test
    public void getCatSuggestedValues(){

        Date d = new Date();

        DataManager.getInstance().startDB(appContext);
        DataManager.getInstance().setContext(appContext);
        DataManager.getInstance().clearDB();
        assertEquals(true, DataManager.getInstance().addOpenUpdateUser("Add", "ola@ola.pt", "1234", "2001-01-01"));
        assertEquals(1, DataManager.getInstance().addGetType("Add", "Income"));
        assertEquals(2, DataManager.getInstance().addGetType("Add", "Variable Expense"));
        assertEquals(3, DataManager.getInstance().addGetType("Add", "Fixed Expense"));
        Category cat = new Category("Income", DataManager.getInstance().addGetType("Get", "Income"), false, 2);
        Category cat2 = new Category("Fixed Expense", DataManager.getInstance().addGetType("Get", "Fixed Expense"), false, 2);
        Category cat3 = new Category("food", DataManager.getInstance().addGetType("Get", "Variable Expense"), true, 2);
        Category cat4 = new Category("shop", DataManager.getInstance().addGetType("Get", "Variable Expense"), true, 2);
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat2));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat3));
        assertEquals(true, DataManager.getInstance().addUpdateCategory("Add", cat4));
        Suggestions sug = new Suggestions();
        Transaction t1 = new Transaction(500.0, d.getInitialDate(true, "last"), 1, true, true);
        Transaction t2 = new Transaction(250.0, d.getInitialDate(true, "last"), 2, true, true);
        Transaction t3 = new Transaction(50.0,  d.getInitialDate(true, "last"), 3, false, true);
        Transaction t4 = new Transaction(100.0, d.getInitialDate(true, "last"), 4, false, true);
        Transaction t5 = new Transaction(25.0, d.getInitialDate(true, "last"),  3, true,  true);
        Transaction t6 = new Transaction(100.0, d.getInitialDate(true, "last"), 4, true, true);
        Transaction t7 = new Transaction(750.0, d.getInitialDate(true, "current"), 1, false,  true);
        Transaction t8 = new Transaction(400.0, d.getInitialDate(true, "current"), 2, false, true);
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t1));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t2));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t3));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t4));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t5));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t6));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t7));
        assertEquals(true, DataManager.getInstance().addUpdateTransaction("Add", t8));
        Double food = 32.5;
        Double shop = 130.0;
        assertEquals(food, sug.suggestCatValues().get("food"));
        assertEquals(shop , sug.suggestCatValues().get("shop"));
    }




}
