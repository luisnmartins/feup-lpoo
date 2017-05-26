package lpoo.pocketsave.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

/**
 * Created by Carlos Freitas on 22/05/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public int year;
    public int month;
    public int day;
    public View pressedView;

    public DatePickerFragment(){

    }


    public static DatePickerFragment newInstance(int id ) {
        
        Bundle args = new Bundle();
        args.putInt("id",id);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),this,year,month,day);


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        this.year = year;
        this.month = month;
        this.day = dayOfMonth;

        Bundle args = getArguments();
        TextView text = (TextView) getActivity().findViewById(args.getInt("id"));
        text.setText(this.year + "-" + this.month + "-" + day);


        



    }

    public int getYear()
    {
        return this.year;
    }

    public  int getMonth()
    {
        return this.month;
    }

    public int getDay() {
        return day;
    }
}
