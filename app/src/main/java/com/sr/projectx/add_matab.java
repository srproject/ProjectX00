package com.sr.projectx;

import android.app.DatePickerDialog;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;

public class add_matab extends AppCompatActivity {

    public GoogleMap mMap;
    ImageView imageaddmatab;
    ImageView samplemap;
    FloatingActionButton fabloc;
    int CAMERA_PIC_REQUEST = 2;
    int  TAKE_PICTURE=0;
    Camera camera;
    Bitmap bitmap;
    EditText timematab;
    EditText datematab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_matab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //addtime
        final InputMethodManager imm = (InputMethodManager)this.getSystemService(Service.INPUT_METHOD_SERVICE);

         datematab =(EditText) findViewById(R.id.datematab);
        datematab.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                 imm.hideSoftInputFromWindow(datematab.getWindowToken(), 0);

                 Calendar mcurrentTime = Calendar.getInstance();
                 int year = mcurrentTime.get(Calendar.YEAR);
                 int mon = mcurrentTime.get(Calendar.MONTH);
                 int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);


                                             DatePickerDialog datePickerDialog  ;


                                             datePickerDialog = new DatePickerDialog(add_matab.this, new DatePickerDialog.OnDateSetListener() {
                    
                     @Override
                     public void onDateSet(DatePicker datePicker, int years, int mons, int days) {
                         
                         datematab.setText(days + " / " + (mons+1) + " / " + years);

                     }
                 }, year, mon, day);
                 datePickerDialog.setTitle("Select Date");
                 datePickerDialog.show();
             }
             });

                 //addtime
                  timematab = (EditText) findViewById(R.id.timematab);
                 timematab.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         imm.hideSoftInputFromWindow(datematab.getWindowToken(), 0);

                         Calendar mcurrentTime = Calendar.getInstance();
                         int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                         int minute = mcurrentTime.get(Calendar.MINUTE);
                         TimePickerDialog mTimePicker;
                          mTimePicker = new TimePickerDialog(add_matab.this, new TimePickerDialog.OnTimeSetListener() {

                             @Override
                             public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                               if(selectedHour<10&&selectedMinute<10){

                                   timematab.setText("0"+selectedHour + ":" + 0+selectedMinute);

                               }
                               else  if(selectedHour<10 ){

                                     timematab.setText("0"+selectedHour + ":" + selectedMinute);

                                 }
                               else  if( selectedMinute<10){

                                     timematab.setText(selectedHour + ":" + 0+selectedMinute);

                                 }
                                 else {

                                   timematab.setText(selectedHour + ":" + selectedMinute);

                               }

                             }
                         }, hour, minute, true);//Yes 24 hour time
                         mTimePicker.setTitle("Select Time");
                         mTimePicker.show();
                     }
                 });
                                            // FindViewById
                                             imageaddmatab = (ImageView) findViewById(R.id.imageaddmatab);
                                             samplemap = (ImageView) findViewById(R.id.samplemap);

                                            // FindViewById//

                                            fabloc = (FloatingActionButton) findViewById(R.id.fabloc);



                                             //ClickListener

                                             imageaddmatab.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {

                                                     Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                                     setResult(RESULT_OK, cameraIntent);
                                                     startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);


                                                 }
                                             });


                                             fabloc.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {

                                                     Intent locIntent = new Intent(getApplication(), MapActivity.class);
                                                     startActivity(locIntent);


                                                 }
                                             });

                                             //ClickListener


                                         }



    //load Image From Storage
    private void loadImageFromStorage( )
    {
        String appname =  getString(R.string.app_name);


        try {

                File f = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Image/location_add.png");
            if(f.exists()) {

            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

                samplemap.setImageBitmap(b);
            }
            else {
                //Intent locIntent = new Intent(getApplication(),MapActivity.class);
               //startActivity(locIntent);


            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
    //END//load Image From Storage




    //scale Down Bitmap

    public static Bitmap scaleDownBitmap(Bitmap photo, int newHeight, Context context) {

        final float densityMultiplier = context.getResources().getDisplayMetrics().density;

        int h= (int) (newHeight*densityMultiplier);
        int w= (int) (h * photo.getWidth()/((double) photo.getHeight()));

        photo=Bitmap.createScaledBitmap(photo, w, h, true);

        return photo;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK && data != null)
        {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageaddmatab.setImageBitmap(bitmap);

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Picture NOt taken", Toast.LENGTH_LONG);
        }
    }

    // for re
    // sume activity

    @Override
    protected void onResume() {
        super.onResume();
        if( MapActivity.activityPaused()==true){
            loadImageFromStorage();
            Toast.makeText(getApplicationContext(), "Update Location", Toast.LENGTH_LONG).show();

        }
    }
}
