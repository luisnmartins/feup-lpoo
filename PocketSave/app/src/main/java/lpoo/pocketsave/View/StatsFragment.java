package lpoo.pocketsave.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.Logic.Transaction;
import lpoo.pocketsave.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String TAG = "StatsFragment";
    static final int DIALOG_ID =0;
    private String from;
    private String to;
    int year_x, month_x, day_x;
    private ImageButton setStats;
    private PieChart chart;
    private HashMap<String,Double> each_cat_spent;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        java.util.Calendar cal = java.util.Calendar.getInstance();
        year_x = cal.get(java.util.Calendar.YEAR);
        month_x = cal.get(java.util.Calendar.MONTH);
        day_x = cal.get(java.util.Calendar.DAY_OF_MONTH);



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_stats, container, false);
        setStats = (ImageButton) view.findViewById(R.id.setStats2);
        setStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 from = ((TextView) view.findViewById(R.id.From)).getText().toString();
                 to = ((TextView) view.findViewById(R.id.To)).getText().toString();
                if(!from.equals("Choose Date")  && !to.equals("Choose Date")) {
                    Log.d(TAG,"FROM: " + from);
                    Log.d(TAG,"TO: " + to);
                    each_cat_spent = DataManager.getInstance().getTotalSpentValues("Category",null,from,to,true);
                    if(each_cat_spent != null)
                    {
                        Log.d(TAG,"TOTALSPENTVALUES TRUE");
                        addDataSet();

                    }
                }
            }
        });

        startChart(view);




        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void startChart(View view)
    {
        chart = (PieChart) view.findViewById(R.id.idPieChart);

        Description a = new Description();
        a.setText("Categories Values");
        chart.setDescription(a);
        chart.setRotationEnabled(true);
        chart.setHoleRadius(25f);
        chart.setTransparentCircleAlpha(0);
        chart.setCenterText("My Stats");
        chart.setCenterTextSize(10);
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

                float x = h.getX();
                //int x = h.getDataIndex();
                Log.d(TAG,"GETDATASETINDEX " + x);

                ArrayList<String> arrayList = getPieCategories();
               String cat_name =  arrayList.get((int) x);

                if(cat_name != null)
                {
                    destroyFragment();
                    OverviewListFragment frag = new OverviewListFragment();
                    Bundle b = new Bundle();
                    b.putString("CatName",cat_name);
                    b.putString("FromDate",from);
                    b.putString("ToDate",to);
                    frag.setArguments(b);
                    startOverview(frag,"over");


                }

            }

            @Override
            public void onNothingSelected() {

            }
        });
    }


    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = getPieCategoriesValue();
        ArrayList<String> xEntrys =getPieCategories();



        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "User Categories");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        for(int i = 0; i < xEntrys.size();i++)
        {
            colors.add(DataManager.getInstance().getCategory(xEntrys.get(i),null,null).get(0).getColor());

        }
       /* colors.add(Color.rgb(102, 178, 255));
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);*/

        pieDataSet.setColors(colors);
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        chart.setData(pieData);
        chart.invalidate();
    }

    public ArrayList<String> getPieCategories(){

        ArrayList<String> ret = new ArrayList<String>();

        for(HashMap.Entry<String,Double> it : each_cat_spent.entrySet())
        {
            Log.d(TAG,"CAT HASH:" + it.getValue() + "AND " + it.getKey());
            ret.add(it.getKey());
        }
        return ret;
    }

    public ArrayList<PieEntry> getPieCategoriesValue(){
        ArrayList<PieEntry> ret = new ArrayList<PieEntry>();
        int pos = 0;
        for(HashMap.Entry<String,Double> it : each_cat_spent.entrySet())
        {
            DecimalFormat dc = new DecimalFormat("#");
            ret.add(new PieEntry(Float.parseFloat(dc.format(it.getValue())),pos));
            pos++;
        }
        return ret;

    }

    public void startOverview(Fragment fragment,String tag)
    {
        if(getFragmentManager().findFragmentByTag(tag) == null)
        {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.statsContainer, fragment,tag);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }


    public  void destroyFragment()
    {
        OverviewListFragment fragment = (OverviewListFragment) getFragmentManager().findFragmentByTag("over");
        if(fragment != null)
        {
            getFragmentManager().popBackStack();
        }
    }
}
