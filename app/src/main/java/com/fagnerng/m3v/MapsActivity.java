package com.fagnerng.m3v;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.fagnerng.m3v.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private LocationManager mLocationManager;
    private HashMap<Integer, Marker> marklist;
    private JSONArray treeList;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        marklist = new HashMap<Integer, Marker>();
        String sample = "[{\"number\":1,\"identification\":37947,\"species\":\"paineira\",\"latitude\":-7.13523611111111,\"longitude\":-34.8458583333333,\"fuste\":2.58,\"height\":6.31,\"dap\":0.1238225457255,\"cap\":0.389,\"biological\":0,\"anthropic\":0,\"id\":\"581165f416ce5d3f7c37f606\"},{\"number\":2,\"identification\":38256,\"species\":\"paineira\",\"latitude\":-7.13507222222222,\"longitude\":-34.8458944444445,\"fuste\":1.89,\"height\":5.01,\"dap\":0.09867606471698,\"cap\":0.31,\"biological\":0,\"anthropic\":0,\"id\":\"581165f416ce5d3f7c37f607\"},{\"number\":3,\"identification\":38235,\"species\":\"paineira\",\"latitude\":-7.13512777777778,\"longitude\":-34.8458916666667,\"fuste\":2.3,\"height\":4.5,\"dap\":0.05092958178941,\"cap\":0.16,\"biological\":0,\"anthropic\":0,\"id\":\"581165f416ce5d3f7c37f608\"},{\"number\":4,\"identification\":38237,\"species\":\"paineira\",\"latitude\":-7.13505833333333,\"longitude\":-34.8459222222222,\"fuste\":2.71,\"height\":5.21,\"dap\":0.09963099437553,\"cap\":0.313,\"biological\":1,\"anthropic\":0,\"id\":\"581165f416ce5d3f7c37f609\"},{\"number\":5,\"identification\":38253,\"species\":\"paineira\",\"latitude\":-7.13498611111111,\"longitude\":-34.8459361111111,\"fuste\":1.73,\"height\":6.51,\"dap\":0.12477747538405,\"cap\":0.392,\"biological\":0,\"anthropic\":0,\"id\":\"581165f416ce5d3f7c37f60a\"},{\"number\":6,\"identification\":38238,\"species\":\"paineira\",\"latitude\":-7.13494166666667,\"longitude\":-34.846,\"fuste\":1.53,\"height\":6.01,\"dap\":0.1238225457255,\"cap\":0.389,\"biological\":0,\"anthropic\":0,\"id\":\"581165f416ce5d3f7c37f60b\"},{\"number\":7,\"identification\":37601,\"species\":\"paineira\",\"latitude\":-7.13493888888889,\"longitude\":-34.8459416666667,\"fuste\":1.99,\"height\":2.9,\"dap\":0.0315126787322,\"cap\":0.099,\"biological\":0,\"anthropic\":0,\"id\":\"581165f416ce5d3f7c37f60c\"},{\"number\":8,\"identification\":38236,\"species\":\"paineira\",\"latitude\":-7.134925,\"longitude\":-34.8459805555556,\"fuste\":2.4,\"height\":6.31,\"dap\":0.14069296969324,\"cap\":0.442,\"biological\":0,\"anthropic\":0,\"id\":\"581165f416ce5d3f7c37f60d\"},{\"number\":9,\"identification\":38255,\"species\":\"paineira\",\"latitude\":-7.13487777777778,\"longitude\":-34.84605,\"fuste\":1.5,\"height\":6.61,\"dap\":0.16552114081557,\"cap\":0.52,\"biological\":0,\"anthropic\":1,\"id\":\"581165f416ce5d3f7c37f60e\"},{\"number\":10,\"identification\":37602,\"species\":\"paineira\",\"latitude\":-7.13486388888889,\"longitude\":-34.8460277777778,\"fuste\":1.54,\"height\":5.51,\"dap\":0.13782818071758,\"cap\":0.433,\"biological\":0,\"anthropic\":0,\"id\":\"581165f416ce5d3f7c37f60f\"},{\"number\":11,\"identification\":37604,\"species\":\"Ipê\",\"latitude\":-7.13500833333333,\"longitude\":-34.8459638888889,\"fuste\":0.29,\"height\":1.18,\"dap\":0.02037183271576,\"cap\":0.064,\"biological\":0,\"anthropic\":1,\"id\":\"581165f416ce5d3f7c37f610\"},{\"number\":12,\"identification\":37876,\"species\":\"Ipê\",\"latitude\":-7.13497222222222,\"longitude\":-34.8458166666667,\"fuste\":0.182,\"height\":2.71,\"dap\":0.05793239928545,\"cap\":0.182,\"biological\":1,\"anthropic\":1,\"id\":\"581165f416ce5d3f7c37f611\"},{\"number\":13,\"identification\":37589,\"species\":\"Ipê\",\"latitude\":-7.13493333333333,\"longitude\":-34.8457527777778,\"fuste\":0.2,\"height\":0.86,\"dap\":0.01846197339866,\"cap\":0.058,\"biological\":1,\"anthropic\":1,\"id\":\"581165f416ce5d3f7c37f612\"},{\"number\":14,\"identification\":37883,\"species\":\"Ipê\",\"latitude\":-7.13495555555556,\"longitude\":-34.8457055555556,\"fuste\":0,\"height\":0.69,\"dap\":0.01878028328484,\"cap\":0.059,\"biological\":0,\"anthropic\":2,\"id\":\"581165f416ce5d3f7c37f613\"},{\"number\":15,\"identification\":38254,\"species\":\"Ipê\",\"latitude\":-7.13493055555556,\"longitude\":-34.8456472222222,\"fuste\":0.175,\"height\":0.87,\"dap\":0.01623380419537,\"cap\":0.051,\"biological\":1,\"anthropic\":0,\"id\":\"581165f416ce5d3f7c37f614\"},{\"number\":16,\"identification\":37885,\"species\":\"\",\"latitude\":-7.13466944444444,\"longitude\":-34.8451111111111,\"fuste\":1.1,\"height\":6.06,\"dap\":0.10249578335118,\"cap\":0.322,\"biological\":0,\"anthropic\":0,\"id\":\"581165f416ce5d3f7c37f615\"},{\"number\":17,\"identification\":37880,\"species\":\"paineira\",\"latitude\":-7.134975,\"longitude\":-34.8449277777778,\"fuste\":2.35,\"height\":6.46,\"dap\":0.1531070552544,\"cap\":0.481,\"biological\":0,\"anthropic\":0,\"id\":\"581165f416ce5d3f7c37f616\"},{\"number\":18,\"identification\":37881,\"species\":\"paineira\",\"latitude\":-7.13501944444445,\"longitude\":-34.8449222222222,\"fuste\":2.72,\"height\":7.06,\"dap\":0.17825353626292,\"cap\":0.56,\"biological\":0,\"anthropic\":0,\"id\":\"581165f416ce5d3f7c37f617\"},{\"number\":19,\"identification\":37878,\"species\":\"paineira\",\"latitude\":-7.13500555555556,\"longitude\":-34.8449138888889,\"fuste\":2.15,\"height\":7.06,\"dap\":0.17825353626292,\"cap\":0.56,\"biological\":0,\"anthropic\":0,\"id\":\"581165f416ce5d3f7c37f618\"},{\"number\":20,\"identification\":37884,\"species\":\"paineira\",\"latitude\":-7.13508333333333,\"longitude\":-34.8450027777778,\"fuste\":2.45,\"height\":4.91,\"dap\":0.08021409131832,\"cap\":0.252,\"biological\":0,\"anthropic\":0,\"id\":\"581165f416ce5d3f7c37f619\"}]";
        try {
            treeList = new JSONArray(sample);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (!enableMyLocation()) {
            String[] permission = {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(this, permission, 1);
        }
    }

    private boolean enableMyLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {

            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                centerToMyLocation();
                return true;
            }
        }
        return false;
    }

    private void centerToMyLocation() {
        if (mMap != null) {

            Location location = getLastKnownLocation();

            if (location != null) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                        .zoom(17.0f)
                        .tilt(30)// Sets the zoom
                        .build();                   // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                marklist.put(1, mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude(), location.getLongitude()))
                        .title("I'm here")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        .flat(true)));
            }
            for (int i =0; i < treeList.length(); i++) {
                try {
                    JSONObject tree = treeList.getJSONObject(i);
                    int number = tree.getInt("number");
                    double lat = tree.getDouble("latitude");
                    double lng = tree.getDouble("longitude");
                    int name = tree.getInt("identification");
                    if (i == 0) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 17));

                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(new LatLng(lat, lng))      // Sets the center of the map to location user
                                .zoom(17.0f)
                                .tilt(30)// Sets the zoom
                                .build();                   // Creates a CameraPosition from the builder
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        marklist.put(0, mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                                .title("I'm here")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))));
                    }
                    marklist.put(number, mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(lat, lng))
                            .title("" + name)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Location getLastKnownLocation() {
        mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                enableMyLocation();
                continue;
            }
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Maps Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
