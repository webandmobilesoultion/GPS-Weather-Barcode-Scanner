package com.raoul.theridge;

//import android.content.Context;
//import android.location.Criteria;
//import android.location.Location;
//import android.location.LocationManager;
//import android.support.v4.app.FragmentActivity;
//import android.os.Bundle;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.model.Polyline;
//import com.google.android.gms.maps.model.PolylineOptions;
//import com.example.mobile_perfect.googlemap.android.SphericalUtil;
//
//import java.util.Arrays;
//
//public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerDragListener{
//
//    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
//    private Polyline mPolyline;
//    private Marker mMarkerA;
//    private Marker mMarkerB;
//    private Marker mMarkerC;
//    private Marker mMarkerD;
//    private Marker mMarkerE;
//    private Marker mMarkerF;
//    private Marker mMarkerG;
//    private Marker mMarkermy;
//    TextView distance_edittext;
//    TextView dis_a;
//    TextView dis_b;
//    TextView dis_c;
//    TextView dis_d;
//    TextView dis_e;
//    TextView dis_f;
//    TextView dis_g;
//    private LocationManager locationManager;
//    private String provider;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
//        distance_edittext=(TextView)findViewById(R.id.distance_textView);
//        dis_a=(TextView)findViewById(R.id.A_textView);
//        dis_b=(TextView)findViewById(R.id.B_textView);
//        dis_c=(TextView)findViewById(R.id.C_textView);
//        dis_d=(TextView)findViewById(R.id.D_textView);
//        dis_e=(TextView)findViewById(R.id.E_textView);
//        dis_f=(TextView)findViewById(R.id.F_textView);
//        dis_g=(TextView)findViewById(R.id.G_textView);
//        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
//        boolean enabledGPS = service
//                .isProviderEnabled(LocationManager.GPS_PROVIDER);
//        boolean enabledWiFi = service
//                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//
//        // Check if enabled and if not send user to the GSP settings
//        // Better solution would be to display a dialog and suggesting to
//        // go to the settings
//        if (!enabledGPS) {
//            Toast.makeText(this, "GPS signal not found", Toast.LENGTH_LONG).show();
//
//        }
//        else {
//            setUpMapIfNeeded();
//        }
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        setUpMapIfNeeded();
//    }
//
//    /**
//     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
//     * installed) and the map has not already been instantiated.. This will ensure that we only ever
//     * call {@link #setUpMap()} once when {@link #mMap} is not null.
//     * <p/>
//     * If it isn't installed {@link SupportMapFragment} (and
//     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
//     * install/update the Google Play services APK on their device.
//     * <p/>
//     * A user can return to this FragmentActivity after following the prompt and correctly
//     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
//     * have been completely destroyed during this process (it is likely that it would only be
//     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
//     * method in {@link #onResume()} to guarantee that it will be called.
//     */
//    private void setUpMapIfNeeded() {
//        // Do a null check to confirm that we have not already instantiated the map.
//        if (mMap == null) {
//            // Try to obtain the map from the SupportMapFragment.
//            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
//                    .getMap();
//            // Check if we were successful in obtaining the map.
//            if (mMap != null) {
//                setUpMap();
//            }
//        }
//
//
////        Marker kiel = map.addMarker(new MarkerOptions()
////                .position(KIEL)
////                .title("Kiel")
////                .snippet("Kiel is cool")
////                .icon(BitmapDescriptorFactory
////                        .fromResource(R.drawable.ic_launcher)));
//
//    }
//
//    /**
//     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
//     * just add a marker near Africa.
//     * <p/>
//     * This should only be called once and when we are sure that {@link #mMap} is not null.
//     */
//    private void setUpMap() {
////        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
////        mMap.addMarker(new MarkerOptions().position(new LatLng(67, 77)).title("Mylocation"));
//        mMap.setMyLocationEnabled(true);
//        //mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
//        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//        mMap.setOnMarkerDragListener(this);
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        // Define the criteria how to select the locatioin provider -> use
//        // default
//        Criteria criteria = new Criteria();
//        provider = locationManager.getBestProvider(criteria, false);
//        Location location = locationManager.getLastKnownLocation(provider);
//        double lat = location.getLatitude();
//        double lng = location.getLongitude();
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 15));
//        mMarkerA = mMap.addMarker(new MarkerOptions().position(new LatLng(38.952023, -94.819459)).title("A").draggable(true).icon(BitmapDescriptorFactory.fromResource(R.drawable.a)));
//        mMarkerB = mMap.addMarker(new MarkerOptions().position(new LatLng(38.951753, -94.819439)).title("B").draggable(true).icon(BitmapDescriptorFactory.fromResource(R.drawable.b)));
//        mMarkerC = mMap.addMarker(new MarkerOptions().position(new LatLng(38.951594, -94.819226)).title("C").draggable(true).icon(BitmapDescriptorFactory.fromResource(R.drawable.c)));
//        mMarkerD = mMap.addMarker(new MarkerOptions().position(new LatLng(38.952204, -94.818993)).title("D").draggable(true).icon(BitmapDescriptorFactory.fromResource(R.drawable.d)));
//        mMarkerE = mMap.addMarker(new MarkerOptions().position(new LatLng(38.951562, -94.818401)).title("E").draggable(true).icon(BitmapDescriptorFactory.fromResource(R.drawable.e)));
//        mMarkerF = mMap.addMarker(new MarkerOptions().position(new LatLng(38.952190, -94.817851)).title("F").draggable(true).icon(BitmapDescriptorFactory.fromResource(R.drawable.f)));
//        mMarkerG = mMap.addMarker(new MarkerOptions().position(new LatLng(38.951860, -94.817298)).title("G").draggable(true).icon(BitmapDescriptorFactory.fromResource(R.drawable.g)));
//
////        mMap.addMarker(new MarkerOptions().position(new LatLng(67, 77)).title("Mylocation")).setDraggable(true);
////        mMap.addMarker(new MarkerOptions().position(new LatLng(70, 90)).title("Myfriend")).setDraggable(true);
//        mPolyline = mMap.addPolyline(new PolylineOptions().geodesic(true));
//        showDistance();
//
//        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
//
//            @Override
//            public void onMyLocationChange(Location arg0) {
//                // TODO Auto-generated method stub
//
//                Marker startmaek=mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("It's Me!"));
//                       startmaek.remove();
//                double lat = arg0.getLatitude();
//                double lng = arg0.getLongitude();
//                mMarkermy = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).draggable(true));
//                mMarkermy.remove();
//                double distance_a = SphericalUtil.computeDistanceBetween(mMarkermy.getPosition(), mMarkerA.getPosition());
//                double distance_b = SphericalUtil.computeDistanceBetween(mMarkermy.getPosition(), mMarkerB.getPosition());
//                double distance_c = SphericalUtil.computeDistanceBetween(mMarkermy.getPosition(), mMarkerC.getPosition());
//                double distance_d = SphericalUtil.computeDistanceBetween(mMarkermy.getPosition(), mMarkerD.getPosition());
//                double distance_e = SphericalUtil.computeDistanceBetween(mMarkermy.getPosition(), mMarkerE.getPosition());
//                double distance_f = SphericalUtil.computeDistanceBetween(mMarkermy.getPosition(), mMarkerF.getPosition());
//                double distance_g = SphericalUtil.computeDistanceBetween(mMarkermy.getPosition(), mMarkerG.getPosition());
//                mMarkerA.setSnippet(formatNumber(distance_a));
//                mMarkerB.setSnippet(formatNumber(distance_b));
//                mMarkerC.setSnippet(formatNumber(distance_c));
//                mMarkerD.setSnippet(formatNumber(distance_d));
//                mMarkerE.setSnippet(formatNumber(distance_e));
//                mMarkerF.setSnippet(formatNumber(distance_f));
//                mMarkerG.setSnippet(formatNumber(distance_g));
//                dis_a.setText("A: "+formatNumber(distance_a));
//                dis_b.setText("B: "+formatNumber(distance_b));
//                dis_c.setText("C: "+formatNumber(distance_c));
//                dis_d.setText("D: "+formatNumber(distance_d));
//                dis_e.setText("E: "+formatNumber(distance_e));
//                dis_f.setText("F: "+formatNumber(distance_f));
//                dis_g.setText("G: "+formatNumber(distance_g));
//                distance_edittext.setText("The A are " + formatNumber(distance_a) + " apart.\n"+"The B are " + formatNumber(distance_b) + " apart.\n"+"The C are " + formatNumber(distance_c) + " apart.\n"+"The D are " + formatNumber(distance_d) + " apart.\n"+"The E are " + formatNumber(distance_e) + " apart.\n"+"The F are " + formatNumber(distance_f) + " apart.\n"+"The G are " + formatNumber(distance_g) + " apart.\n");
//
//            }
//        });
//
//
//    }
//
//    private void showDistance() {
//        Criteria criteria = new Criteria();
//        provider = locationManager.getBestProvider(criteria, false);
//        Location location = locationManager.getLastKnownLocation(provider);
//        double lat = location.getLatitude();
//        double lng = location.getLongitude();
//        mMarkermy = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).draggable(true));
//        mMarkermy.remove();
//
//        double distance_a = SphericalUtil.computeDistanceBetween(mMarkermy.getPosition(), mMarkerA.getPosition());
//        double distance_b = SphericalUtil.computeDistanceBetween(mMarkermy.getPosition(), mMarkerA.getPosition());
//        double distance_c = SphericalUtil.computeDistanceBetween(mMarkermy.getPosition(), mMarkerA.getPosition());
//        double distance_d = SphericalUtil.computeDistanceBetween(mMarkermy.getPosition(), mMarkerA.getPosition());
//        double distance_e = SphericalUtil.computeDistanceBetween(mMarkermy.getPosition(), mMarkerA.getPosition());
//        double distance_f = SphericalUtil.computeDistanceBetween(mMarkermy.getPosition(), mMarkerA.getPosition());
//        double distance_g = SphericalUtil.computeDistanceBetween(mMarkermy.getPosition(), mMarkerA.getPosition());
//        mMarkerA.setSnippet(formatNumber(distance_a));
//        mMarkerB.setSnippet(formatNumber(distance_b));
//        mMarkerC.setSnippet(formatNumber(distance_c));
//        mMarkerD.setSnippet(formatNumber(distance_d));
//        mMarkerE.setSnippet(formatNumber(distance_e));
//        mMarkerF.setSnippet(formatNumber(distance_f));
//        mMarkerG.setSnippet(formatNumber(distance_g));
//        dis_a.setText("A: "+formatNumber(distance_a));
//        dis_b.setText("B: "+formatNumber(distance_b));
//        dis_c.setText("C: "+formatNumber(distance_c));
//        dis_d.setText("D: "+formatNumber(distance_d));
//        dis_e.setText("E: "+formatNumber(distance_e));
//        dis_f.setText("F: "+formatNumber(distance_f));
//        dis_g.setText("G: "+formatNumber(distance_g));
//
//        distance_edittext.setText("The a are " + formatNumber(distance_a) + " apart.\n"+"The b are " + formatNumber(distance_b) + " apart.\n"+"The c are " + formatNumber(distance_c) + " apart.\n"+"The d are " + formatNumber(distance_d) + " apart.\n"+"The e are " + formatNumber(distance_e) + " apart.\n"+"The f are " + formatNumber(distance_f) + " apart.\n"+"The g are " + formatNumber(distance_g) + " apart.\n");
//
//    }
//
//    private void updatePolyline() {
//        mPolyline.setPoints(Arrays.asList(mMarkerA.getPosition(), mMarkerB.getPosition()));
//    }
//
//    private String formatNumber(double distance) {
//        String unit = "yd";
//        distance*=1.09361;
////        if (distance < 1) {
////            distance *= 1000;
////            unit = "mm";
////        } else if (distance > 1000) {
////            distance /= 1000;
////            unit = "km";
////        }
//
//        return String.format("%4.0f%s", distance, unit);
//    }
//
//    @Override
//    public void onMarkerDragEnd(Marker marker) {
//        showDistance();
//        updatePolyline();
//    }
//
//    @Override
//    public void onMarkerDragStart(Marker marker) {
//
//    }
//
//    @Override
//    public void onMarkerDrag(Marker marker) {
//        showDistance();
//        updatePolyline();
//    }
//}
import com.raoul.theridge.R;
import com.raoul.theridge.android.SphericalUtil;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MapsActivity extends FragmentActivity implements LocationListener {
    private GoogleMap map;
    private static final LatLng A = new LatLng(38.952023, -94.819459);
    private static final LatLng B = new LatLng(38.951753, -94.819439);
    private static final LatLng C = new LatLng(38.951594, -94.819226);
    private static final LatLng D = new LatLng(38.952204, -94.818993);
    private static final LatLng E = new LatLng(38.951562, -94.818401);
    private static final LatLng F = new LatLng(38.952190, -94.817851);
    private static final LatLng G = new LatLng(38.951860, -94.817298);
    private LocationManager locationManager;
    private String provider;
    TextView dis_a;
    TextView dis_b;
    TextView dis_c;
    TextView dis_d;
    TextView dis_e;
    TextView dis_f;
    TextView dis_g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
//        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
//        map.setMyLocationEnabled(true);
//        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        dis_a=(TextView)findViewById(R.id.A_textView);
        dis_b=(TextView)findViewById(R.id.B_textView);
        dis_c=(TextView)findViewById(R.id.C_textView);
        dis_d=(TextView)findViewById(R.id.D_textView);
        dis_e=(TextView)findViewById(R.id.E_textView);
        dis_f=(TextView)findViewById(R.id.F_textView);
        dis_g=(TextView)findViewById(R.id.G_textView);

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabledGPS = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean enabledWiFi = service
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        // Check if enabled and if not send user to the GSP settings
        // Better solution would be to display a dialog and suggesting to
        // go to the settings
//        if (!enabledGPS) {
//            Toast.makeText(this, "GPS signal not found", Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//            startActivity(intent);
//        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, this);


        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            Toast.makeText(this, "Selected Provider " + provider,
                    Toast.LENGTH_SHORT).show();
            onLocationChanged(location);
        } else {

            //do something
        }

    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat =  location.getLatitude();
        double lng = location.getLongitude();
