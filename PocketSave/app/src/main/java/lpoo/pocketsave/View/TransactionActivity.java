package lpoo.pocketsave.View;

import android.os.Bundle;
import java.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import lpoo.pocketsave.Logic.PocketSave;
import lpoo.pocketsave.Logic.User;
import lpoo.pocketsave.R;

public class TransactionActivity extends AppCompatActivity {
    EditText value, description, date;
    Button savebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        value = (EditText) findViewById(R.id.ValueText);
        date = (EditText) findViewById(R.id.DateText);
        description = (EditText) findViewById(R.id.DescriptionText);
        savebtn = (Button) findViewById(R.id.Savebtn);
        AddData();

    }

    public  void AddData() {
        savebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            PocketSave.getInstance().addTransaction(Integer.parseInt(value.getText().toString()),
                                    date.getText().toString(),
                                    description.getText().toString(), getIntent().getStringExtra("Category"));
                        /*if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();*/
                    }
                }
        );
    }


}
