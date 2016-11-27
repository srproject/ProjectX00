package com.sr.projectx;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback;

import java.io.FileOutputStream;

import static com.sr.projectx.R.id.fabeditphoto;

public class add_matab extends AppCompatActivity {

    public GoogleMap mMap;
    ImageView imageaddmatab;
    ImageView samplemap;
    FloatingActionButton fabloc;
    int CAMERA_PIC_REQUEST = 2;
    int  TAKE_PICTURE=0;
    Camera camera;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_matab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageaddmatab=(ImageView) findViewById(R.id.imageaddmatab);
        samplemap  =(ImageView) findViewById(R.id.samplemap);
        fabloc=(FloatingActionButton) findViewById(R.id.fabloc);
        imageaddmatab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                setResult(RESULT_OK,cameraIntent);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);


            }
        });


        fabloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent locIntent = new Intent(getApplication(),MapActivity.class);
                 startActivity(locIntent);



            }
        });
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            bitmap = (Bitmap) intent.getParcelableExtra("sample_name");
            samplemap.setImageBitmap(bitmap);

        }





    }

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
}
