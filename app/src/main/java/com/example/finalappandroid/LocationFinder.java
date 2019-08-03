package com.example.finalappandroid;
//import Location
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationFinder implements LocationListener{
    LocationManager lm;
    Context mContext;
    public LocationFinder(Context mContext){
        this.mContext = mContext;
        lm = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
    }
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
