package com.example.finalappandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.view.Menu;
import android.widget.Toast;

public class LastParking extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private MyDatabaseHelper myDBHelper;
    private SQLiteDatabase dbRead;
    private String longitude;
    private String latitude;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_parking);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        myDBHelper = new MyDatabaseHelper(this);
        dbRead = myDBHelper.getReadableDatabase();
        loadDriverDetails();

    }

    private void loadDriverDetails(){
        TextView nameInput = findViewById(R.id.driverNameInput);
        TextView carInput = findViewById(R.id.driverCarInput);
        TextView numberInput = findViewById(R.id.driverNumberInput);

        String nameOfDriver = mPreferences.getString("currentNameOfDriver", "Yossi Cohen");
        String carOfDriver = mPreferences.getString("currentCarOfDriver", "Honda");
        String numberOfDriver = mPreferences.getString("currentNumberOfDriver", "11-222-33");

        setLocationForGoogleMaps();
        Log.d(this.toString(), "name of driver: " + nameOfDriver + ", car of driver: " + carOfDriver + ", number: " + numberOfDriver);

        nameInput.setText(nameOfDriver);
        carInput.setText(carOfDriver);
        numberInput.setText(numberOfDriver);
        String googleUrl = "https://www.google.com/maps/place/" + latitude + "," + longitude;
        Log.d(this.toString(), "GOOGLE URL = " + googleUrl);
        WebView wb = findViewById(R.id.webViewContact);
        wb.setWebViewClient(new WebViewClient());
        WebSettings webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wb.loadUrl(googleUrl);
    }

    private void setLocationForGoogleMaps() {
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
            Log.d(this.toString(), "inside the if in the set location for google maps");
            latitude = result.getString( 1 );
            longitude = result.getString( 2 );
        }else{
            Log.d(this.toString(), "inside the else in the set location for google maps");
            latitude = mPreferences.getString("longtitude", "32.047732");
            longitude = mPreferences.getString("latitude", "34.761187");
        }
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
