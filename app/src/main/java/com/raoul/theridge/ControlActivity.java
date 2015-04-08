package com.raoul.theridge;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.raoul.theridge.R;


public class ControlActivity extends Activity {
    Fragment googlefragment;
    Fragment virtualfragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        Button googlebut=(Button)findViewById(R.id.google_button);
        Button virtualbut=(Button)findViewById(R.id.virtual_button);
        googlefragment=new GoogleActivity();
        virtualfragment=new VirtualActivity();
        googlebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place,  googlefragment);

                fragmentTransaction.commit();

            }
        });


        virtualbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, virtualfragment);
                fragmentTransaction.commit();

            }
        });


    }



}
