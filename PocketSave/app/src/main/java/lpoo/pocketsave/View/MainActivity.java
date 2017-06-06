package lpoo.pocketsave.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.util.ArrayList;

import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.Logic.DatabaseHelper;
import lpoo.pocketsave.Logic.Transaction;
import lpoo.pocketsave.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,ChooseStatsDialog.ChooseStatsListener,SearchView.OnQueryTextListener{

    Button more;
    Menu mOptionsMenu;
    DatabaseHelper myDB;
    private DrawerLayout sDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private NavigationView mNavView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        mToolbar.setTitle("Main Menu");
        mToolbar.setLogo(R.mipmap.ic_launcher_round);
        setSupportActionBar(mToolbar);

        sDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this,sDrawerLayout,R.string.open,R.string.close);

        sDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();


        Fragment fragment = new MainFragment();
        fragment.setRetainInstance(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.linear_main, fragment);
        fragmentTransaction.commit();

        mNavView = (NavigationView) findViewById(R.id.nav_view);
        mNavView.setNavigationItemSelectedListener(this);

        Toast.makeText(MainActivity.this,"User logged in",Toast.LENGTH_LONG).show();
        ((TextView)mNavView.getHeaderView(0).findViewById(R.id.User)).setText(DataManager.getInstance().getUser().getEmail());
        Double value = DataManager.getInstance().getUser().getTotalSaved();
        TextView totalvalue = (TextView)mNavView.getHeaderView(0).findViewById(R.id.totalValue);
        if(value>= 0)
            totalvalue.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        else
            totalvalue.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        totalvalue.setText("Total Saved "+ value);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public Context getcontext(){
        return this;

    }



    public void NewTransaction(View view){



                closeAllFragments();
                Intent transactionIntent = new Intent(MainActivity.this, TransactionActivity.class);
                ArrayList<Category> aux = DataManager.getInstance().getCategory(((Button)view).getText().toString(),null,null);
                System.out.println("a categoria e " + ((Button)view).getText().toString());
                Category cat = aux.get(0);
                System.out.println("a categoria e " + cat.getID());
                Bundle b = new Bundle();
                b.putLong("cat",cat.getID());
                b.putString("Category",cat.getTitle());
                b.putBoolean("isToAdd",true);
                transactionIntent.putExtras(b);
                MainActivity.this.startActivity(transactionIntent);

    }

    public Menu getmOptionsMenu()
    {
        return mOptionsMenu;
    }



    public void getStats(View view)
    {
        Intent statsIntent;
        statsIntent = new Intent(MainActivity.this, StatsActivity.class);
        statsIntent.putExtra("Stats", String.valueOf(view.getTag()));
        MainActivity.this.startActivity(statsIntent);
    }


    public void getNewCategory(View view)
    {
        mToolbar.setTitle("New Category");
        setFragment(new AddCategoryFragment(),"cat");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.navigation_menu, menu);
        mOptionsMenu = menu;
        getMenuInflater().inflate(R.menu.main_menu, mOptionsMenu);
        mOptionsMenu.findItem(R.id.action_search).setVisible(false);
        mOptionsMenu.setGroupVisible(R.id.overGroup,false);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }




    public void viewAll() {
        more.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    /*    Cursor res = DatabaseSingleton.getInstance().getDB().getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID :"+ res.getString(0)+"\n");
                            buffer.append("VALUE :"+ res.getString(1)+"\n");
                            buffer.append("DATE :"+ res.getString(2)+"\n");
                            buffer.append("Description :"+ res.getString(3)+"\n");
                            buffer.append("Marks :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());*/
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        OverviewListFragment fragment= (OverviewListFragment) getSupportFragmentManager().findFragmentByTag("over");

        System.out.println("PRESTES");

        if( fragment != null)
            {
                if(id == R.id.sortTransDate)
                {
                fragment.getmAdapter().setmComparator(fragment.getDateComparator());
                fragment.getmRecyclerView().setAdapter(fragment.getmAdapter());
                }else if(id == R.id.sortTransValue)
                {
                    fragment.getmAdapter().setmComparator(fragment.getValueComparator());
                    System.out.println("entrou");
                    fragment.getmAdapter().notifyDataSetChanged();
                    fragment.getmRecyclerView().setAdapter(fragment.getmAdapter());
                }else if(id == R.id.sortTransCat)
                {
                    fragment.getmAdapter().setmComparator(fragment.getCatComparator());

                    fragment.getmRecyclerView().setAdapter(fragment.getmAdapter());
                    fragment.getmAdapter().notifyDataSetChanged();
                }
            }




        //noinspection SimplifiableIfStatemen
        return super.onOptionsItemSelected(item);
    }

    protected void quitApp()
    {
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);

    }

    protected void setFragment(Fragment fragment,String tag)
    {
        //findViewById(R.id.linear_main).setVisibility(LinearLayout.INVISIBLE);

         if(getSupportFragmentManager().findFragmentByTag(tag) == null)
         {
             FragmentManager fragmentManager = getSupportFragmentManager();
             FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
             fragmentTransaction.replace(R.id.linear_main, fragment,tag);
             fragmentTransaction.addToBackStack(null);
             fragmentTransaction.commit();
         }


    }


    protected void logOut()
    {
        Intent logoutIntent = new Intent(MainActivity.this,LoginActivity.class);
        MainActivity.this.startActivity(logoutIntent);
        finish();
    }


    public Toolbar getmToolbar()
    {
        return this.mToolbar;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id == R.id.nav_overview)
        {
            mOptionsMenu.findItem(R.id.action_search).setVisible(true);
            mOptionsMenu.setGroupVisible(R.id.overGroup,true);;
            mToolbar.setTitle("Overview");
            OverviewListFragment frag = new OverviewListFragment();
            Bundle b = new Bundle();
            b.putString("TransType","Variable Expense");
            frag.setArguments(b);
            setFragment(frag,"over");
        }else if(id == R.id.nav_transactions)
        {
            closeAllFragments();
            Intent transactionIntent = new Intent(MainActivity.this, StatsActivity.class);
            MainActivity.this.startActivity(transactionIntent);

        }else if(id == R.id.nav_logout)
        {
            this.logOut();
        }else if(id == R.id.nav_settings)
        {
            closeAllFragments();
            Intent settingsIntent = new Intent(MainActivity.this,SettingsActivity.class);
            MainActivity.this.startActivity(settingsIntent);

        }else if(id == R.id.nav_month)
        {
            closeAllFragments();
            Intent transactionIntent = new Intent(MainActivity.this, Month.class);
            MainActivity.this.startActivity(transactionIntent);
        }else if(id == R.id.nav_exit)
        {
            quitApp();
        }
        sDrawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }



    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        mToolbar.setTitle("Stats");
        setFragment(new StatsFragment(),"stats");

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        mToolbar.setTitle("Stats");
        setFragment(new CatStatsFragment(),"stats");

    }




    public void closeAllFragments()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        for(int i = 0; i < fragmentManager.getBackStackEntryCount();i++)
        {
            fragmentManager.popBackStack();
        }
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // Here is where we are going to implement the filter logic
        OverviewListFragment fragment = (OverviewListFragment) getSupportFragmentManager().findFragmentByTag("over");
        if(fragment != null)
        {
            fragment.filterIn(newText);
        }
        return true;
    }



}
