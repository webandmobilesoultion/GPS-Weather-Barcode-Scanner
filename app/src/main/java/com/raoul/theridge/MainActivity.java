package com.raoul.theridge;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.raoul.theridge.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends Activity {
    String resultstring;
    SharedPreferences preferences;
    String barcodesavestr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_WORLD_WRITEABLE);
        barcodesavestr = preferences.getString("barcode", "");
        resultstring = "http://falconridgegolfclub.ezlinksmobile.com/index.php";
        ImageButton rangebut = (ImageButton) findViewById(R.id.range_imageButton);
        rangebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        ImageButton centerimage=(ImageButton)findViewById(R.id.center_imageButton);
        centerimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SenceActivity.class);
                startActivity(intent);
            }
        });
        ImageButton barcodebut = (ImageButton) findViewById(R.id.barcode_imageButton);
        barcodebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivity(intent);
            }
        });

        ImageButton barcodegeneraterbut = (ImageButton) findViewById(R.id.perfomanc_imageButton);
        barcodegeneraterbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent=new Intent(MainActivity.this,EncoderActivity.class);
                    startActivity(intent);

            }
        });
        ImageButton weatherbut = (ImageButton) findViewById(R.id.weather_imageButton);
        weatherbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeathersampleActivity.class);
                startActivity(intent);
            }
        });
        ImageButton tee_imagebut = (ImageButton) findViewById(R.id.tee_imageButton);
        tee_imagebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(resultstring));
                startActivity(browserIntent);
            }
        });

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

}
