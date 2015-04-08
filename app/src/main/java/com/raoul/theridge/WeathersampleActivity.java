package com.raoul.theridge;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.raoul.theridge.ImageLoadPackge.ImageLoader;

import javax.xml.xpath.XPathConstants;


public class WeathersampleActivity extends Activity {
    String temperature;
    Bitmap icon = null;
    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTemperatureField;
    ImageView weatherIcon;
    ProgressDialog dialog;
    String temp;
    String wind;
    String humerny;
    String update;
    String weather_icon_url;
    ImageLoader imageLoader;
    private XPath xPath = XPathFactory.newInstance().newXPath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weathersample);
        imageLoader=new ImageLoader(WeathersampleActivity.this);
        cityField = (TextView)findViewById(R.id.city_field);
        updatedField = (TextView)findViewById(R.id.updated_field);
        detailsField = (TextView)findViewById(R.id.details_field);
        currentTemperatureField = (TextView)findViewById(R.id.current_temperature_field);
        weatherIcon = (ImageView)findViewById(R.id.weather_icon);
        ImageButton refresh_but=(ImageButton)findViewById(R.id.refesh_imageButton);
        refresh_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new retrieve_weatherTask().execute();
            }
        });
        new retrieve_weatherTask().execute();

    }



    protected class retrieve_weatherTask extends AsyncTask<Void, String, String> {

        protected void onPreExecute(){
            dialog = new ProgressDialog(WeathersampleActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Loading…");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(Void ...arg0) {
            // TODO Auto-generated method stub
            String qResult = "";
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet("http://api.wunderground.com/api/e1a9b9b8914c336f/conditions/q/KS/Lenexa.xml");

            try {
                HttpResponse response = httpClient.execute(httpGet,
                        localContext);
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    InputStream inputStream = entity.getContent();
                    Reader in = new InputStreamReader(inputStream);
                    BufferedReader bufferedreader = new BufferedReader(in);
                    StringBuilder stringBuilder = new StringBuilder();
                    String stringReadLine = null;
                    while ((stringReadLine = bufferedreader.readLine()) != null) {
                        stringBuilder.append(stringReadLine + "\n");
                    }
                    qResult = stringBuilder.toString();
                    Log.d("sdfadfasdfadfefaweraer2r23",qResult);
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Document dest = null;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder parser;
            try {
                parser = dbFactory.newDocumentBuilder();
                dest = parser
                        .parse(new ByteArrayInputStream(qResult.getBytes()));
            } catch (ParserConfigurationException e1) {
                e1.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            temp = sv ("//current_observation/temp_f", dest);
            wind=sv ("//current_observation/wind_gust_mph", dest);
            humerny=sv("//current_observation/relative_humidity", dest);
            update=sv("//current_observation/observation_time", dest);
            weather_icon_url=sv("//current_observation/icon_url", dest);
            Log.d("TEMP", temp);

            return temp;

        }

        public String sv(String query, Node node) {

            String rs = "";

            try {
                Node n = (Node) xPath.evaluate(query, node, XPathConstants.NODE);
                if (n != null) {
                    rs = n.getTextContent();
                }
            } catch (Exception e) {
                rs = "";
            }
            return rs;
        }

        protected void onPostExecute(String result) {
            if (dialog == null)
            {

            }
            else {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            currentTemperatureField.setText( String.format("%.0f", Double.parseDouble(temp))+" °F");
                            updatedField.setText(update);
                            detailsField.setText("Humidity "+humerny+"\n"+"Wind "+wind+" mph");
                            imageLoader.DisplayImage(weather_icon_url,weatherIcon);

                        }
                    });

                }
            }
        }


    }
}
