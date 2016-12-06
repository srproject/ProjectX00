package com.sr.projectx;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    public GoogleMap mMap;
    private final LatLng HAMBURG = new LatLng(30.039661, 31.265244);
    private final LatLng KIEL = new LatLng(30.039661, 31.265244);
    private final LatLng KIEL2 = new LatLng(30.039561, 31.265244);
    private final LatLng KIEL3 = new LatLng(30.039461, 31.265244);
    private final LatLng KIEL4 = new LatLng(30.039361, 31.265244);
    private final LatLng KIEL5 = new LatLng(30.039261, 31.265244);


    Bitmap bitmap;
    Button set_button;
    ImageView samplemap;
    ImageView imageloclocloc;
    Bitmap bbb;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_fragment);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        samplemap = (ImageView) findViewById(R.id.samplemap);
        imageloclocloc = (ImageView) findViewById(R.id.imageloclocloc);
        set_button = (Button) findViewById(R.id.set_button);

        set_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CaptureMapScreen();
                imageloclocloc.setImageBitmap(bitmap);

            }
        });

        set_button.setOnLongClickListener(new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onLongClick(View view) {

                if (bitmap != null) {
                    try {
                        saveImage(bitmap);
                        finish();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    bitmap = null;

                    Toast.makeText(getApplicationContext(), "No Image Click Again", Toast.LENGTH_SHORT).show();

                }

                return false;
            }
        });

    }



        //saveImage

    private void saveImage(Bitmap bitmap) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 40, bytes);
        String appname = getString(R.string.app_name);
        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Image/");
        if (!folder.exists()) {
            folder.mkdirs();
            Toast.makeText(getApplicationContext(), "Folder Maked", Toast.LENGTH_SHORT).show();

        } else {
            File f2 = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Image/location_add.png");


            f2.createNewFile();
            FileOutputStream fo = new FileOutputStream(f2);
            fo.write(bytes.toByteArray());
            fo.close();
            Toast.makeText(getApplicationContext(), "Image Saved", Toast.LENGTH_LONG).show();


        }
    }
    //END//saveImage




    public void CaptureMapScreen() {


        GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback() {


            @Override
            public void onSnapshotReady(Bitmap snapshot) {
                // TODO Auto-generated method stub
                bitmap = snapshot;
                try {
                    //saveImage(bitmap);
                    //  Toast.makeText(getApplicationContext(), "Image Saved", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        };

        mMap.snapshot(callback);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        setUpMap();
    }

    private void setUpMap() {

        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.getUiSettings().setMapToolbarEnabled(false);


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMapToolbarEnabled(false);


         Marker kiel = mMap.addMarker(new MarkerOptions()
                .position(KIEL)
                .title("Matab")
                .snippet("Sameh Say: Kiel is cool"+"   Sr Say: Kiel is cool"+"   Sr Say: Kiel is cool"+"   Sr Say: Kiel is cool"+"   Sr Say: Kiel is cool"+"   Sr Say: Kiel is cool")
                  .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_warning_black_48dp)));
        Marker kiel2 = mMap.addMarker(new MarkerOptions()
                .position(KIEL2)
                .title("fire")
                .snippet("Kiel is cool")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_warning_black_48dp)));
        Marker kiel3 = mMap.addMarker(new MarkerOptions()
                .position(KIEL3)
                .title("fire on Azhar")
                .snippet("Kiel is cool")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_warning_black_48dp)));
        Marker kiel4 = mMap.addMarker(new MarkerOptions()
                .position(KIEL4)
                .title("fire4")
                .snippet("Kiel is cool")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_warning_black_48dp)));
        Marker kiel5 = mMap.addMarker(new MarkerOptions()
                .position(KIEL5)
                .title("fire5")
                .snippet("Kiel is cool")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_warning_black_48dp)));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(KIEL, 50));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                if(marker.getTitle().equals("Kiel")){
                    Intent intent1 = new Intent(getApplicationContext(), RegistrationActivity.class);
                     startActivity(intent1);
                }
            }
        });

        // mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }

    private static boolean isInBackground;
    private static boolean isAwakeFromBackground;
    private static final int backgroundAllowance = 10000;

    public static boolean activityPaused() {
        isInBackground = true;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isInBackground) {
                    isAwakeFromBackground = true;
                }
            }
        }, backgroundAllowance);
        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Start your code
        } else {
            //Show snackbar

        }
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        mMap.animateCamera(cameraUpdate);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates((android.location.LocationListener) MapActivity.this);
    }


}
