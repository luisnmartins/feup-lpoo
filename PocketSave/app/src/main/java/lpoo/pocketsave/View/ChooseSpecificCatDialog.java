package lpoo.pocketsave.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.blackcat.currencyedittext.CurrencyEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.Logic.Transaction;
import lpoo.pocketsave.R;

/**
 * Created by Carlos Freitas on 24/05/2017.
 */

public class ChooseSpecificCatDialog extends DialogFragment {


    private Boolean isMonth;
    public ChooseSpecificCatDialog()
    {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle b = getArguments();
        if(b != null)
            isMonth = b.getBoolean("isMonth");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
       final ArrayList<Category> aux = DataManager.getInstance().getCategory(null,null,"Variable Expense");
        if(!isMonth)
        {
            deleteDefaults(aux);
        }




        final ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(),aux);
        if(isMonth)
        ((Month) getActivity()).setCategories_size(adapter.getCount());


        builder.setTitle("Categories").setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(isMonth)
                {
                    ((TextView) getActivity().findViewById(R.id.categorycombobox)).setText(((Category)adapter.getItem(which)).getTitle());
                    ((Month) getActivity()).setCat((Category)adapter.getItem(which));
                    String date =  ((Month) getActivity()).returnFirstofMonth();
                    ArrayList<Transaction> trans = DataManager.getInstance().getTransactionsBetweenDates("Category",((Category) adapter.getItem(which)).getTitle(),date,date,false);
                    ((Month) getActivity()).add(((Category) adapter.getItem(which)).getTitle());

                    if(trans != null) {
                        Log.d("DIALOG", "TRANS NOT NULL");
                        ((Month)getActivity()).setTrans(trans.get(0));
                        ((Month) getActivity()).setSetCatValue((long)trans.get(0).getValue());
                    }
                    else
                        ((Month) getActivity()).setSetCatValue(0);
                }else
                {
                    String catName = ((Category)adapter.getItem(which)).getTitle();
                    startCatFragment(catName);
                }



            }
        });


            return builder.create();
    }


    public void startCatFragment(String catName)
    {
        AddCategoryFragment frag = new AddCategoryFragment();
        if(getActivity().getSupportFragmentManager().findFragmentByTag("addCat") == null)
        {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle b = new Bundle();
            b.putBoolean("hideEstimated",false);
            b.putBoolean("isEdit",true);
            b.putString("catName",catName);
            frag.setArguments(b);
            fragmentTransaction.replace(R.id.linear_main,frag,"addCat");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    public void deleteDefaults(ArrayList<Category> categories)
    {
        List<String> defaults = Arrays.asList(getResources().getStringArray(R.array.DefaultCategories));



        for(int i = 0; i < categories.size(); i++)
        {
            if(defaults.contains(categories.get(i).getTitle()))
            {
                categories.remove(i);
                i--;
            }
        }
    }

}
