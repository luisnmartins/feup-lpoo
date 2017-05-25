package lpoo.pocketsave.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.content.DialogInterface;
import java.util.ArrayList;
import java.util.List;


import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.widget.ArrayAdapter;

import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.Logic.Transaction;
import lpoo.pocketsave.R;


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
        final ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<Category>(getActivity(),android.R.layout.select_dialog_multichoice, DataManager.getInstance().getCategory("mainMenuCategories"));
        // Set the dialog title
        builder.setTitle("Categories")

                .setAdapter(arrayAdapter,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

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

        return builder.create();
    }

}
