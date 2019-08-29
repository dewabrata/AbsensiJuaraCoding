package com.juaracoding.absensi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.camerakit.CameraKitView;
import com.jpegkit.Jpeg;
import com.jpegkit.JpegImageView;
import com.juaracoding.absensi.R;
import com.location.aravind.getlocation.GeoLocator;


public class PhotoActivity extends AppCompatActivity {
    private CameraKitView cameraView;
    private Button btnCapture;
    JpegImageView imageView;
    private Button btnGps;
    GeoLocator geoLocator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        geoLocator = new GeoLocator(getApplicationContext(),PhotoActivity.this);
        setContentView(R.layout.activity_photo);
        imageView = findViewById(R.id.imageView);
        cameraView = findViewById(R.id.camera);
        btnCapture = findViewById(R.id.btnCapture);
        btnCapture.setOnClickListener(photoOnClickListener);
        btnGps = findViewById(R.id.btnGPS);

        btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(PhotoActivity.this,geoLocator.getLattitude() + "," + geoLocator.getLongitude() + "\n" + geoLocator.getAddress(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.onResume();
    }

    @Override
    protected void onPause() {
        cameraView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        cameraView.onStop();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private View.OnClickListener photoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cameraView.captureImage(new CameraKitView.ImageCallback() {
                @Override
                public void onImage(CameraKitView view, final byte[] photo) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final Jpeg jpeg = new Jpeg(photo);
                            imageView.post(new Runnable() {
                                @Override
                                public void run() {
                                    imageView.setJpeg(jpeg);
                                }
                            });
                        }
                    }).start();
                }
            });
        }};
}
