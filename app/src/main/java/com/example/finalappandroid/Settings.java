package com.example.finalappandroid;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Settings extends AppCompatActivity {
    //Shared prefrences vars
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    // settings vars
    EditText driverName;
    EditText driverCar;
    EditText driverCarNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        driverName = findViewById(R.id.editName);
        driverCar = findViewById(R.id.editCar);
        driverCarNumber = findViewById(R.id.editNumber);
        //init shared prefrences
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        checkDriverDetails();
    }
    private void checkDriverDetails(){ //This is for default driver details
        String nameOfDriver = mPreferences.getString(getString(R.string.name), "Yossi Cohen");
        String carOfDriver = mPreferences.getString(getString(R.string.car), "Honda");
        String numberOfDriver = mPreferences.getString(getString(R.string.Number), "11-222-33");

    }

    public void saveDriverDetails(View v){
        //Here is the function for the 'save' button to save the driver details- parameters save in the params above

        //Save details in shared preferences
        //Save the name
        String driverStringName = driverName.getText().toString();
        mEditor.putString(getString(R.string.name), driverStringName);
        mEditor.commit();
        //Save the car
        String driverStringCar = driverCar.getText().toString();
        mEditor.putString(getString(R.string.name), driverStringCar);
        mEditor.commit();
        //Save the Number
        String driverStringNumber = driverCarNumber.getText().toString();
        mEditor.putString(getString(R.string.name), driverStringNumber);
        mEditor.commit();

    }
}
