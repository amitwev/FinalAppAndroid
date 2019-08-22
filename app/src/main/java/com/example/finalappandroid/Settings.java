package com.example.finalappandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationHolder;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.custom.CustomErrorReset;
import com.basgeekball.awesomevalidation.utility.custom.CustomValidation;
import com.basgeekball.awesomevalidation.utility.custom.CustomValidationCallback;

public class Settings extends AppCompatActivity {
    //Shared prefrences vars
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    // settings vars
    EditText editTextDriverName;
    EditText editTextDriverCar;
    EditText editTextdriverCarNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editTextDriverName = findViewById(R.id.editTextDriverName);
        editTextDriverCar = findViewById(R.id.editTextDriverCar);
        editTextdriverCarNumber = findViewById(R.id.editTextdriverCarNumber);
        //init shared prefrences
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        Log.d(this.toString(), "end of on create method");
    }
    protected void onStart(){
        super.onStart();
        loadDriverDetailsOnStart();
        Log.d(this.toString(), "end of on start");

    }
    protected void onStop(){
        super.onStop();
        saveDetailsToSharedPrefrence();
        Log.d(this.toString(), "end of on Stop");
    }

    private void loadDriverDetailsOnStart(){ //This is for default driver details -> first time use
        String nameOfDriver = mPreferences.getString("currentNameOfDriver", "Yossi Cohen");
        editTextDriverName.setText(nameOfDriver);
        String carOfDriver = mPreferences.getString("currentCarOfDriver", "Honda");
        editTextDriverCar.setText(carOfDriver);
        String numberOfDriver = mPreferences.getString("currentNumberOfDriver", "11-222-33");
        editTextdriverCarNumber.setText(numberOfDriver);
        Log.d(this.toString(),"on create name " + nameOfDriver);
        Log.d(this.toString(), "on create car " + carOfDriver);
        Log.d(this.toString() , "on create number " + numberOfDriver);
    }
     public boolean validateDriverDetails(){
        AwesomeValidation mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        mAwesomeValidation.addValidation(this, R.id.editTextDriverName, "[a-zA-Z\\s]+",R.string.errorDriverName);
        mAwesomeValidation.addValidation(this, R.id.editTextDriverCar, "[a-zA-Z\\s]+",R.string.errorDriverCar);
        mAwesomeValidation.addValidation(this, R.id.editTextdriverCarNumber, "^[0-9]{2,3}[-]{1}[0-9]{2,3}[-]{1}[0-9]{2,3}$",R.string.errorDriverCarNumber);
        //validateCarNumber(mAwesomeValidation);
        if(mAwesomeValidation.validate()){
            return true;
        }else{
            return false;
        }

    }

    public void saveDriverDetails(View v){//Function to save to user details after clicked on 'save' button
        Log.d(this.toString(), "inside the save driver details");
        if(validateDriverDetails()){
            Log.d(this.toString(),"inside true in tha validation -> saving the details");
            saveDetailsToSharedPrefrence();
            popupMessage(true);
        }else{
            popupMessage(false);
            Log.d(this.toString(), "inside the false in the validation");
        }
    }
    private void popupMessage(Boolean isSuccess){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(isSuccess){
            builder.setTitle(R.string.titleForPopUpSuccess);
            builder.setMessage(R.string.messageForPopUpSuccess);
        }else{
            builder.setTitle(R.string.titleForPopUpFailed);
            builder.setMessage(R.string.messageForPopUpFailed);
        }
        builder.setCancelable(true);
        builder.show();
    }
    public void saveDetailsToSharedPrefrence(){
        //Declare paramas
        String driverStringName = editTextDriverName.getText().toString();
        String driverStringCar = editTextDriverCar.getText().toString();
        String driverStringNumber = editTextdriverCarNumber.getText().toString();
        //Save driver name
        mEditor.putString("currentNameOfDriver",driverStringName);
        mEditor.commit();
        //Save the car
        mEditor.putString("currentCarOfDriver", driverStringCar);
        mEditor.commit();
        //Save the Number
        mEditor.putString("currentNumberOfDriver", driverStringNumber);
        mEditor.commit();
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
