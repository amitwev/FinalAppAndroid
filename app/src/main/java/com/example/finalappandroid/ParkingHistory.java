package com.example.finalappandroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.Menu;
import android.widget.Toast;

public class ParkingHistory extends AppCompatActivity {
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase dbRead;
    private SQLiteDatabase dbWrite;
    private String sortLocationString;
    private String[] returnCulomnsFromDB;
    private Cursor allData;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_history);
        //For Db Connection
        initDB();
        allData = getAllDataFromDB();
        writeDataToTable();
    }

    public void deleteAllData(View v){
        Log.d(this.toString(), "inside delete all data");
        deleteAllDataFromDB();
    }

    private void deleteAllDataFromDB() {
        Log.d( this.toString(), "inside the delete from db" );
        String deleteRowsString = "DELETE FROM " + DatabaseContact.LocationHistory.TABLE_NAME;
        dbWrite.execSQL( deleteRowsString );
        finish();
        startActivity(getIntent());
    }

    private void writeDataToTable() {
        //TODO add here function to insert the data to table
        TableLayout historyTable = findViewById(R.id.historyTable);

        TableRow firstTableRowForInit = findViewById(R.id.firstTableRow);
        ViewGroup.LayoutParams layoutParamsForInitRow = firstTableRowForInit.getLayoutParams();
        Log.d(this.toString(), "table row params = " + firstTableRowForInit.getLayoutParams());

        TextView textViewForInit = findViewById(R.id.textViewForInit);
        ViewGroup.LayoutParams layoutParamsForInitTextView = textViewForInit.getLayoutParams();
        Log.d(this.toString(), "text view params = " + textViewForInit.getLayoutParams());

        Log.d(this.toString(), "inside write to table function, the count for data is = " + allData.getCount());
        allData.moveToLast();
        for(int i = allData.getCount(); i > 0; i--){
            Log.d(this.toString(), "inside the loop");
            TableRow newRow = new TableRow(firstTableRowForInit.getContext());
            newRow.setLayoutParams( layoutParamsForInitRow);

            TextView colNumOne = new TextView(textViewForInit.getContext());
            colNumOne.setLayoutParams(layoutParamsForInitTextView);
            colNumOne.setGravity( 1 );
            TextView colNumLatitude = new TextView(textViewForInit.getContext());
            colNumLatitude.setLayoutParams(layoutParamsForInitTextView);
            colNumLatitude.setGravity( 1 );
            TextView colNumLongitude = new TextView(textViewForInit.getContext());
            colNumLongitude.setLayoutParams(layoutParamsForInitTextView);
            colNumLongitude.setGravity( 1 );

            colNumOne.setText(allData.getString(0));
            colNumLatitude.setText(allData.getString(1));
            colNumLongitude.setText(allData.getString(2));

            historyTable.addView(newRow);

            newRow.addView(colNumOne);
            newRow.addView(colNumLatitude);
            newRow.addView(colNumLongitude);

            allData.moveToPrevious();
        }
        Log.d(this.toString(), "end of write to table");
    }

    private Cursor getAllDataFromDB() {
        return dbRead.query(
                DatabaseContact.LocationHistory.TABLE_NAME,
                returnCulomnsFromDB,
                null, null, null, null,
                sortLocationString, "5");
    }

    private void initDB(){
        myDatabaseHelper = new MyDatabaseHelper(this);
        dbRead = myDatabaseHelper.getReadableDatabase();
        dbWrite = myDatabaseHelper.getWritableDatabase();
        sortLocationString = DatabaseContact.LocationHistory.COLUMN_NAME_LOCATION_ID ;
        returnCulomnsFromDB = new String[]{
                DatabaseContact.LocationHistory.COLUMN_NAME_LOCATION_ID,
                DatabaseContact.LocationHistory.COLUMN_NAME_LATITUDE,
                DatabaseContact.LocationHistory.COLUMN_NAME_LONGITUDE
        };
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
