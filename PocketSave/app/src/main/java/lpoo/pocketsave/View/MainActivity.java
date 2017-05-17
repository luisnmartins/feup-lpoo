package lpoo.pocketsave.View;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import lpoo.pocketsave.Logic.DatabaseHelper;
import lpoo.pocketsave.Logic.PocketSave;
import lpoo.pocketsave.R;

public class MainActivity extends AppCompatActivity {

    Button more;
    DatabaseHelper myDB;
    private DrawerLayout sDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;



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




        more = (Button) findViewById(R.id.Morebtn);

        myDB = PocketSave.getInstance().createDB(this);
        myDB.addUser("ola@ola.pt", "1234");
        PocketSave.getInstance().addCategory("Carro", "income");
        PocketSave.getInstance().addCategory("Propinas", "income");
        TextView balanceView = (TextView) findViewById(R.id.BalanceView);
        balanceView.setText("1244");
        viewAll();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void NewTransaction(View view){


                Intent transactionIntent = new Intent(MainActivity.this, TransactionActivity.class);
                transactionIntent.putExtra("Category", String.valueOf(view.getTag()));
                MainActivity.this.startActivity(transactionIntent);

    }

    public void getOverview(View view){

        Intent overviewIntent = new Intent(MainActivity.this, OverviewActivity.class);
        MainActivity.this.startActivity(overviewIntent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
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
                        Cursor res = myDB.getAllData();
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
                            /*buffer.append("Marks :"+ res.getString(3)+"\n\n");*/
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

        //noinspection SimplifiableIfStatemen
        return super.onOptionsItemSelected(item);
    }


}
