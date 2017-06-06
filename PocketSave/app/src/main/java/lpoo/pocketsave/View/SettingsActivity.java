package lpoo.pocketsave.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.R;

public class SettingsActivity extends AppCompatActivity {


    private RadioGroup listType;
    private EditText newPass, newEmail;
    private ImageButton editPass,editMail;
    private Button save;
    private ImageButton addIncome,addExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.settingsToolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);

        addIncome = (ImageButton) findViewById(R.id.newIncomeButton);
        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareNewIncome();
            }
        });
        addExpense = (ImageButton) findViewById(R.id.newExpenseButton);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareNewExpense();
            }
        });

        listType = (RadioGroup) findViewById(R.id.radioGroupList);
        listType.clearCheck();

        initializeAccountOptions();


        checkRadioButtonPressed();


    }

    void initializeAccountOptions()
    {
        newPass = (EditText) findViewById(R.id.inputNewPass);
        newEmail = (EditText) findViewById(R.id.inputNewEmail);

        newEmail.setText(DataManager.getInstance().getUser().getEmail());
        newPass.setText(DataManager.getInstance().getUser().getPassword());

        newPass.setEnabled(false);
        newEmail.setEnabled(false);
        initializeEditAccount();
        initializeSaveAccountChanges();

    }

    void initializeEditAccount()
    {
        editPass = (ImageButton) findViewById(R.id.editNewPassword);
        editMail = (ImageButton) findViewById(R.id.allowEditNewEmail);

        editPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newPass.isEnabled())
                verifyPasswordDialog(newPass);
            }
        });

        editMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newEmail.isEnabled())
                verifyPasswordDialog(newEmail);
            }
        });
    }

    void initializeSaveAccountChanges()
    {
        save = (Button) findViewById(R.id.saveSettingsChanges);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = newEmail.getText().toString();
                String pass = newPass.getText().toString();


                if(!isEmailValid(email) || (DataManager.getInstance().addOpenUpdateUser("Open",email,pass,null) && newEmail.isEnabled()))
                {
                    newEmail.setError("Invalid new Email");
                    newEmail.requestFocus();
                    return;
                }

                if(!isPasswordValid(pass))
                {
                    newPass.setError("Password too small");
                    newPass.requestFocus();
                    return;
                }


                DataManager.getInstance().addOpenUpdateUser("Update",email,pass,null);
                Toast.makeText(getApplicationContext(),"Changes Save",Toast.LENGTH_SHORT);
                newPass.setEnabled(false);
                newEmail.setEnabled(false);

            }
        });
    }


    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }



    public void checkRadioButtonPressed()
    {

        listType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId){
                    case R.id.IncomeButton:{

                        destroyFragment("overExpense");
                        FixedExpensesIncomesListFragment frag = new FixedExpensesIncomesListFragment();
                        Bundle b = new Bundle();
                        b.putString("TransType","Income");
                        frag.setArguments(b);
                        createListFragment(frag,"overIncome");
                        break;
                    }
                    case R.id.ExpensesButton:{

                        destroyFragment("overIncome");
                        FixedExpensesIncomesListFragment frag = new FixedExpensesIncomesListFragment();
                        Bundle b = new Bundle();
                        b.putString("TransType","Fixed Expense");
                        frag.setArguments(b);
                        createListFragment(frag,"overExpense");
                        break;

                    }
                }
            }
        });

    }

    public void createListFragment(Fragment fragment,String tag)
    {
        if(getSupportFragmentManager().findFragmentByTag(tag) == null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.listContainer, fragment,tag);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }


    public  void destroyFragment(String tag)
    {
        FixedExpensesIncomesListFragment fragment = (FixedExpensesIncomesListFragment) getSupportFragmentManager().findFragmentByTag(tag);
        if(fragment != null)
        {
            getSupportFragmentManager().popBackStack();
        }
    }



    public void verifyPasswordDialog(final EditText edit)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Verify Password");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(input.getText().toString().equals(DataManager.getInstance().getUser().getPassword()))
                {
                    edit.setEnabled(true);
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    public void prepareNewIncome()
    {
        destroyFragment("overExpense");
        destroyFragment("overIncome");
        Intent intentNewIncome = new Intent(SettingsActivity.this,FixValueActivity.class);
        Bundle b = new Bundle();
        b.putBoolean("isIncome",true);
        intentNewIncome.putExtras(b);
        SettingsActivity.this.startActivity(intentNewIncome);
    }

    public void prepareNewExpense()
    {
        destroyFragment("overExpense");
        destroyFragment("overIncome");
        Intent intentNewExpense = new Intent(SettingsActivity.this,FixValueActivity.class);
        Bundle b = new Bundle();
        b.putBoolean("isIncome",false);
        intentNewExpense.putExtras(b);
        SettingsActivity.this.startActivity(intentNewExpense);
    }


}
