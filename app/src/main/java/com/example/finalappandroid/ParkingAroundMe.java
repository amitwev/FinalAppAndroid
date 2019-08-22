package com.example.finalappandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.Menu;
import android.widget.Toast;

import java.util.Set;

public class ParkingAroundMe extends AppCompatActivity {
    private WebView webViewContent;
    private MyDatabaseHelper myDBHelper;
    private SQLiteDatabase dbRead;
    private String latitude;
    private String longitude;
    private SharedPreferences mPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_around_me);
        init();
    }

    private void init() {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        webViewContent = findViewById( R.id.webViewParkingAroundMe );
        myDBHelper = new MyDatabaseHelper(this);
        dbRead = myDBHelper.getReadableDatabase();
        getLastLocationFromDB();
        loadGoogleMaps();
    }

    private void getLastLocationFromDB() {
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
        result.moveToFirst();
        if(result.getCount() > 0){
            latitude = result.getString( 1 );
            longitude = result.getString( 2 );
            Log.d(this.toString(), "inside the true for result, lattit = " + latitude + ", longi = " + longitude);

        }else{
            latitude = mPreferences.getString("latitude", "34.761187");
            longitude = mPreferences.getString("longtitude", "32.047732");
            Log.d(this.toString(), "inside the false for result, lati = " + latitude + ", longi = " + longitude);

        }
    }

    public void loadGoogleMaps(){
        Log.d(this.toString(), " inside load google maps");
        String googleUrl = "https://www.google.co.il/maps/search/parking/@" + latitude +"," + longitude + ",15z/data=!3m1!4b1";
        Log.d(this.toString(), googleUrl);
        webViewContent.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webViewContent.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewContent.loadUrl(googleUrl);
    }

    //Start Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings1) {
            Toast.makeText( this, "Last Parking page", Toast.LENGTH_SHORT ).show();
            Intent activity1Intent = new Intent( getApplicationContext(), LastParking.class );
            startActivity( activity1Intent );


        } else if (id == R.id.action_settings2) {
            Toast.makeText( this, "Current Location page", Toast.LENGTH_SHORT ).show();
            Intent activity2Intent = new Intent( getApplicationContext(), SaveCurrentLocation.class );
            startActivity( activity2Intent );


        } else if (id == R.id.action_settings3) {
            Toast.makeText( this, "Parking History page", Toast.LENGTH_SHORT ).show();
            Intent activity3Intent = new Intent( getApplicationContext(), ParkingHistory.class );
            startActivity( activity3Intent );


        } else if (id == R.id.action_settings4) {
            Toast.makeText( this, "parking around me page", Toast.LENGTH_SHORT ).show();
            Intent activity4Intent = new Intent( getApplicationContext(), ParkingAroundMe.class );
            startActivity( activity4Intent );


        } else if (id == R.id.action_settings5) {
            Toast.makeText( this, "Settings page", Toast.LENGTH_SHORT ).show();
            Intent activity5Intent = new Intent( getApplicationContext(), Settings.class );
            startActivity( activity5Intent );

        } else if (id == R.id.action_settings6) {
            Toast.makeText( this, "Team page", Toast.LENGTH_SHORT ).show();
            Intent activity6Intent = new Intent( getApplicationContext(), Team.class );
            startActivity( activity6Intent );

        } else if (id == R.id.action_settings) {
            Toast.makeText( this, "MAIN page", Toast.LENGTH_SHORT ).show();
            Intent activityIntent = new Intent( getApplicationContext(), MainActivity.class );
            startActivity( activityIntent );

        }
        return super.onOptionsItemSelected( item );
    }

    //END Menu


}
