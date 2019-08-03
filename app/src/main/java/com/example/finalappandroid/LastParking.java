package com.example.finalappandroid;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class LastParking extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_parking);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        loadDriverDetails();

    }
    private void loadDriverDetails(){
        TextView nameInput = findViewById(R.id.driverNameInput);
        TextView carInput = findViewById(R.id.driverCarInput);
        TextView numberInput = findViewById(R.id.driverNumberInput);

        String nameOfDriver = mPreferences.getString("currentNameOfDriver", "Yossi Cohen");
        String carOfDriver = mPreferences.getString("currentCarOfDriver", "Honda");
        String numberOfDriver = mPreferences.getString("currentNumberOfDriver", "11-222-33");
        String latitude = mPreferences.getString("longtitude", "32.047732");
        String longitude = mPreferences.getString("latitude", "34.761187");
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
}