//        Toast.makeText(this, "Location " + lat+","+lng,
//                Toast.LENGTH_LONG).show();
        LatLng coordinate = new LatLng(lat, lng);
//        Toast.makeText(this, "Location " + coordinate.latitude+","+coordinate.longitude,
//                Toast.LENGTH_LONG).show();
//
//        Marker startPerc = map.addMarker(new MarkerOptions()
//                .position(coordinate)
//                .title("Start")
//                .snippet("Inizio del percorso")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
//        startPerc.remove();
        double distance_a = SphericalUtil.computeDistanceBetween(coordinate, A);
        double distance_b = SphericalUtil.computeDistanceBetween(coordinate, B);
        double distance_c = SphericalUtil.computeDistanceBetween(coordinate, C);
        double distance_d = SphericalUtil.computeDistanceBetween(coordinate, D);
        double distance_e = SphericalUtil.computeDistanceBetween(coordinate, E);
        double distance_f = SphericalUtil.computeDistanceBetween(coordinate, F);
        double distance_g = SphericalUtil.computeDistanceBetween(coordinate, G);
        dis_a.setText("A: "+formatNumber(distance_a));
        dis_b.setText("B: "+formatNumber(distance_b));
        dis_c.setText("C: "+formatNumber(distance_c));
        dis_d.setText("D: "+formatNumber(distance_d));
        dis_e.setText("E: "+formatNumber(distance_e));
        dis_f.setText("F: "+formatNumber(distance_f));
        dis_g.setText("G: "+formatNumber(distance_g));
    }


    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }


        private String formatNumber(double distance) {
        String unit = "yd";
        distance*=1.09361;
//        if (distance < 1) {
//            distance *= 1000;
//            unit = "mm";
//        } else if (distance > 1000) {
//            distance /= 1000;
//            unit = "km";
//        }

        return String.format("%4.0f%s", distance, unit);
    }

}