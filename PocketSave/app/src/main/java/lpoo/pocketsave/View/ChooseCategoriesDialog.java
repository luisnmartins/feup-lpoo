package lpoo.pocketsave.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.Logic.DataManager;


/**
 * Created by Carlos Freitas on 22/05/2017.
 */

public class ChooseCategoriesDialog extends DialogFragment {

    private  List mSelectedItems;
    public ChooseCategoriesDialog() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mSelectedItems = new ArrayList();  // Where we track the selected items


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ArrayList<Category> aux = new ArrayList<>();
        aux.add(0,new Category(1,"Cenas",1,true));
        aux.add(1,new Category(2,"consigo",1,true));
        String[] categories = ListToArray(DataManager.getInstance().getCategory(null));

        final ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<Category>(getActivity(),android.R.layout.select_dialog_multichoice, aux);
        // Set the dialog title
        builder.setTitle("Categories")

               .setMultiChoiceItems(categories, null, new DialogInterface.OnMultiChoiceClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                       if (isChecked) {
                           // If the user checked the item, add it to the selected items
                           mSelectedItems.add(which);
                       } else if (mSelectedItems.contains(which)) {
                           // Else, if the item is already in the array, remove it
                           mSelectedItems.remove(Integer.valueOf(which));
                       }
                   }

               })
                // Set the action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog

                    }
                })
                .setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return  builder.create();

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
