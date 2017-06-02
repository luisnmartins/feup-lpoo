package lpoo.pocketsave.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.BoringLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.R;

public class TransactionActivity extends AppCompatActivity {
    EditText  description;
    EditText value;
    TextView date;
    static final int CAM_REQUEST = 1;
    String category,mCurrentPhotoPath;
    Button savebtn;
    ImageView image;
    RadioGroup checkPayment;
    private boolean isCash = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);




        showDialogDate();

        checkPayment = (RadioGroup) findViewById(R.id.radioGroupPaymentMethod);
        value = (EditText) findViewById(R.id.ValueText);
        description = (EditText) findViewById(R.id.DescriptionText);
        savebtn = (Button) findViewById(R.id.Savebtn);

        image = (ImageView) findViewById(R.id.receiptImage);

        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }}
        );
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {

                    return false;
                }
                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // Ensure that there's a camera activity to handle the intent
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        // Create the File where the photo should go
                        File photoFile = null;
                        try {
                            photoFile = createImageFile();
                        } catch (IOException ex) {
                            // Error occurred while creating the File
                        }
                        // Continue only if the File was successfully created
                        if (photoFile != null) {
                            Uri photoURI = FileProvider.getUriForFile(getContext(),
                                    "com.example.android.fileprovider",
                                    photoFile);
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            startActivityForResult(takePictureIntent, CAM_REQUEST);
                        }
                    }
                }
                return true;
            }
        });


        AddData();
        checkRadioGroupChange();


        if(!checkArguments())
        {
            toolbar.setTitle("Transaction - " + getIntent().getExtras().getString("Category"));
            setSupportActionBar(toolbar);

        }


    }


    public Context getContext() {
        return this;
    }



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = "1";//new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        Log.d("DIRETORIO: ", storageDir.getAbsolutePath());
        File image = new File(storageDir.getAbsolutePath(), imageFileName+".jpg");
        /*File image = File.createTempFile(
                imageFileName,  /* prefix */
        //".jpg",         /* suffix */
        //storageDir      /* directory */
        //);

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.d("Teste: ", mCurrentPhotoPath);
        return image;
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {

            image.setImageDrawable(Drawable.createFromPath(mCurrentPhotoPath));
            Toast.makeText(this, "Call gallery", Toast.LENGTH_SHORT);
            //galleryAddPic();
        }
        else{
            Toast.makeText(this, "Error on result", Toast.LENGTH_SHORT);
        }
    }



    public void showDialogDate(){
        date = (TextView) findViewById(R.id.DateView);

        date.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        DialogFragment newFragment = new DatePickerFragment().newInstance(v.getId());
                        newFragment.show(getSupportFragmentManager(),"datePicker");

                    }
                }
        );
    }


    public  void AddData() {
        savebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if(value.getText().toString().equals(""))
                        {

                            value.setError("You must insert a value");
                            value.requestFocus();
                            return ;


                        }
                        if( date.getText().toString().equals("Choose Date"))
                        {
                            date.setError("You must choose a date");
                            date.requestFocus();
                            return;
                        }

                        String dateString = date.getText().toString();
                        String desc = description.getText().toString();
                        Double valueDouble = Double.parseDouble(value.getText().toString());
                        System.out.println("value" + valueDouble);
                        Bundle b = getIntent().getExtras();
                        long id = 0;
                        if(b != null)
                          id = b.getLong("CatID");
                        Log.d("CNEAS","MANDO PATH: " + mCurrentPhotoPath);
                        DataManager.getInstance().addUpdateTransaction("Add",-1,valueDouble,dateString,desc,id,true,mCurrentPhotoPath,isCash);
                        finish();
                        //TODO: change done value

                    }
                }
        );
    }

    public void checkRadioGroupChange()
    {
        checkPayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case (R.id.creditcard): {

                        isCash = false;
                        break;
                    }
                    case (R.id.money):
                    {
                        isCash = true;
                        break;
                    }
                }
            }
        });
    }

    boolean checkArguments()
    {

        Bundle b = getIntent().getExtras();
        Boolean isAdd = b.getBoolean("isToAdd");
        if(isAdd)
            return false;
        else{
            value.setEnabled(false);
            value.setText(Double.toString(b.getDouble("value")));
            date.setEnabled(false);
            date.setText(b.getString("date"));
            description.setEnabled(false);
            description.setText(b.getString("description"));
            checkPayment.setEnabled(false);
            savebtn.setEnabled(false);
            image.setImageDrawable(Drawable.createFromPath(b.getString("image")));
            image.setEnabled(false);
            Log.d("cenas","PATH IMAGEM" + b.getString("image"));
            return true;

        }
    }

}
