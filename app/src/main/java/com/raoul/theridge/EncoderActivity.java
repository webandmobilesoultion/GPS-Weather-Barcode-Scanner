/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.raoul.theridge;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.raoul.theridge.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.raoul.theridge.data.Contents;
import com.raoul.theridge.qrcode.QRCodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Example Encoder Activity.
 * 
 * @author Justin Wetherell (phishman3579@gmail.com)
 */
public final class EncoderActivity extends Activity {
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    String barcodesavestr;
    private static final String TAG = EncoderActivity.class.getSimpleName();
    private ImageView view;
    private Bitmap bitmap;
    private EditText input;
    private String value;
    TextView contents;
    QRCodeEncoder qrCodeEncoder;
    int smallerDimension;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.encoder);
        preferences= getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_WORLD_WRITEABLE);
        editor= preferences.edit();
        barcodesavestr= preferences.getString("barcode","");
        contents = (TextView) findViewById(R.id.contents_text_view);
        // This assumes the view is full screen, which is a good assumption
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 7 / 8;
        if(barcodesavestr.equals("")){


            AlertDialog.Builder alert = new AlertDialog.Builder(EncoderActivity.this);

            alert.setTitle("Barcode edit");
            alert.setMessage("Please enter barcoce");

// Set an EditText view to get user input
            input = new EditText(EncoderActivity.this);
            alert.setView(input);

            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    value = input.getText().toString();
                    editor.putString("barcode",value);
                    editor.commit();

                    // qrCodeEncoder = new QRCodeEncoder("12345678910", null,
                    // Contents.Type.TEXT, BarcodeFormat.UPC_A.toString(),
                    // smallerDimension);

                    try {
                        QRCodeEncoder    reqrCodeEncoder=null;
                        reqrCodeEncoder = new QRCodeEncoder(value, null, Contents.Type.TEXT, BarcodeFormat.CODE_128.toString(), smallerDimension);
                        ImageView    review = (ImageView) findViewById(R.id.image_view);
                        Bitmap   rebitmap = reqrCodeEncoder.encodeAsBitmap();

                        review.setImageDrawable(null);
                        review.setImageBitmap(rebitmap);
                        contents.setText(reqrCodeEncoder.getDisplayContents());
                        setTitle(getString(R.string.app_name) + " - " + reqrCodeEncoder.getTitle());



                    } catch (WriterException e) {
                        e.printStackTrace();
                    }





                    // Do something with value!
                }
            });

            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Canceled.
                }
            });

            alert.show();

        }
        else{

            try {
                qrCodeEncoder = null;
                // qrCodeEncoder = new QRCodeEncoder("AT", null, Contents.Type.TEXT,
                // BarcodeFormat.CODABAR.toString(), smallerDimension);
                // qrCodeEncoder = new QRCodeEncoder("HI", null, Contents.Type.TEXT,
                // BarcodeFormat.CODE_39.toString(), smallerDimension);
                // qrCodeEncoder = new QRCodeEncoder("Hello", null,
                // Contents.Type.TEXT, BarcodeFormat.CODE_128.toString(),
                // smallerDimension);
                // qrCodeEncoder = new QRCodeEncoder("1234567891011", null,
                // Contents.Type.TEXT, BarcodeFormat.EAN_13.toString(),
                // smallerDimension);
                // qrCodeEncoder = new QRCodeEncoder("12345678", null,
                // Contents.Type.TEXT, BarcodeFormat.EAN_8.toString(),
                // smallerDimension);
                // qrCodeEncoder = new QRCodeEncoder("1234", null,
                // Contents.Type.TEXT, BarcodeFormat.ITF.toString(),
                // smallerDimension);
                // qrCodeEncoder = new QRCodeEncoder("2345", null,
                // Contents.Type.TEXT, BarcodeFormat.PDF_417.toString(),
                // smallerDimension);
                qrCodeEncoder = new QRCodeEncoder(barcodesavestr, null, Contents.Type.TEXT, BarcodeFormat.CODE_128.toString(), smallerDimension);
                // qrCodeEncoder = new QRCodeEncoder("12345678910", null,
                // Contents.Type.TEXT, BarcodeFormat.UPC_A.toString(),
                // smallerDimension);

                bitmap = qrCodeEncoder.encodeAsBitmap();
                view = (ImageView) findViewById(R.id.image_view);
                view.setImageBitmap(bitmap);


                contents.setText(qrCodeEncoder.getDisplayContents());
                setTitle(getString(R.string.app_name) + " - " + qrCodeEncoder.getTitle());
            } catch (WriterException e) {
                Log.e(TAG, "Could not encode barcode", e);
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "Could not encode barcode", e);
            }

        }

//        ImageButton share_but=(ImageButton)findViewById(R.id.share_imageButton);
//        share_but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                view.buildDrawingCache();
////                Bitmap bm =view.getDrawingCache();
//
//                Intent imageIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                File imagesFolder = new File(Environment.getExternalStorageDirectory(), "Punch");
//                imagesFolder.mkdirs();
//                String fileName = "image"  + ".jpg";
//                File output = new File(imagesFolder, fileName);
//                Uri uriSavedImage = Uri.fromFile(output);
//                imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
//                OutputStream fos = null;
//
//                try {
//                    fos = getContentResolver().openOutputStream(uriSavedImage);
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//                    fos.flush();
//                    fos.close();
//                }
//                catch (FileNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                finally
//                {}
//
//                Intent intent = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
//
//
//                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(output));
//                intent.setType("image/*");
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//                startActivity(Intent.createChooser(intent, null));
//            }
//        });
        ImageButton edit_imagebut=(ImageButton)findViewById(R.id.edit_imageButton);
        edit_imagebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(EncoderActivity.this);

                alert.setTitle("Barcode edit");
                alert.setMessage("Please enter barcoce");

// Set an EditText view to get user input
                input = new EditText(EncoderActivity.this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        value = input.getText().toString();
                        editor.putString("barcode",value);
                        editor.commit();

                        // qrCodeEncoder = new QRCodeEncoder("12345678910", null,
                        // Contents.Type.TEXT, BarcodeFormat.UPC_A.toString(),
                        // smallerDimension);

                        try {
                            QRCodeEncoder    reqrCodeEncoder=null;
                            reqrCodeEncoder = new QRCodeEncoder(value, null, Contents.Type.TEXT, BarcodeFormat.CODE_128.toString(), smallerDimension);
                            ImageView    review = (ImageView) findViewById(R.id.image_view);
                            Bitmap   rebitmap = reqrCodeEncoder.encodeAsBitmap();

                            review.setImageDrawable(null);
                            review.setImageBitmap(rebitmap);
                            contents.setText(reqrCodeEncoder.getDisplayContents());
                            setTitle(getString(R.string.app_name) + " - " + reqrCodeEncoder.getTitle());



                        } catch (WriterException e) {
                            e.printStackTrace();
                        }





                        // Do something with value!
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();
            }
        });
    }
}
