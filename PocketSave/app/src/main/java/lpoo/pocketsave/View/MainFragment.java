package lpoo.pocketsave.View;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);
        initializeAllButtons(rootView);
        return rootView;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity activity = (MainActivity) getActivity();
        activity.getmToolbar().setTitle("Main Menu");
    }

    public void initializeButton(final View view,int buttonID)
    {
        Button btn = (Button) view.findViewById(buttonID);
        final Animation myAnim = AnimationUtils.loadAnimation(getActivity(),R.anim.zoom_in);
        btn.setAnimation(myAnim);
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    v.startAnimation(myAnim);

                }
                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.NewTransaction(v);
                }
                return true;
            }
        });


    }

    public void initializeMoreButton(View view, int id)
    {
        Button btn = (Button) view.findViewById(id);
        final Animation myAnim = AnimationUtils.loadAnimation(getActivity(),R.anim.zoom_in);
        btn.setAnimation(myAnim);
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    v.startAnimation(myAnim);

                }
                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    startDialogOtherCategories();
                }
                return true;
            }
        });
    }

    public void initializeAllButtons(View view)
    {
        initializeButton(view,R.id.Cat1btn);
        initializeButton(view,R.id.Cat2btn);
        initializeButton(view,R.id.Cat3btn);
        initializeButton(view,R.id.Cat4btn);
        initializeButton(view,R.id.Cat5btn);
        initializeMoreButton(view,R.id.Morebtn);

    }

    public  void startDialogOtherCategories()
    {
        ArrayList<Category> cats = DataManager.getInstance().getCategory("mainMenuCategories",false,"Variable Expense");
        if(cats != null)
        {
            final ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(),cats);
            new AlertDialog.Builder(getActivity()).setTitle("Other Categories").setAdapter(adapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), "Item Selected: " + which, Toast.LENGTH_SHORT).show();
                    Category aux = (Category) adapter.getItem(which);
                    Intent transactionIntent = new Intent(getActivity(), TransactionActivity.class);
                    Bundle b = new Bundle();
                    b.putLong("CatID",aux.getID());
                    b.putString("Category",aux.getTitle());
                    transactionIntent.putExtras(b);
                    getActivity().startActivity(transactionIntent);



                }
            }).show();
        }



    }

}
