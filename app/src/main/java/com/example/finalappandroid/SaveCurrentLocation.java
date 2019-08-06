package com.example.finalappandroid;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SaveCurrentLocation extends AppCompatActivity implements LocationListener {
    private LocationManager locationManager;
    private LocationListener locationListener;
    private static final int THREE_MIN_UPDATE;
    private WebView webViewContent;
    private Location lastKnownLocation;
    private MyDatabaseHelper myDBHelper;
    private SQLiteDatabase dbWrite;
    static {
        THREE_MIN_UPDATE = 180000;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_current_location);
        webViewContent = findViewById(R.id.webViewContact);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //For DB Params
        myDBHelper = new MyDatabaseHelper(this);
        dbWrite = myDBHelper.getWritableDatabase();

        //TODO add here something that related to package uses and permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //  ActivityCompat#requestPermissions
            return;

        }
        //Note that in order to update the location from the update button
        //Need to set the minTime to less then '180000' -> for me its working only for less like '10000'
        //Sergay asked to update the location every 3 min -> 180000
        //This is something that related to the emulator and the GPS-> will test it on real phone later
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 180000, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);
        lastKnownLocation = renderLocation();
        loadGoogleMaps(lastKnownLocation);
    }
    public void onStop() {
        super.onStop();
        Log.d(this.toString(), "in stop");
        //TODO add here save location to SQL
    }
    public Location renderLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        return lastKnownLocation;
    }
    public void updateCurrentLocation(View v){
        onLocationChanged(renderLocation());
    }
    public void saveCurrentLocation(View v){
        //TODO add here save to DB
        //TODO add here save to shared pref -> in order to show it on the 'last location'
        Log.d(this.toString(), "inside the save button");
        long rowIdForNewInsert;
        Location nowLocation = renderLocation();
        ContentValues cv = new ContentValues();
        //Get the params and set it into the content values
        cv.put(DatabaseContact.LocationHistory.COLUMN_NAME_LATITUDE, nowLocation.getLatitude());
        cv.put(DatabaseContact.LocationHistory.COLUMN_NAME_LONGITUDE, nowLocation.getLongitude());
        // insert to DB
        rowIdForNewInsert = dbWrite.insert(DatabaseContact.LocationHistory.TABLE_NAME, null, cv);
        Log.d(this.toString(), "new row = " + rowIdForNewInsert);
    }
    @Override
    public void onLocationChanged(Location location){
        loadGoogleMaps(location);
    }
    public void loadGoogleMaps(Location location){
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        String googleUrl = "https://www.google.com/maps/place/"+latitude+","+longitude;
        webViewContent.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webViewContent.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewContent.loadUrl(googleUrl);
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {
        //This function will open the settings page if the settings is not enabled
//        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//        startActivity(intent);
    }
}
