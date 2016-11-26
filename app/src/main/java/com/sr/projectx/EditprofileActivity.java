package com.sr.projectx;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class EditprofileActivity extends Activity {
    FloatingActionButton fabeditphoto;
    ImageView imageVieweditpro;
    int CAMERA_PIC_REQUEST = 2;
    int  TAKE_PICTURE=0;
    Camera camera;
    Bitmap bitmap;
    Button editpro_save_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        editpro_save_button=(Button)findViewById(R.id.editpro_save_button);
        imageVieweditpro=(ImageView) findViewById(R.id.imageVieweditpro);
        fabeditphoto =(FloatingActionButton) findViewById(R.id.fabeditpro);


        fabeditphoto.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v)

            {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                setResult(RESULT_OK,cameraIntent);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);

            }

        });


    }

    public boolean hascam(){

        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK && data != null)
        {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageVieweditpro.setImageBitmap(bitmap);

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Picture NOt taken", Toast.LENGTH_LONG);
        }
    }
}

