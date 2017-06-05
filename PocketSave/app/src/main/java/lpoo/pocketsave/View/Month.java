package lpoo.pocketsave.View;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.Logic.Transaction;
import lpoo.pocketsave.R;

public class Month extends AppCompatActivity {

    private Toolbar mytool;

    private EditText  SetCatValue,SetFixedExpenses,SetIncome;
    private  EditText EstimatedValue;
    private ImageButton editIncome,editFixedExpenses,saveCategory;
    private Button saveButton;
    private TextView cat;
    private Category category;
    private Transaction trans;
    private int categories_size=1;
    private String tempCatName;
    private HashMap<String,Double> catValues = new HashMap<>();
    private ImageButton addCategory;

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

        addCategory = (ImageButton) findViewById(R.id.addCatMonth);
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCategoryFragment();
            }
        });
        saveButton = (Button) findViewById(R.id.setMonth);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SetFixedExpenses.getText().toString().equals(""))
                {
                    SetFixedExpenses.requestFocus();
                    SetFixedExpenses.setError("You need to input a value");
                    return;
                }
                if (SetIncome.getText().toString().equals(""))
                {
                    SetIncome.setError("You need to input a value");
                    SetIncome.requestFocus();
                    return;
                }
                if(categories_size != catValues.size())
                {
                    SetCatValue.setError("You need to add values to all categories");
                    SetCatValue.requestFocus();
                    return;
                }


                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = 1;
                String date = year + "-" + month + "-" + day;
                Double IncomeValue = Double.parseDouble(SetIncome.getText().toString());
                Double ExpenseValue = Double.parseDouble(SetFixedExpenses.getText().toString());
                long IncomeID = DataManager.getInstance().getCategory("Income",null,null).get(0).getID();
                long ExpenseID = DataManager.getInstance().getCategory("Fixed Expense",null,null).get(0).getID();
                DataManager.getInstance().addUpdateTransaction("Add",-1,IncomeValue,date,"Income",IncomeID,false, null,true);
                DataManager.getInstance().addUpdateTransaction("Add",-1,ExpenseValue,date,"Expenses",ExpenseID,false, null,true);
                Intent mainIntent = new Intent(Month.this, MainActivity.class);
                Month.this.startActivity(mainIntent);
                finish();




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
        EstimatedValue = (EditText) findViewById(R.id.SetEstimatedValue);
        SetCatValue = (EditText) findViewById(R.id.SetCatValue);
        SetFixedExpenses = (EditText) findViewById(R.id.SetFixedExpenses);
        SetIncome = (EditText) findViewById(R.id.SetIncome);

        EstimatedValue.setEnabled(false);
        SetCatValue.setEnabled(false);
        SetFixedExpenses.setEnabled(false);
        SetIncome.setEnabled(false);
    }

    public void setCat(Category cat)
    {
        this.category = cat;
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
                if(!SetCatValue.getText().toString().equals("") && !cat.getText().toString().equals("Choose Category"))
                {
                    String date = returnFirstofMonth();
                    Double value = Double.parseDouble(SetCatValue.getText().toString());
                    if(trans == null){
                        DataManager.getInstance().addUpdateTransaction("Add",-1,value,date,"estimativa",category.getID(),false, null,true);

                    }else{
                        DataManager.getInstance().addUpdateTransaction("Update",trans.getID(),value,date,"estimativa",category.getID(),false,null,true);
                    }
                    catValues.put(tempCatName, (double) value);
                    Double estimative = calculateBalance();
                    EstimatedValue.setText(Double.toString(estimative));

                }else
                {
                    SetCatValue.setError("You need to insert a value");
                    SetCatValue.requestFocus();
                }
                cat.setText("Choose Category");
            }
        });
    }

    public void getCategoriesList(View v)
    {
        DialogFragment dialog = new ChooseSpecificCatDialog();
        dialog.show(getSupportFragmentManager(),"chooseCategories");

    }

    public void setTrans(Transaction trans)
    {
        this.trans  =trans;
    }

    public String returnFirstofMonth()
    {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = 1;
        String date = year + "-" + month + "-" + day;
        return date;
    }

    public void setSetCatValue(long value)
    {
        if(value >0)
            SetCatValue.setText(Long.toString(value));
        else
            SetCatValue.setHint("No Value");
    }

    public void setCategories_size(int number)
    {
        this.categories_size = number;
    }

    public void add(String name)
    {
        tempCatName= name;

    }

    public Double calculateBalance()
    {
        Double income = Double.parseDouble(SetIncome.getText().toString());
        Double expenses = Double.parseDouble(SetFixedExpenses.getText().toString());
        Double valor=0.0;
        Double total;

        for(HashMap.Entry<String, Double> expected : catValues.entrySet()){
            valor += expected.getValue();
        }

        total=income-expenses-valor;
        System.out.println("valor total " + total);
        return total;
    }

    public void addCategoryFragment()
    {
        AddCategoryFragment frag = new AddCategoryFragment();
        if(getSupportFragmentManager().findFragmentByTag("addCat") == null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle b = new Bundle();
            b.putBoolean("hideEstimated",true);
            frag.setArguments(b);
            fragmentTransaction.replace(R.id.linear_month,frag,"addCat");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

}
