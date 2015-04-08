package com.raoul.theridge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.raoul.theridge.R;


public class BarcodeGeneratorActivity extends Activity {
    String barcoetstr;
    String barcodesavestr;
    Button readbut;
    Button savebut;
//    Button clearbut;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    EditText barcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_generator);
        preferences= getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_WORLD_WRITEABLE);
        editor= preferences.edit();
        barcodesavestr= preferences.getString("barcode","");
        barcode=(EditText)findViewById(R.id.barcode_editText);
//        final EditText qrcode=(EditText)findViewById(R.id.qrcode_editText);
        if(!(barcodesavestr ==null)){
            barcode.setText(barcodesavestr);

        }
//        clearbut=(Button)findViewById(R.id.clear_button);
        savebut=(Button)findViewById(R.id.save_button);
        readbut=(Button)findViewById(R.id.read_button);
        savebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barcoetstr = barcode.getText().toString();
                if(!(barcoetstr ==null)){
                    editor.putString("barcode",barcoetstr);
                    editor.commit();
                }
                else {
                    Toast.makeText(BarcodeGeneratorActivity.this,"Please enter barcde value!",Toast.LENGTH_LONG).show();
                }

            }
        });
        readbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barcodesavestr= preferences.getString("barcode","");
                if(!(barcodesavestr ==null)){
                   Intent intent=new Intent(BarcodeGeneratorActivity.this,EncoderActivity.class);
                    startActivity(intent);
                }
               else {

                    Toast.makeText(BarcodeGeneratorActivity.this,"It is does not value!Please click save",Toast.LENGTH_LONG).show();
                }

            }
        });
//        clearbut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editor.clear();
//
//            }
//        });
//        barcode.setOnEditorActionListener(
//                new EditText.OnEditorActionListener() {
//                    @Override
//                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
//                                actionId == EditorInfo.IME_ACTION_DONE ||
//                                event.getAction() == KeyEvent.ACTION_DOWN &&
//                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
//                            barcoetstr = barcode.getText().toString();
//                            Intent intent = new Intent(BarcodeGeneratorActivity.this, com.google.zxing.client.android.encode.EncodeActivity.class);
//                            intent.setAction("com.google.zxing.client.android.ENCODE");
//                            intent.putExtra("ENCODE_FORMAT", "CODE_128");
//                            intent.putExtra("ENCODE_DATA", barcoetstr);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//                            startActivity(intent);
//                            return true;
//                        }
//                        return false;
//                    }
//                });

//        qrcode.setOnKeyListener(new View.OnKeyListener() {
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_ENTER) {
//                    qrcodestr=qrcode.getText().toString();
//                    new IntentIntegrator(BarcodeGeneratorActivity.this).shareText(qrcodestr);
//
//                    return false;
//                }
//                return false;
//            }
//        });
        // new IntentIntegrator(this).shareText("123456789101","CODE_128");

    }


}
