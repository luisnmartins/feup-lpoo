package lpoo.pocketsave.View;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

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

    public void initializeAllButtons(View view)
    {
        initializeButton(view,R.id.Cat1btn);
        initializeButton(view,R.id.Cat2btn);
        initializeButton(view,R.id.Cat3btn);
        initializeButton(view,R.id.Cat4btn);
        initializeButton(view,R.id.Cat5btn);

    }

}
