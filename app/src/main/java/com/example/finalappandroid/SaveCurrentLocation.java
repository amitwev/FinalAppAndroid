package com.example.finalappandroid;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Button;

public class SaveCurrentLocation extends AppCompatActivity implements LocationListener {
    private LocationManager locationManager;
    private LocationListener locationListener;
    private static final int THREE_MIN_UPDATE;
    private WebView webViewContent;
    static {
        THREE_MIN_UPDATE = 180000;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(this.toString(), "inside on create ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_current_location);
        webViewContent = findViewById(R.id.webViewContact);
        Log.d(this.toString(), "web view = "  + webViewContent);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //TODO add here something that related to package uses and permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(this.toString(), "inside the if");
            // TODO: Consider calling
            //  ActivityCompat#requestPermissions
            return;

        }
        Log.d(this.toString(), "before the location manager");
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, THREE_MIN_UPDATE, 10, this);
        Log.d(this.toString(), "After the location manager");
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        loadGoogleMaps(lastKnownLocation, webViewContent);
        Log.d(this.toString(), "after the location manager");
//        Location currenctLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        Button updateLocationButton = findViewById(R.id.updateLocationButton);
//        updateLocationButton.setOnClickListener(onLocationChanged(currenctLocation));
    }
    @Override
    public void onLocationChanged(Location location) {
        Log.d(this.toString(), "inside the on location change");
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        Log.d(this.toString(), "Location changed to long: " + latitude + ", alti: "+ longitude);

    }
    public void loadGoogleMaps(Location location, WebView webView){
        Log.d(this.toString(), "inside load google maps");
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        String googleUrl = "https://www.google.com/maps/place/"+latitude+","+longitude;
        webView.loadUrl(googleUrl);
        Log.d(this.toString(), "finish load the google maps page");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        //startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

    }
}
