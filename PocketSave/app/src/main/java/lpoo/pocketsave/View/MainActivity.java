package lpoo.pocketsave.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import jp.wasabeef.recyclerview.animators.ScaleInAnimator;
import lpoo.pocketsave.Logic.DatabaseHelper;
import lpoo.pocketsave.Logic.DatabaseSingleton;
import lpoo.pocketsave.Logic.PocketSave;
import lpoo.pocketsave.Logic.User;
import lpoo.pocketsave.R;
import lpoo.pocketsave.View.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,ChooseStatsDialog.ChooseStatsListener{

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

        more = (Button) findViewById(R.id.Morebtn);
        //initialize database instance
        DatabaseSingleton.getInstance().createDB(this);
       // DatabaseSingleton.getInstance().getDB().addUser("ola@ola.pt", "1234");
        //if(User.getInstance().login("ola@ola.pt", "1234"))
            Toast.makeText(MainActivity.this,"User logged in",Toast.LENGTH_LONG).show();
       // else
           // Toast.makeText(MainActivity.this,"Error trying to log in. Please try again",Toast.LENGTH_LONG).show();


        /*DatabaseSingleton.getInstance().getDB().addType("income");
        DatabaseSingleton.getInstance().getDB().addType("expense");
        DatabaseSingleton.getInstance().getDB().addCategory("cat1", 0);
        DatabaseSingleton.getInstance().getDB().addCategory("cat2", 1);*/
        /*
        PocketSave.getInstance().addCategory("Carro", "income");
        PocketSave.getInstance().addCategory("Propinas", "income");
        TextView balanceView = (TextView) findViewById(R.id.BalanceView);
        balanceView.setText("1244");
        viewAll();*/


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public Context getcontext(){
        return this;

    }



    public void NewTransaction(View view){



                closeAllFragments();
                Intent transactionIntent = new Intent(MainActivity.this, TransactionActivity.class);
                transactionIntent.putExtra("Category", String.valueOf(view.getTag()));
                MainActivity.this.startActivity(transactionIntent);

    }

    public Menu getmOptionsMenu()
    {
        return mOptionsMenu;
    }

    public void getOverview(View view){

        //Intent overviewIntent = new Intent(MainActivity.this, OverviewActivity.class);
        //MainActivity.this.startActivity(overviewIntent);
       // getMenuInflater().inflate(R.menu.navigation_menu,mOptionsMenu);
        mToolbar.setTitle("Overview");
        setFragment(new OverviewListFragment(),"over");
    }

    public void getStats(View view)
    {
        Intent transactionIntent = new Intent(MainActivity.this, StatsActivity.class);
        transactionIntent.putExtra("Stats", String.valueOf(view.getTag()));
        MainActivity.this.startActivity(transactionIntent);
    }

    public void setStatsAlert(View view)
    {
        DialogFragment dialog = new ChooseStatsDialog();
        dialog.show(getSupportFragmentManager(),"ChooseStatsDialog");
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
        getMenuInflater().inflate(R.menu.edit_menu, mOptionsMenu);
        mOptionsMenu.findItem(R.id.addTrans).setVisible(false);
        return true;
    }

    /*public void goTransaction(){

        PocketSave.getInstance().changeState(PocketSave.State.Transaction, );

    }*/

    public void viewAll() {
        more.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = DatabaseSingleton.getInstance().getDB().getAllData();
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
                        showMessage("Data",buffer.toString());
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

        if(id == R.id.addTrans)
        {
            OverviewListFragment fragment= (OverviewListFragment) getSupportFragmentManager().findFragmentByTag("over");
            if( fragment != null)
            {
                fragment.getmAdapter().add("newly added item", 1);
                fragment.getmRecyclerView().setAdapter(fragment.getmAdapter());
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



        //resetButtons(false);
    }

    public void resetButtons(Boolean clickable)
    {
        findViewById(R.id.Cat1btn).setClickable(clickable);
        findViewById(R.id.Cat2btn).setClickable(clickable);
        findViewById(R.id.Cat3btn).setClickable(clickable);
        findViewById(R.id.Cat4btn).setClickable(clickable);
        findViewById(R.id.Cat5btn).setClickable(clickable);
        findViewById(R.id.Addbtn).setClickable(clickable);
        findViewById(R.id.BalanceView).setClickable(clickable);
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
            mOptionsMenu.findItem(R.id.addTrans).setVisible(true);
            mToolbar.setTitle("Overview");
            setFragment(new OverviewListFragment(),"over");
        }else if(id == R.id.nav_transactions)
        {
            closeAllFragments();
            Intent transactionIntent = new Intent(MainActivity.this, StatsActivity.class);
            MainActivity.this.startActivity(transactionIntent);

        }else if(id == R.id.nav_logout)
        {
            quitApp();
        }else if(id == R.id.nav_settings)
        {

        }else if(id == R.id.nav_month)
        {
            closeAllFragments();
            Intent transactionIntent = new Intent(MainActivity.this, Month.class);
            MainActivity.this.startActivity(transactionIntent);
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


    public void getColorPicker(View v)
    {

        ColorPickerDialogBuilder
                .with(this.getcontext())
                .setTitle("Choose color")
                .initialColor(999999999)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        //toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        changeBackgroundColor(selectedColor);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }

    public void changeBackgroundColor(int color)
    {
        Button btn = (Button) findViewById(R.id.colorbutton);
        btn.setBackgroundColor(color);
    }


    public void closeAllFragments()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        for(int i = 0; i < fragmentManager.getBackStackEntryCount();i++)
        {
            fragmentManager.popBackStack();
        }
    }





}
