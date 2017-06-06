package lpoo.pocketsave.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.R;

public class FixValueActivity extends AppCompatActivity {


    private EditText title,value;
    private Boolean isIncome;
    private TextView inputMoths;
    private Button save;
    private ArrayList<String> monthsSelected;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_value);

        mToolbar = (Toolbar) findViewById(R.id.settingsToolbar);
        setSupportActionBar(mToolbar);
        title = (EditText) findViewById(R.id.inputFixedTitle);
        value = (EditText) findViewById(R.id.inputFixedValue);
        save = (Button) findViewById(R.id.fixedSaveButton);
        inputMoths = (TextView) findViewById(R.id.inputMonthFixed);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().equals(""))
                {
                    title.setError("Needs a title!!");
                    title.requestFocus();
                    return;
                }
                if(value.getText().equals(""))
                {
                    value.setError("Dont forget to put the value");
                    value.requestFocus();
                    return;

                }
                if(monthsSelected.isEmpty() || monthsSelected == null)
                {
                    inputMoths.setError("Select the months!");
                    inputMoths.requestFocus();
                    return;
                }
                if(isIncome)
                {
                    for(int i = 0; i < monthsSelected.size();i++)
                    {
                        Double dovalue = Double.parseDouble(value.getText().toString());
                        String date = "01-"+monthsSelected.get(i)+ "-" + Calendar.getInstance().get(Calendar.YEAR);
                        long cat_id = DataManager.getInstance().getCategory("Income",null,null).get(0).getID();
                        DataManager.getInstance().addUpdateTransaction("Add",-1,dovalue,date,title.getText().toString(),cat_id,false,null,true);
                    }

                }else
                {
                    for(int i = 0; i < monthsSelected.size();i++)
                    {
                        Double dovalue = Double.parseDouble(value.getText().toString());
                        String date = "01-"+monthsSelected.get(i)+ "-" + Calendar.getInstance().get(Calendar.YEAR);
                        long cat_id = DataManager.getInstance().getCategory("Fixed Expense",null,null).get(0).getID();
                        DataManager.getInstance().addUpdateTransaction("Add",-1,dovalue,date,title.getText().toString(),cat_id,false,null,true);
                    }
                }
                finish();;

            }
        });

        inputMoths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMonthsDialog();


            }
        });

       isIncome =  this.getIntent().getExtras().getBoolean("isIncome");
    }


    public void createMonthsDialog()
    {
         final ArrayList<String> auxMonths = new ArrayList<>();
        final String[] aux = getResources().getStringArray(R.array.months);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set the months").setMultiChoiceItems(R.array.months, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked)
                        {


                            auxMonths.add(aux[which]);


                        }else if(auxMonths.contains(aux[which])){
                            auxMonths.remove(aux[which]);
                        }
                    }
                }).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                monthsSelected = auxMonths;
                if(monthsSelected != null)
                {
                    String text = new String();
                    for(int i = 0; i < monthsSelected.size();i++)
                    {
                        text += monthsSelected.get(i) + " ";
                    }
                    inputMoths.setText(text);
                }else inputMoths.setText("Choose Months");
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();


    }


}
