package com.example.finalappandroid;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

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
        String nameOfDriver = mPreferences.getString("editName", "Yossi Cohen");
        editTextDriverName.setText(nameOfDriver);
        String carOfDriver = mPreferences.getString("editCar", "Honda");
        editTextDriverCar.setText(carOfDriver);
        String numberOfDriver = mPreferences.getString("editNumber", "11-222-33");
        editTextdriverCarNumber.setText(numberOfDriver);
        Log.d(this.toString(),"on create name " + nameOfDriver);
        Log.d(this.toString(), "on create car " + carOfDriver);
        Log.d(this.toString() , "on create number " + numberOfDriver);
    }

    public boolean validateDriverDetails(){
        //TODO need to add  validation to car number
        AwesomeValidation mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        mAwesomeValidation.addValidation(this, R.id.editTextDriverName, "[a-zA-Z\\s]+",R.string.errorDriverName);
        mAwesomeValidation.addValidation(this, R.id.editTextDriverCar, "[a-zA-Z\\s]+",R.string.errorDriverName);
        mAwesomeValidation.addValidation(this, R.id.editTextdriverCarNumber, "",R.string.errorDriverName);
        if(mAwesomeValidation.validate()){
            return true;
        }else{
            return false;
        }

    }

    public void saveDriverDetails(View v){//Function to save to user details after clicked on 'save' button
        //TODO need to validate the user input - using awesome validation
        //TODO create function for success and failed
        //TODO need to save driver details in DB?
        Log.d(this.toString(), "inside the save driver details");
        if(validateDriverDetails()){
            Log.d(this.toString(),"inside true in tha validation -> saving the details");
            saveDetailsToSharedPrefrence();
        }else{
            Log.d(this.toString(), "inside the false in the validation");
            //TODO add error here for the validation
        }
    }

    public void saveDetailsToSharedPrefrence(){
        //Declare paramas
        String driverStringName = editTextDriverName.getText().toString();
        String driverStringCar = editTextDriverCar.getText().toString();
        String driverStringNumber = editTextdriverCarNumber.getText().toString();
        //Save driver name
        mEditor.putString("editName",driverStringName);
        mEditor.commit();
        //Save the car
        mEditor.putString("editCar", driverStringCar);
        mEditor.commit();
        //Save the Number
        mEditor.putString("editNumber", driverStringNumber);
        mEditor.commit();
    }
}
