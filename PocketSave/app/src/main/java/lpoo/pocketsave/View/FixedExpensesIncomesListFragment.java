package lpoo.pocketsave.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Comparator;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.Logic.Transaction;
import lpoo.pocketsave.R;

/**
 * Created by Carlos Freitas on 31/05/2017.
 */

public class FixedExpensesIncomesListFragment extends Fragment {

    protected RecyclerView mRecyclerView;
    protected IncomeExpensesRecyclerViewAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected ArrayList<Transaction> mDataset;
    private static final String TAG = "RecyclerViewFragmentSettings";
    private String TransType;



    private static final Comparator<Transaction> VALUE_COMPARATOR = new Comparator<Transaction>() {
        @Override
        public int compare(Transaction a, Transaction b) {
            return a.compareTo(b);
        }
    };

    private static final Comparator<Transaction> DATE_COMPARATOR = new Comparator<Transaction>() {
        @Override
        public int compare(Transaction o1, Transaction o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    };

    private static final Comparator<Transaction> CAT_COMPARATOR = new Comparator<Transaction>() {
        @Override
        public int compare(Transaction o1, Transaction o2) {
            return Long.compare(o1.getCatID(),o2.getCatID());
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        TransType = b.getString("TransType");
        initDataset();

    }

    public  Comparator<Transaction> getDateComparator()
    {
        return DATE_COMPARATOR;
    }

    public  Comparator<Transaction> getValueComparator()
    {
        return VALUE_COMPARATOR;
    }

    public  Comparator<Transaction> getCatComparator()
    {
        return CAT_COMPARATOR;
    }

    public IncomeExpensesRecyclerViewAdapter getmAdapter()
    {
        return  mAdapter;
    }

    public RecyclerView getmRecyclerView()
    {
        return mRecyclerView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settingslist_list, container, false);
        rootView.setTag(TAG);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.SettingsList);
        if (getActivity().getIntent().getBooleanExtra("GRID", true)) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setItemAnimator(new SlideInLeftAnimator());
        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mAdapter = new IncomeExpensesRecyclerViewAdapter(getActivity(),mDataset,DATE_COMPARATOR);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.add(mDataset);


        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }




    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {

        mDataset = DataManager.getInstance().getTypeTransaction(TransType);

    }


}
