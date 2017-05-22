package lpoo.pocketsave.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;

/**
 * Created by Carlos Freitas on 21/05/2017.
 */

public class ChooseStatsDialog extends DialogFragment {


    public ChooseStatsDialog() {
    }

    /* The activity that creates an instance of this dialog fragment must
    * implement this interface in order to receive event callbacks.
    * Each method passes the DialogFragment in case the host needs to query it. */
    public interface ChooseStatsListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    ChooseStatsListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (ChooseStatsListener) getActivity();
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("How do you want to see your statistics?")
                .setPositiveButton("Between 2 Dates", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(ChooseStatsDialog.this);
                    }
                })
                .setNegativeButton("Compare Periods", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(ChooseStatsDialog.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}

