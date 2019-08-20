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

import java.util.Set;

public class ParkingAroundMe extends AppCompatActivity {
    private WebView webViewContent;
    private MyDatabaseHelper myDBHelper;
    private SQLiteDatabase dbRead;
    private String latitude;
    private String longitude;
    private SharedPreferences mPreferences;

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menuparking, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent nextActivity;
        Log.d(this.toString(), "the menu item id = " + item.getItemId());
        int devScreen = 2131230786;
        if(item.getItemId() == devScreen){
            nextActivity = new Intent(this, Team.class);
        }else{
            nextActivity = new Intent(this, Settings.class);
        }
        startActivity(nextActivity);
        return super.onOptionsItemSelected(item);
    }


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


}
