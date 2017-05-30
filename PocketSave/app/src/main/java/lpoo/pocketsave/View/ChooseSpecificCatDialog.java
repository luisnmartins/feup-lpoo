package lpoo.pocketsave.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.blackcat.currencyedittext.CurrencyEditText;

import java.util.ArrayList;

import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.Logic.Transaction;
import lpoo.pocketsave.R;

/**
 * Created by Carlos Freitas on 24/05/2017.
 */

public class ChooseSpecificCatDialog extends DialogFragment {


    public ChooseSpecificCatDialog()
    {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //testList();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
       final ArrayList<Category> aux = DataManager.getInstance().getCategory(null,null,"Variable Expense");

        final ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(),aux);

        // final ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<Category>(getActivity(),android.R.layout.select_dialog_singlechoice, DataManager.getInstance().getCategory("mainMenuCategories",true));
        builder.setTitle("Categories").setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((TextView) getActivity().findViewById(R.id.categorycombobox)).setText(((Category)adapter.getItem(which)).getTitle());
                ((Month) getActivity()).setCat((Category)adapter.getItem(which));
                String date =  ((Month) getActivity()).returnFirstofMonth();
                ArrayList<Transaction> trans = DataManager.getInstance().getTransactionsBetweenDates("Category",((Category) adapter.getItem(which)).getTitle(),date,date,false);

                if(trans != null) {
                    ((Month)getActivity()).setTrans(trans.get(0));
                    ((Month) getActivity()).getSetCatValue().setText(Double.toString(trans.get(0).getValue()));
                }
                else
                    ((Month) getActivity()).getSetCatValue().setText("no value");


            }
        });


            return builder.create();
    }



    private String[] ListToArray(ArrayList<Category> aux)
    {

        String[] result = new String[aux.size()];
        for(int i = 0; i < aux.size();i++)
        {
            result[i] = aux.get(i).toString();
            System.out.println(aux.get(i));
        }
        System.out.println("o meu numero de cat e " + aux.size());
        return result;
    }
}
