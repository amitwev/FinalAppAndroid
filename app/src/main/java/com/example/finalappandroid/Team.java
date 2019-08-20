package com.example.finalappandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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


}
