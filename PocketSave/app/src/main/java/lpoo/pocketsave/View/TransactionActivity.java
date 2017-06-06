package lpoo.pocketsave.View;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
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
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
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
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    PointF startPoint = new PointF();
    PointF midPoint = new PointF();
    float oldDist = 1f;
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;
    private Boolean istoEdit = false;
    private ImageButton zoomImage;




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);




        showDialogDate();
        zoomImage = (ImageButton) findViewById(R.id.zoomImage);
        zoomImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage();
            }
        });
        checkPayment = (RadioGroup) findViewById(R.id.radioGroupPaymentMethod);
        value = (EditText) findViewById(R.id.ValueText);
        description = (EditText) findViewById(R.id.DescriptionText);
        savebtn = (Button) findViewById(R.id.Savebtn);

        image = (ImageView) findViewById(R.id.receiptImage);

        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ImageView view = (ImageView)v;

                switch (event.getAction() & MotionEvent.ACTION_MASK)
                {
                    case MotionEvent.ACTION_DOWN:
                        savedMatrix.set(matrix);
                        startPoint.set(event.getX(),event.getY());
                        mode = DRAG;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(event);
                        if(oldDist > 10f)
                        {
                            savedMatrix.set(matrix);
                            midPoint(midPoint,event);
                            mode = ZOOM;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
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
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = NONE;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(mode == DRAG)
                        {
                            matrix.set(savedMatrix);
                            matrix.postTranslate(event.getX() - startPoint.x,event.getY() - startPoint.y);

                        }else if (mode == ZOOM)
                        {
                            float newDist = spacing(event);
                            if(newDist >10f)
                            {
                                matrix.set(savedMatrix);
                                float scale = newDist/oldDist;
                                matrix.postScale(scale, scale, midPoint.x, midPoint.y);
                            }
                        }
                        break;
                }
                view.setImageMatrix(matrix);
                return  true;
            }
        });


        AddData();
        checkRadioGroupChange();


        if(!checkArguments())
        {
            toolbar.setTitle("Transaction - " + getIntent().getExtras().getString("Category"));
            setSupportActionBar(toolbar);

        }else {
            toolbar.setTitle("My Transaction");
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
                        long idTrans = 0;
                        idTrans = b.getLong("myID");
                        if(b != null)
                          id = b.getLong("CatID");
                        if(istoEdit)
                        {
                            Log.d("OLA","O ID QUE RECEBE" + idTrans);
                           boolean aux =  DataManager.getInstance().addUpdateTransaction("Update",idTrans,valueDouble,dateString,desc,id,true,mCurrentPhotoPath,isCash);
                            Log.d("OLA","O DE UPDATE TRANSACTION" + aux);
                        }
                        else
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
        istoEdit= b.getBoolean("isToEdit");
        if(isAdd)
            return false;
        else{
            if(istoEdit == true)
            {
                date.setText(b.getString("date"));
                value.setText(Double.toString(b.getDouble("value")));
                description.setText(b.getString("description"));
                image.setImageDrawable(Drawable.createFromPath(b.getString("image")));
                int cash = b.getInt("isCash");
                if(cash == 1)
                    isCash = true;
                else isCash = false;
                return true;
            }

            value.setEnabled(false);
            value.setText(Double.toString(b.getDouble("value")));
            date.setEnabled(false);
            date.setText(b.getString("date"));
            description.setEnabled(false);
            description.setText(b.getString("description"));
            checkPayment.setEnabled(false);
            savebtn.setEnabled(false);
            savebtn.setVisibility(View.INVISIBLE);
            int cash = b.getInt("isCash");
            if(cash == 1)
                isCash = true;
            else isCash = false;
            this.mCurrentPhotoPath = b.getString("image");
            image.setImageDrawable(Drawable.createFromPath(b.getString("image")));
            image.setEnabled(false);
            Log.d("cenas","PATH IMAGEM" + b.getString("image"));
            return true;

        }
    }


    @SuppressLint("FloatMath")
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }


    public void showImage()
    {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });

        ImageView img = new ImageView(this);
        img.setImageDrawable(Drawable.createFromPath(mCurrentPhotoPath));
        builder.addContentView(img,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();

    }


}
