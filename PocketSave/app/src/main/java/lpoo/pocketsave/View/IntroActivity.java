package lpoo.pocketsave.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.R;

public class IntroActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        DataManager.startDB(IntroActivity.this);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent mainIntent = new Intent(IntroActivity.this, LoginActivity.class);
                IntroActivity.this.startActivity(mainIntent);
                IntroActivity.this.finish();
            }
        }, SPLASH_TIME_OUT);
    }
}