package lpoo.pocketsave.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.R;

public class IntroActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        startData();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (DataManager.getInstance().getPreferences() == -1){

                    Intent mainIntent = new Intent(IntroActivity.this, LoginActivity.class);
                    IntroActivity.this.startActivity(mainIntent);
                    IntroActivity.this.finish();
                }else{

                    if(DataManager.getInstance().verifyMonth()){
                        goMonth();
                    }
                    else
                    {

                        Intent mainIntent = new Intent(IntroActivity.this, MainActivity.class);
                        IntroActivity.this.startActivity(mainIntent);
                        Toast.makeText(IntroActivity.this,"User logged in",Toast.LENGTH_LONG).show();
                        finish();
                    }

                }
            }
        }, SPLASH_TIME_OUT);


    }

    public void startData(){
        DataManager.startDB(IntroActivity.this);
        DataManager.getInstance().setContext(IntroActivity.this);
        DataManager.getInstance().initPreferences();
    }

    public void goMonth()
    {

        Intent monthIntent = new Intent(IntroActivity.this,Month.class);
        Bundle b = new Bundle();
        b.putBoolean("isFirst",true);
        monthIntent.putExtras(b);
        IntroActivity.this.startActivity(monthIntent);
        finish();
        Toast.makeText(IntroActivity.this,"User logged in",Toast.LENGTH_LONG).show();
    }
}