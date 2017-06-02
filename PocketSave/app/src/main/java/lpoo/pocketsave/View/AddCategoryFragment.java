package lpoo.pocketsave.View;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import lpoo.pocketsave.Logic.DataManager;
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
    private ImageButton catIcon;
    private int  color = 9999999;


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

        colorButton = (Button) view.findViewById(R.id.colorbutton);
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getColorPicker();
            }
        });
        catEstimaed = (EditText) view.findViewById(R.id.CatEstimated);
        catTitle = (EditText) view.findViewById(R.id.CatTitle);
        catIcon = (ImageButton) view.findViewById(R.id.CatIcon);


        save = (Button) view.findViewById(R.id.AddCat);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(catTitle.getText().toString().equals("") || catEstimaed.getText().toString().equals(""))
                {
                    return;
                }
                DataManager.getInstance().addUpdateCategory("Add",catTitle.getText().toString(),"Variable Expense",false);
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = 1;
                String date = year + "-" + month + "-" + day;
                Double estimated_value = Double.parseDouble(catEstimaed.getText().toString());
                long cat_id = DataManager.getInstance().getCategory(catTitle.getText().toString(),null,null).get(0).getID();

                DataManager.getInstance().addUpdateTransaction("Add",-1,estimated_value,date,"estimated",cat_id,false,null,false);

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
                .initialColor(999999999)
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
