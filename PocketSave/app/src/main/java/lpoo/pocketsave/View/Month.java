package lpoo.pocketsave.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import lpoo.pocketsave.R;

public class Month extends AppCompatActivity {

    private Toolbar mytool;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        mytool = (Toolbar) findViewById(R.id.include);
        mytool.setTitle("Month");
        mytool.setLogo(R.mipmap.ic_schedule_black_24dp);
        setSupportActionBar(mytool);

        addItemsOnSpinner();
    }


    public void addItemsOnSpinner()
    {
        mSpinner = (Spinner) findViewById(R.id.categorycombobox);
        List<String> list = new ArrayList<String>();
        list.add("Cat1");
        list.add("Cat2");
        list.add("Cat3");
    }
}
