package lpoo.pocketsave.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


import lpoo.pocketsave.Logic.DatabaseHelper;
import lpoo.pocketsave.Logic.PocketSave;
import lpoo.pocketsave.R;

public class MainActivity extends AppCompatActivity {

    Button Cat1, Cat2, Cat3, Cat4, Cat5, More;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DatabaseHelper myDB = new DatabaseHelper(this);
        //PocketSave newPocketSave = new PocketSave(myDB);
        PocketSave.getInstance();

        Cat1 = (Button) findViewById(R.id.Cat1btn);
        Cat2 = (Button) findViewById(R.id.Cat2btn);
        Cat3 = (Button) findViewById(R.id.Cat3btn);
        Cat4 = (Button) findViewById(R.id.Cat4btn);
        Cat5 = (Button) findViewById(R.id.Cat5btn);
        More = (Button) findViewById(R.id.Morebtn);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void NewTransaction(View view){

        Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
        intent.putExtra("button", view.getId());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
