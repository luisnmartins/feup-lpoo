package lpoo.pocketsave.View;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.blackcat.currencyedittext.CurrencyEditText;

import java.util.ArrayList;
import java.util.List;

import lpoo.pocketsave.R;

public class Month extends AppCompatActivity {

    private Toolbar mytool;

    private CurrencyEditText EstimatedValue, SetCatValue,SetFixedExpenses,SetIncome;
    private ImageButton editIncome,editFixedExpenses,saveCategory;
    private Button saveButton;
    private TextView cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        mytool = (Toolbar) findViewById(R.id.include);
        mytool.setTitle("Month");
        mytool.setLogo(R.mipmap.ic_schedule_black_24dp);
        setSupportActionBar(mytool);
        initializeEditTexts();
        initializeImageButtons();

        saveButton = (Button) findViewById(R.id.setMonth);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SetFixedExpenses.getText().toString().equals(""))
                {
                    SetFixedExpenses.requestFocus();
                    SetFixedExpenses.setError("You need to input a value");
                }
                if (SetIncome.getText().toString().equals(""))
                {
                    SetIncome.setError("You need to input a value");
                    SetIncome.requestFocus();
                }
                if(SetCatValue.getText().toString().equals(""))
                {
                    SetCatValue.setError("You need to input a value for each category");
                    SetCatValue.requestFocus();
                }
            }
        });

        cat= (TextView) findViewById(R.id.categorycombobox);
        cat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    if(cat.getText().toString().equals("Choose Category"))
                    {
                        SetCatValue.setEnabled(false);
                    }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!cat.getText().toString().equals("Choose Category"))
                    SetCatValue.setEnabled(true);
                else SetCatValue.setEnabled(false);
            }
        });

    }


    public void addItemsOnSpinner()
    {
        //mSpinner = (Spinner) findViewById(R.id.categorycombobox);
        List<String> list = new ArrayList<String>();
        list.add("Cat1");
        list.add("Cat2");
        list.add("Cat3");
    }

    public void initializeEditTexts()
    {
        EstimatedValue = (CurrencyEditText) findViewById(R.id.SetEstimatedValue);
        SetCatValue = (CurrencyEditText) findViewById(R.id.SetCatValue);
        SetFixedExpenses = (CurrencyEditText) findViewById(R.id.SetFixedExpenses);
        SetIncome = (CurrencyEditText) findViewById(R.id.SetIncome);

        EstimatedValue.setEnabled(false);
        SetCatValue.setEnabled(false);
        SetFixedExpenses.setEnabled(false);
        SetIncome.setEnabled(false);
    }

    public void initializeImageButtons()
    {
        editIncome = (ImageButton) findViewById(R.id.editIncome);
        editFixedExpenses = (ImageButton) findViewById(R.id.editExpenses);
        saveCategory = (ImageButton) findViewById(R.id.saveCategory);

        editIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetIncome.setEnabled(true);
            }
        });

        editFixedExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetFixedExpenses.setEnabled(true);
            }
        });

        saveCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat.setText("Choose Category");
            }
        });
    }

    public void getCategoriesList(View v)
    {
        DialogFragment dialog = new ChooseSpecificCatDialog();
        dialog.show(getSupportFragmentManager(),"chooseCategories");

    }
}
