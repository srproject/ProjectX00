package com.sr.projectx;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProfileActivity extends Fragment {
ImageView imageViewprofile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.profile_fragment, container, false);
        imageViewprofile=(ImageView) rootView   .findViewById(R.id.imageViewprofile);

        FloatingActionButton fabeditphoto =(FloatingActionButton)rootView.findViewById(R.id.fabeditphoto);
        fabeditphoto.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), EditprofileActivity.class);
                startActivity(intent);

            }

        });
        return rootView;

    }


    //load Image From Storage
    private void loadImageFromStorage( )
    {
        String appname =  getString(R.string.app_name);


        try {

            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Image/profile_image.png");
            if(f.exists()) {

                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

                imageViewprofile.setImageBitmap(b);
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


    @Override
    public void onResume() {
        super.onResume();

            loadImageFromStorage();


    }



}
