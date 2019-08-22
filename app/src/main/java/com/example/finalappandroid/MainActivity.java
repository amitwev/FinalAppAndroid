package com.example.finalappandroid;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyDatabaseHelper myDBHelper;
    SQLiteDatabase dbRead;
    SQLiteDatabase dbWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDBHelper = new MyDatabaseHelper(this);
        dbRead =  myDBHelper.getReadableDatabase();
        dbWrite = myDBHelper.getWritableDatabase();
        myDBHelper.onCreate(dbWrite);
        //menu


        //end menu


        /////Move to Settings button
        Button settingsButton = findViewById(R.id.moveSettings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Settings Button Clicked");
                Intent activity2Intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(activity2Intent);
            }
        });
        // Move to last parking
        Button lastParkingButton = findViewById(R.id.moveLastParking);
        lastParkingButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                System.out.println("last parking button click");
                Intent activity2Intent = new Intent(getApplicationContext(),LastParking.class);
                startActivity(activity2Intent);
            }
        });
        // move to save parking
        Button saveParking = findViewById(R.id.moveToSaveLocation);
        saveParking.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                System.out.println("save parking button click");
                Intent activity2Intent = new Intent(getApplicationContext(),SaveCurrentLocation.class);
                startActivity(activity2Intent);
            }
        });
        //move to location history
        Button locationHistory = findViewById(R.id.moveToLocationHistory);
        locationHistory.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                System.out.println("location history button click");
                Intent activity2Intent = new Intent(getApplicationContext(), ParkingHistory.class);
                startActivity(activity2Intent);
            }
        });
        Button parkingAroundMe = findViewById( R.id.parkingAroundMeButton );
        parkingAroundMe.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v) {
                System.out.println( "parking around me button click" );
                Intent activity2Intent = new Intent( getApplicationContext(), ParkingAroundMe.class );
                startActivity( activity2Intent );
            }
        } );
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


    protected void onStart() {
        super.onStart();
    }
    protected void onStop(){
        super.onStop();
    }
}
