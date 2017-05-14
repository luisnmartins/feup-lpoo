package lpoo.pocketsave.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import java.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import lpoo.pocketsave.Logic.DatabaseSingleton;
import lpoo.pocketsave.Logic.PocketSave;
import lpoo.pocketsave.Logic.User;
import lpoo.pocketsave.R;

public class TransactionActivity extends AppCompatActivity {
    EditText value, description;
    TextView date;
    int year_x, month_x, day_x;
    static final int DIALOG_ID =0;
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

        value = (EditText) findViewById(R.id.ValueText);
        description = (EditText) findViewById(R.id.DescriptionText);
        savebtn = (Button) findViewById(R.id.Savebtn);
        AddData();


    }

    public void showDialogDate(){
        date = (TextView) findViewById(R.id.DateView);

        date.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        showDialog(DIALOG_ID);

                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        else
            return null;

    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month+1;
            day_x = dayOfMonth;
            date.setText(year_x+" / "+month_x+" / "+day_x);
        }
    };

    public  void AddData() {
        savebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dateString = year_x+"/"+month_x+"/"+day_x;
                            DatabaseSingleton.getInstance().getDB().addTransaction(Integer.parseInt(value.getText().toString()),
                                    dateString,
                                    description.getText().toString(), 0);
                        /*if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();*/
                    }
                }
        );
    }


}
