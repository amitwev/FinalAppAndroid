package com.example.finalappandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Team extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        //Amit Button
        Button amitButton = findViewById(R.id.amitDev);
        amitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("amit button click");
                Intent activity2Intent = new Intent(getApplicationContext(), AmitDev.class);
                startActivity(activity2Intent);
            }
        });
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
