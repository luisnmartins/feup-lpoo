package lpoo.pocketsave.View;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import lpoo.pocketsave.Logic.DatabaseHelper;
import lpoo.pocketsave.Logic.DatabaseSingleton;
import lpoo.pocketsave.R;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        populateListView();
    }

    private void populateListView(){

        Cursor cursor = DatabaseSingleton.getInstance().getDB().getAllData();

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
        list.setAdapter(myCursorAdapter);
    }

    /*private void detailViewClick(){
        ListView list = (ListView) findViewById(R.id.ListViewDB);
        list.setOnClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }
        );

    }   */

}
