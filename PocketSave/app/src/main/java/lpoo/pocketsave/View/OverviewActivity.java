package lpoo.pocketsave.View;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import lpoo.pocketsave.Logic.DatabaseHelper;
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


    }


}
