package lpoo.pocketsave.View;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.util.Calendar;
import java.util.HashMap;

import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.Logic.Date;
import lpoo.pocketsave.Logic.Transaction;
import lpoo.pocketsave.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddCategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCategoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Button save,colorButton;
    private EditText catEstimaed;
    private EditText catTitle;
    private Boolean isEdit = false;
    private int  color = Color.BLUE;


    private OnFragmentInteractionListener mListener;

    public AddCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCategoryFragment newInstance(String param1, String param2) {
        AddCategoryFragment fragment = new AddCategoryFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);
        Bundle b = getArguments();
        if(b != null)
            isEdit = b.getBoolean("isEdit");


        colorButton = (Button) view.findViewById(R.id.colorbutton);
        colorButton.setBackgroundColor(Color.BLUE);
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getColorPicker();
            }
        });
        catEstimaed = (EditText) view.findViewById(R.id.CatEstimated);
        catTitle = (EditText) view.findViewById(R.id.CatTitle);

        if(isEdit)
        {

            Date d = new Date();
            String d1 = d.getInitialDate(true,"current");
            String d2 = d.getInitialDate(false,"currentDate");

            String catName = b.getString("catName");
            Category category = DataManager.getInstance().getCategory(catName,null,"Variable Expense").get(0);
            catTitle.setText(category.getTitle());
            colorButton.setBackgroundColor(category.getColor());
            color = category.getColor();
            HashMap<String,Double> aux = DataManager.getInstance().getTotalSpentValues("Category",category.getTitle(),d1,d2,false);
            Double estimated = aux.get(category.getTitle());
            catEstimaed.setText(Double.toString(estimated));
            catEstimaed.setEnabled(false);


        }

        Boolean hide = false;
        if(getArguments() != null)
        {
            hide = getArguments().getBoolean("hideEstimated");
        }

        if(hide)
        {
            catEstimaed.setEnabled(false);
            catEstimaed.setVisibility(View.INVISIBLE);
            view.findViewById(R.id.catEstimatedText).setVisibility(View.INVISIBLE);
        }



        save = (Button) view.findViewById(R.id.AddCat);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(catTitle.getText().toString().equals("") || (catEstimaed.getText().toString().equals("") && catEstimaed.isEnabled()))
                {
                    return;
                }
                Date d = new Date();
                String date = d.getInitialDate(true,"current");

                Category newCategory = new Category(catTitle.getText().toString(), DataManager.getInstance().addGetType("Get", "Variable Expense"), false, color);
                if(isEdit){
                    String catName = getArguments().getString("catName");
                    Category aux = DataManager.getInstance().getCategory(catName,false,"Variable Expense").get(0);
                  //  Log.d("CENAS", );

                    //aux.setTitle(catTitle.getText().toString());
                    aux.setColor(color);
                    DataManager.getInstance().addUpdateCategory("Update", aux);

                }else{

                    DataManager.getInstance().addUpdateCategory("Add", newCategory);

                }

                if(catEstimaed.isEnabled())
                {
                    Category cat_id = DataManager.getInstance().getCategory(catTitle.getText().toString(),null,null).get(0);
                    Double estimated_value = Double.parseDouble(catEstimaed.getText().toString());
                    Transaction newTransaction = new Transaction(estimated_value, date, cat_id.getID(), false, false);
                    DataManager.getInstance().addUpdateTransaction("Add",newTransaction);
                }

                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return  view;
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


        Activity activity = getActivity();
        if(activity instanceof MainActivity)
        {
            ((MainActivity) activity).getmToolbar().setTitle("Main Menu");
        }

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


    public void getColorPicker()
    {


        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle("Choose color")
                .initialColor(Color.BLUE)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        //toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        changeBackgroundColor(selectedColor);
                        color = selectedColor;

                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();

    }

    public void changeBackgroundColor(int color)
    {

        this.colorButton.setBackgroundColor(color);
    }
}
