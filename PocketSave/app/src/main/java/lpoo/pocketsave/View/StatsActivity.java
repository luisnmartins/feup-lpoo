package lpoo.pocketsave.View;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import lpoo.pocketsave.R;

public class StatsActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment frag = new StatsFragment();
        frag.setRetainInstance(true);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.linear_stats,frag,"stats");
        fragmentTransaction.commit();

        /*customAdapter = new CustomAdapter(getSupportFragmentManager(),getApplicationContext());

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(customAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setTabTextColors(
                ContextCompat.getColor(getBaseContext(), android.R.color.white),
                ContextCompat.getColor(getBaseContext(), android.R.color.white)
        );
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }
        });*/
    }


    public void showDatePickerDialog(View v)
    {
        DialogFragment newFragment = new DatePickerFragment().newInstance(v.getId());
        newFragment.show(getSupportFragmentManager(),"datePicker");

    }


}
