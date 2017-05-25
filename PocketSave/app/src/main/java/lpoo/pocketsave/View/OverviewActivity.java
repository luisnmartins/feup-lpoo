package lpoo.pocketsave.View;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import lpoo.pocketsave.Logic.DatabaseHelper;
import lpoo.pocketsave.Logic.DatabaseSingleton;
import lpoo.pocketsave.R;



public class OverviewActivity extends AppCompatActivity {


    private DrawerLayout sDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        mToolbar.setTitle("Overview");
        setSupportActionBar(mToolbar);

         sDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutOverview);
         mToggle= new ActionBarDrawerToggle(this,sDrawerLayout,R.string.open,R.string.close);

        sDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();


        //populateListView();
        registerListClickCallback();
    }

    private void populateListView(){

        /*Cursor cursor = DatabaseSingleton.getInstance().getDB().getAllData();

        startManagingCursor(cursor);


        String[] fromFieldNames = new String[]
                {DatabaseHelper.TRANS_ID,DatabaseHelper.TRANS_VALUE, DatabaseHelper.TRANS_DATE};
        int[] toViewIDs = new int[]
                {R.id.item_id,    R.id.item_value,     R.id.item_date};



        // Create adapter to may columns of the DB onto elemesnt in the UI.
        SimpleCursorAdapter myCursorAdapter =
                new SimpleCursorAdapter(
                        this,		// Context
                        R.layout.item_layout,	// Row layout template
                        cursor,					// cursor (set of DB records to map)
                        fromFieldNames,			// DB Column names
                        toViewIDs				// View IDs to put information in
                );

        System.out.println("Try  fill list");
        ListView list = (ListView) findViewById(R.id.ListViewDB);
        list.setAdapter(myCursorAdapter);*/

    }

    private void registerListClickCallback() {
        ListView list = (ListView) findViewById(R.id.ListViewDB);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long idInDB) {



                displayToastForId(idInDB);
            }
        });
    }

    private void displayToastForId(long idInDB) {
        Cursor cursor = DatabaseSingleton.getInstance().getDB().getTransaction(Long.toString(idInDB));
        if(cursor != null) {
            if (cursor.moveToFirst()) {
                String idDB = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_VALUE));

                String message = "ID: " + idDB + "\n"
                        + "Name: " + name;
                Toast.makeText(OverviewActivity.this, message, Toast.LENGTH_LONG).show();
            }
            cursor.close();
        }
        else
            System.out.println("Error getting Transaction\n");
    }
}
