package lpoo.pocketsave.View;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import lpoo.pocketsave.R;

/**
 * Created by Carlos Freitas on 24/05/2017.
 */

public class ChooseSpecificCatDialog extends DialogFragment {

    private CharSequence mOptions[] = {"cat1","cat2","cat3","cat4","cat5","cat1","cat2","cat3","cat4","cat5","cat1","cat2","cat3","cat4","cat5"};

    public ChooseSpecificCatDialog()
    {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //testList();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Categories").setItems(mOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((TextView) getActivity().findViewById(R.id.categorycombobox)).setText(mOptions[which]);
            }
        });


            return builder.create();
    }

    public void testList()
    {
        for(int i = 0; i < 10; i++)
        {
            mOptions[i] = "Cat"+i;
        }
    }
}
