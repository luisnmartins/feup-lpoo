package lpoo.pocketsave.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import lpoo.pocketsave.R;

/**
 * Created by Carlos Freitas on 21/05/2017.
 */

public class CatStatsFragment extends Fragment {


    private Spinner spinner;

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
        return inflater.inflate(R.layout.catstats_fragment, container, false);
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
