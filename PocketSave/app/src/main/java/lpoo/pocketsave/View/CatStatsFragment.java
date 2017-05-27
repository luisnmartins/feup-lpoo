package lpoo.pocketsave.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.Logic.Transaction;
import lpoo.pocketsave.R;

/**
 * Created by Carlos Freitas on 21/05/2017.
 */

public class CatStatsFragment extends Fragment {


    private TextView chooseComp;
    private ImageButton setStats;

    public CatStatsFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.catstats_fragment, container, false);
        chooseComp = (TextView) rootView.findViewById(R.id.chooseCompare);
        chooseComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        setStats = (ImageButton) rootView.findViewById(R.id.setStats1);
        setStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String from = ((TextView) rootView.findViewById(R.id.chooseFrom)).getText().toString();
                String to = ((TextView) rootView.findViewById(R.id.chooseTo)).getText().toString();
                if(!from.equals("Choose Date")  && !to.equals("Choose Date")) {
                    ArrayList<Transaction> aux = DataManager.getInstance().getTransactions(null, from, to);
                }
            }
        });
        return  rootView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
       // MainActivity activity = (MainActivity) getActivity();
       // activity.getmToolbar().setTitle("Main Menu");
    }



}
