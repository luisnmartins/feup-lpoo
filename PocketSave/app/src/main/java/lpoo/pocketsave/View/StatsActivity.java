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
    CustomAdapter customAdapter;


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

    private class CustomAdapter extends FragmentPagerAdapter {
        private String fragments [] = {"Category Stats","Stats2"};
        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {

            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: {

                    return new StatsFragment();

                }
                case 1:{
                    return new CatStatsFragment();
                }
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }
    }
    public void setCatsChoose(View view)
    {
        DialogFragment dialog = new ChooseCategoriesDialog();
        dialog.show(getSupportFragmentManager(),"ChooseCategoriesDialog");
    }

    public void showDatePickerDialog(View v)
    {
        DialogFragment newFragment = new DatePickerFragment().newInstance(v.getId());
        newFragment.show(getSupportFragmentManager(),"datePicker");

    }


}
