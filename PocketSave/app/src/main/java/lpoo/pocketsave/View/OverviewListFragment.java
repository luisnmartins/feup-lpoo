package lpoo.pocketsave.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.blackcat.currencyedittext.CurrencyEditText;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import jp.wasabeef.recyclerview.animators.BaseItemAnimator;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;
import jp.wasabeef.recyclerview.animators.FadeInRightAnimator;
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator;
import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator;
import jp.wasabeef.recyclerview.animators.FlipInLeftYAnimator;
import jp.wasabeef.recyclerview.animators.FlipInRightYAnimator;
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;
import jp.wasabeef.recyclerview.animators.OvershootInRightAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInLeftAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInRightAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInTopAnimator;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.Logic.Transaction;
import lpoo.pocketsave.R;


public class OverviewListFragment extends Fragment implements SearchView.OnQueryTextListener{


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    enum Type {
            FadeIn(new FadeInAnimator()),
            FadeInDown(new FadeInDownAnimator()),
            FadeInUp(new FadeInUpAnimator()),
            FadeInLeft(new FadeInLeftAnimator()),
            FadeInRight(new FadeInRightAnimator()),
            Landing(new LandingAnimator()),
            ScaleIn(new ScaleInAnimator()),
            ScaleInTop(new ScaleInTopAnimator()),
            ScaleInBottom(new ScaleInBottomAnimator()),
            ScaleInLeft(new ScaleInLeftAnimator()),
            ScaleInRight(new ScaleInRightAnimator()),
            FlipInTopX(new FlipInTopXAnimator()),
            FlipInBottomX(new FlipInBottomXAnimator()),
            FlipInLeftY(new FlipInLeftYAnimator()),
            FlipInRightY(new FlipInRightYAnimator()),
            SlideInLeft(new SlideInLeftAnimator()),
            SlideInRight(new SlideInRightAnimator()),
            SlideInDown(new SlideInDownAnimator()),
            SlideInUp(new SlideInUpAnimator()),
            OvershootInRight(new OvershootInRightAnimator(1.0f)),
            OvershootInLeft(new OvershootInLeftAnimator(1.0f));

            private BaseItemAnimator mAnimator;

            Type(BaseItemAnimator animator) {
                mAnimator = animator;
            }

            public BaseItemAnimator getAnimator() {
                return mAnimator;
            }
        }


    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    //private Toolbar mToolbar;


    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RadioButton mLinearLayoutRadioButton;
    protected RadioButton mGridLayoutRadioButton;

    protected RecyclerView mRecyclerView;
    protected MyOverviewListRecyclerViewAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected ArrayList<Transaction> mDataset;
    protected String TransType;
    protected  String CatName;


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
        CatName = b.getString("CatName");
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

    public MyOverviewListRecyclerViewAdapter getmAdapter()
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
        View rootView = inflater.inflate(R.layout.fragment_overviewlist_list, container, false);
        rootView.setTag(TAG);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.OverviewList);
        if (getActivity().getIntent().getBooleanExtra("GRID", true)) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        mRecyclerView.setItemAnimator(new SlideInLeftAnimator());

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;



        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }

        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext()));


       mAdapter = new MyOverviewListRecyclerViewAdapter(getActivity(),mDataset,DATE_COMPARATOR);


        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.add(mDataset);
        // END_INCLUDE(initializeRecyclerView)

        /*getActivity().findViewById(R.id.addTrans).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.add("newly added item", 1);
            }
        });*/


        mLinearLayoutRadioButton = (RadioButton) rootView.findViewById(R.id.linear_layout_rb);
        mLinearLayoutRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecyclerViewLayoutManager(LayoutManagerType.LINEAR_LAYOUT_MANAGER);
            }
        });

        mGridLayoutRadioButton = (RadioButton) rootView.findViewById(R.id.grid_layout_rb);
        mGridLayoutRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecyclerViewLayoutManager(LayoutManagerType.GRID_LAYOUT_MANAGER);
            }
        });

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Activity activity = getActivity();
        if(activity instanceof MainActivity)
        {
            ((MainActivity) activity).getmToolbar().setTitle("Main Menu");
            ((MainActivity)activity).getmOptionsMenu().findItem(R.id.action_search).setVisible(false);
            ((MainActivity)activity).getmOptionsMenu().setGroupVisible(R.id.overGroup,false);
        }



    }


    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {

        if(CatName != null)
        {
            mDataset = DataManager.getInstance().getTransactionsBetweenDates("Category",CatName,getArguments().getString("FromDate"),getArguments().getString("ToDate"),true);
        }
        mDataset = DataManager.getInstance().getTypeTransaction(TransType);

    }

    public void filterIn(String query)
    {
        final List<Transaction> filteredModelList = filter(mDataset, query);
        mAdapter.replaceAll(filteredModelList);
    }

    private static ArrayList<Transaction> filter(ArrayList<Transaction> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final ArrayList<Transaction> filteredModelList = new ArrayList<>();
        for (Transaction model : models) {
            final String text = Double.toString(model.getValue());
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }






}
