package com.raoul.theridge;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class SenceActivity extends Activity {
    ImageButton facebook_imagebutton;
    ImageButton twiter_imagebutton;
    ImageButton game_imagebutton;
    ImageButton email_imagebutton;
    ImageButton location_imagebutton;
    ImageButton site_imagebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sence);
        facebook_imagebutton=(ImageButton)findViewById(R.id.facebook_imageButton);
        twiter_imagebutton=(ImageButton)findViewById(R.id.twiter_imageButton);
        game_imagebutton=(ImageButton)findViewById(R.id.game_imageButton);
        email_imagebutton=(ImageButton)findViewById(R.id.emaile_imageButton);
        location_imagebutton=(ImageButton)findViewById(R.id.location_imageButton);
        site_imagebutton=(ImageButton)findViewById(R.id.site_imageButton);
        facebook_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://facebook.com/FalconRidgeGolfClub"));
                startActivity(browserIntent);
            }
        });
        twiter_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://twitter.com/FalconRidgeKS"));
                startActivity(browserIntent);
            }
        });
        site_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://falconridgegolf.com"));
                startActivity(browserIntent);
            }
        });
        email_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        location_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SenceActivity.this,LocationActivity.class);
                startActivity(intent);
            }
        });
        game_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SenceActivity.this)
                        .setTitle("Message")
                        .setMessage("Coming soon ")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });


    }

    protected void sendEmail() {
        Log.i("Send email", "");
//        ParseUser user=ParseUser.getCurrentUser();
        String[] TO = {"info@falconridgegolf.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SenceActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
