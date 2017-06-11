package lpoo.pocketsave.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import java.util.ArrayList;

import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.R;

/**
 * Created by Carlos Freitas on 11/06/2017.
 */

public class ChooseCategoriesForMain extends DialogFragment{

    private ArrayList<String> selectedItems;
    private  boolean[] selected;
    private static int count;
     private ArrayList<Category> cats = DataManager.getInstance().getCategory(null,null,"Variable Expense");

    public ChooseCategoriesForMain()
    {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        selectedItems = new ArrayList();  // Where we track the selected items
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        count = 0;
        final String aux[] = getArray();
        selected = new boolean[aux.length];

        builder.setTitle("Categories")
                .setMultiChoiceItems(aux, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                               count += isChecked ? 1:-1;
                                selected[which] = isChecked;
                                if(count > 5)
                                {
                                    Toast.makeText(getActivity(),"You Selected too many",Toast.LENGTH_SHORT).show();
                                    selected[which] = false;
                                    count --;
                                    ((AlertDialog)dialog).getListView().setItemChecked(which,false);
                                }

                            }
                        })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        if(count == -1)
                            setThings();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();

    }

    public String[] getArray()
    {
        String[] ret = new String[cats.size()];

        for(int i = 0 ; i < cats.size();i++)
        {
            ret[i] = cats.get(i).getTitle();
        }

        return ret;

    }
    public void setThings()
    {
        if(selected != null )
        {
            for(int i = 0; i < selected.length;i++)
            {
                if(selected[i])
                {
                    cats.get(i).setMainMenu(selected[i]);
                    DataManager.getInstance().addUpdateCategory("Update",cats.get(i));
                } else if (selected[i] == false) {
                    cats.get(i).setMainMenu(selected[i]);
                    DataManager.getInstance().addUpdateCategory("Update",cats.get(i));
                }

            }
            ((MainActivity)getActivity()).setCatsText();
        }
    }
}
