package lpoo.pocketsave.View;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
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
import lpoo.pocketsave.Logic.DatabaseHelper;
import lpoo.pocketsave.Logic.DatabaseSingleton;
import lpoo.pocketsave.R;
import lpoo.pocketsave.View.dummy.DummyContent;
import lpoo.pocketsave.View.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OverviewListFragment extends Fragment {




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
    protected List<String> mDataset;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //getActivity().s(mToolbar);
        // Initialize dataset, this data would usually come from a local content provider or
        // remote server
        initDataset();
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


       mAdapter = new MyOverviewListRecyclerViewAdapter(getActivity(),mDataset);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
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
        MainActivity activity = (MainActivity) getActivity();
        //activity.resetButtons(true);
        activity.getmToolbar().setTitle("Main Menu");
        activity.getmOptionsMenu().findItem(R.id.addTrans).setVisible(false);


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
        mDataset = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            mDataset.add(i,"This is element #" + i);
        }
    }

    private void populateListView(){

        Cursor cursor = DatabaseSingleton.getInstance().getDB().getAllData();

        //startManagingCursor(cursor);


        String[] fromFieldNames = new String[]
                {DatabaseHelper.CAT_TITLE,DatabaseHelper.TRANS_VALUE, DatabaseHelper.TRANS_DATE};
        int[] toViewIDs = new int[]
                {R.id.listCat,    R.id.listValue,     R.id.listDate};



        // Create adapter to may columns of the DB onto elemesnt in the UI.
        SimpleCursorAdapter myCursorAdapter =
                new SimpleCursorAdapter(
                        getActivity(),		// Context
                        R.layout.fragment_overviewlist,	// Row layout template
                        cursor,					// cursor (set of DB records to map)
                        fromFieldNames,			// DB Column names
                        toViewIDs				// View IDs to put information in
                );

        System.out.println("Try  fill list");
        RecyclerView list = (RecyclerView) getActivity().findViewById(R.id.OverviewList);
       // list.setAdapter(myCursorAdapter);

    }

    private void registerListClickCallback() {
        ListView list = (ListView) getActivity().findViewById(R.id.ListViewDB);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long idInDB) {



                displayToastForId(idInDB);
            }
        });
    }

    private void displayToastForId(long idInDB) {
        Cursor cursor = DatabaseSingleton.getInstance().getDB().getTransaction(Long.toString(idInDB));
        if(cursor != null) {
            if (cursor.moveToFirst()) {
                String idDB = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TRANS_VALUE));

                String message = "ID: " + idDB + "\n"
                        + "Name: " + name;
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
            cursor.close();
        }
        else
            System.out.println("Error getting Transaction\n");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();

        if(id == R.id.addTrans)
        {
            mAdapter.add("newly added item", 1);
            mRecyclerView.setAdapter(mAdapter);
        }

        return true;
    }


}
