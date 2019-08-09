package com.example.finalappandroid;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    protected void onStart() {
        super.onStart();

    }
    protected void onStop(){
        super.onStop();
    }
}
