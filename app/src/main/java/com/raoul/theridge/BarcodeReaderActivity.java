package com.raoul.theridge;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.raoul.theridge.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class BarcodeReaderActivity extends Activity {
    String resultstring;
    String resutformat;
    TextView result_textview;
    TextView format_textview;
    Button start_but;
    private static final String url_user = "http://www.sjbsolutions.no/russapp/login.php?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_reader);
        result_textview=(TextView)findViewById(R.id.result_textView);
        format_textview=(TextView)findViewById(R.id.format_textView);
        start_but=(Button)findViewById(R.id.goto_button);
        start_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resultstring.contains("www.falconridgegolf.com"))
                {

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(resultstring));
                    startActivity(browserIntent);
                }
                else {

                    Toast.makeText(BarcodeReaderActivity.this, "Scan data in not mache our URL:\n"+url_user, Toast.LENGTH_LONG).show();

                }
            }
        });
        new IntentIntegrator(this).initiateScan();
    }





//    public void encodeBarcode(View view) {
//        // new IntentIntegrator(this).shareText("123456789101","CODE_128");
//
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {

                resultstring=result.getContents().toString();
                resutformat=result.getFormatName().toString();
                result_textview.setText(resultstring);
                format_textview.setText(resutformat);

            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    /**
     * Sample of scanning from a Fragment
     */

}