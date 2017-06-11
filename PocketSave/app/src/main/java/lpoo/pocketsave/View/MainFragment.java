package lpoo.pocketsave.View;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.Logic.Date;
import lpoo.pocketsave.Logic.Suggestions;
import lpoo.pocketsave.Logic.Transaction;
import lpoo.pocketsave.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    Thread t;
    private TextView suggestionsBox;
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
        suggestionsBox = (TextView) rootView.findViewById(R.id.suggestionsText);
        suggestionsBox.setText("");
        createThread();
        ((TextView)rootView.findViewById(R.id.BalanceText)).setText(getCurrentBalance().toString());
        return rootView;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity activity = (MainActivity) getActivity();
        activity.getmToolbar().setTitle("Main Menu");
        t.interrupt();
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
                    b.putBoolean("isToAdd",true);
                    transactionIntent.putExtras(b);
                    getActivity().startActivity(transactionIntent);



                }
            }).show();
        }



    }

    Double getCurrentBalance()
    {
        Date d = new Date();
        Double balance = 0.0;
        Double spent = 0.0;
        HashMap<String,Double> hashMap =DataManager.getInstance().getTotalSpentValues("Type",null,d.getInitialDate(true,"current"),d.getInitialDate(false,"current"),true);
       Log.d("CNEAS", "DATA DE BALANCE: " + d.getInitialDate(true,"current"));
        Log.d("CNEAS", "DATA DE FIM BALANCE" + d.getInitialDate(false,"current"));
        if(hashMap != null)
        {
            for(HashMap.Entry<String,Double> it : hashMap.entrySet())
            {
              if(it.getKey().equals("Fixed Expense") || it.getKey().equals("Variable Expense"))
              {
                  balance -= it.getValue();
              }else balance += it.getValue();
            }
        }
        return balance;

    }

    void createThread()
    {
        final Suggestions sug = new Suggestions();

         t = new Thread(){
            int sug_index = 0;
            @Override
            public void run() {
                try{
                    while(!isInterrupted()){
                        Thread.sleep(10000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(sug_index == 0)
                                {
                                   ArrayList<String> onLimit =  sug.onlimitCategory();
                                    if(onLimit == null || onLimit.isEmpty())
                                    {
                                        sug_index++;
                                        return;
                                    }
                                    String msg = "Beware that you are almost reaching your expected values too soon in the following categories: ";
                                    for(int i = 0; i < onLimit.size();i++)
                                    {


                                        if(i != onLimit.size()-1)
                                        {
                                            msg += onLimit.get(i) + ", ";
                                        }else  msg += onLimit.get(i);
                                    }
                                    suggestionsBox.setText(msg);
                                    sug_index++;

                                }else if(sug_index == 1)
                                {
                                    ArrayList<String>  toHigh = sug.compareCatValuesBefore();
                                    if(toHigh == null || toHigh.isEmpty())
                                    {
                                        sug_index++;
                                        return ;
                                    }
                                    String msg = "Remember that you spent to much last month in the following categories: ";
                                    for(int i = 0; i < toHigh.size();i++)
                                    {


                                        if(i != toHigh.size()-1)
                                        {
                                            msg += toHigh.get(i) + ", ";
                                        }else  msg += toHigh.get(i);
                                    }
                                    suggestionsBox.setText(msg);
                                    sug_index++;
                                }else if(sug_index == 2)
                                {
                                    ArrayList<String> toManyCash = sug.limitCashMethodCategory();
                                    if(toManyCash == null || toManyCash.isEmpty())
                                    {
                                        sug_index = 0;
                                        return;
                                    }
                                    String msg = "I think your making too much small buys in the following categories: ";
                                    for(int i = 0; i < toManyCash.size();i++)
                                    {
                                        if(i != toManyCash.size()-1)
                                        {
                                            msg += toManyCash.get(i) + ", ";
                                        }else  msg += toManyCash.get(i);
                                    }
                                    suggestionsBox.setText(msg);
                                    sug_index = 0;
                                }

                            }
                            });
                    }
                } catch (InterruptedException e) {


                }
            }
            public void cancel()
            {
                interrupt();
            }
        };

        t.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        t.interrupt();
    }
}
