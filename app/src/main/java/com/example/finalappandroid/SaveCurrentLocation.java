package com.example.finalappandroid;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.Menu;
import android.widget.Toast;

public class SaveCurrentLocation extends AppCompatActivity implements LocationListener {
    private LocationListener locationListener;
    private LocationManager locationManager;
    private static final int THREE_MIN_UPDATE;
    private WebView webViewContent;
    private Location lastKnownLocation;
    private MyDatabaseHelper myDBHelper;
    private SQLiteDatabase dbWrite;
    private SQLiteDatabase dbRead;
    static {
        THREE_MIN_UPDATE = 180000;
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent nextActivity = new Intent(this,ParkingHistory.class);
        startActivity(nextActivity);
        return super.onOptionsItemSelected(item);
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
        dbRead = myDBHelper.getReadableDatabase();

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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, THREE_MIN_UPDATE, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);
        lastKnownLocation = renderLocation();
        Log.d(this.toString(), "the current location = " + lastKnownLocation + ", lati = " + lastKnownLocation.getLatitude() + ", longi = " + lastKnownLocation.getLongitude());
        loadGoogleMaps(lastKnownLocation);
    }
    public void onStop() {
        super.onStop();
        Log.d(this.toString(), "in stop");
        //TODO add here save location to SQL
        if(isNeedToSaveCurrentLocation( )){
            Log.d(this.toString(), "inside the if for saving the current location");
            saveCurrentLocation();
        }else{
            Log.d(this.toString(), "inside the else -> no need to save location -> already saved");
        }
    }
    private void popupMessage(Boolean isSuccess){
        Log.d(this.toString(), "inside popup message for the user in save location, message to show = " + isSuccess);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(isSuccess){
            builder.setTitle(R.string.titleForPopUpSuccess);
            builder.setMessage(R.string.messageForPopUpSuccess);
        }else{
            builder.setTitle(R.string.titleForPopUpFailed);
            builder.setMessage(R.string.messageForPopUpSaveLocationFalied);
        }
        builder.setCancelable(true);
//        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(), "Neutral button clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
        builder.show();
    }
    public Location renderLocation() {
        Location loc = new Location("dummyprovider");
        Log.d(this.toString(),"inside render location");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Log.d(this.toString(), "last known location = " + lastKnownLocation);
        if(lastKnownLocation == null){
            Log.d(this.toString(), "inside render location -> location is null");
            loc.setLatitude(34.761187);
            loc.setLongitude(32.047732);
            return loc;
        }else{
            Log.d(this.toString(), "inside render location -> location not null-> return device location");
            return lastKnownLocation;
        }

    }
    public void updateCurrentLocation(View v){
        onLocationChanged(renderLocation());
    }

    private boolean isNeedToSaveCurrentLocation(){
        Log.d(this.toString(), "inside is need to save current location function ");
        Location currentLocationFromGPS = renderLocation();
        String[] culomns = {DatabaseContact.LocationHistory.COLUMN_NAME_LOCATION_ID,
                            DatabaseContact.LocationHistory.COLUMN_NAME_LATITUDE,
                            DatabaseContact.LocationHistory.COLUMN_NAME_LONGITUDE};
        String sortLocation = DatabaseContact.LocationHistory.COLUMN_NAME_LOCATION_ID + " DESC";
        Cursor result = dbRead.query(
                DatabaseContact.LocationHistory.TABLE_NAME,
                culomns,
                null, null, null, null,
                sortLocation,
                "1"
        );
        //last db Location is = 32.1012657, 34.86733096
        //latit                 32.1012657, 34.86733096
        if(result.getCount() > 0) {
            result.moveToFirst();
            Log.d(this.toString(), "last db Location is = " + result.getDouble(1) + ", " + result.getDouble(2));
            Log.d(this.toString(), "current location of GPS = latit" + currentLocationFromGPS.getLatitude() + ", longi =" + currentLocationFromGPS.getLongitude());
            if (result.getDouble( 1 ) == currentLocationFromGPS.getLatitude() && result.getDouble( 2 ) == currentLocationFromGPS.getLongitude()) {
                Log.d( this.toString(), "inside the true for the if checking ident" );
                return false;
            } else {
                Log.d( this.toString(), "inside the else" );
                return true;
            }
        }
        return true;
    }
    public void saveLocationButtonClicked(View v){
        saveCurrentLocation();
    }
    public void saveCurrentLocation(){
        Log.d(this.toString(), "inside the save cuurent location function");
        //TODO add here save to DB
        long rowIdForNewInsert;
        Location nowLocation = renderLocation();
        ContentValues cv = new ContentValues();
        //Get the params and set it into the content values
        cv.put(DatabaseContact.LocationHistory.COLUMN_NAME_LATITUDE, nowLocation.getLatitude());
        cv.put(DatabaseContact.LocationHistory.COLUMN_NAME_LONGITUDE, nowLocation.getLongitude());
        // insert to DB
        boolean messageToShow;
        if(isNeedToSaveCurrentLocation()){
            Log.d(this.toString(), "inisde the if for saving the current location");
            rowIdForNewInsert = dbWrite.insert(DatabaseContact.LocationHistory.TABLE_NAME, null, cv);
            Log.d(this.toString(), "new row = " + rowIdForNewInsert);
            messageToShow = true;
        }else{
            Log.d(this.toString(), "inisde the else for saving the current location");
            messageToShow = false;
        }
        popupMessage(messageToShow);

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
