package com.example.finalappandroid;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MyDatabaseHelper myDbHelper;
    SQLiteDatabase dbRead;
    SQLiteDatabase dbWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDbHelper = new MyDatabaseHelper(this);
        dbRead =  myDbHelper.getReadableDatabase();
        dbWrite = myDbHelper.getWritableDatabase();



        /////Move to Settings button
        Button buttonOne = findViewById(R.id.buttonTest);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked");

                Intent activity2Intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(activity2Intent);
            }
        });
    }
    protected void onStart() {
        super.onStart();

    }
    protected void onStop(){
        super.onStop();
    }
}
