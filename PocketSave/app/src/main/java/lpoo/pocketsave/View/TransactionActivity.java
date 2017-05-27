package lpoo.pocketsave.View;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blackcat.currencyedittext.CurrencyEditText;

import java.util.Calendar;

import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.R;

public class TransactionActivity extends AppCompatActivity {
    EditText  description;
    CurrencyEditText value;
    TextView date;
    int year_x, month_x, day_x;
    String category;
    Button savebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showDialogDate();

        value = (CurrencyEditText) findViewById(R.id.ValueText);
        description = (EditText) findViewById(R.id.DescriptionText);
        savebtn = (Button) findViewById(R.id.Savebtn);
        category = getIntent().getExtras().getString("Category");
        AddData();


    }


    public void showDialogDate(){
        date = (TextView) findViewById(R.id.DateView);

        date.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        DialogFragment newFragment = new DatePickerFragment().newInstance(v.getId());
                        newFragment.show(getSupportFragmentManager(),"datePicker");

                    }
                }
        );
    }


    public  void AddData() {
        savebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        value.getTextLocale();
                        if(value.getText().toString() == "" || date.getText().toString().equals("Choose Date"))
                        {
                            return ;
                        }
                        String dateString = date.getText().toString();
                        String desc = description.getText().toString();
                        double valueDouble = value.getRawValue();
                        System.out.println("value" + valueDouble);
                        Bundle b = getIntent().getExtras();
                        long id = 0;
                        if(b != null)
                          id = b.getInt("CatID");
                        DataManager.getInstance().addChangeTransaction("Add",id,valueDouble,dateString,desc,1,true);
                        finish();
                        //TODO: change done value

                    }
                }
        );
    }


}
