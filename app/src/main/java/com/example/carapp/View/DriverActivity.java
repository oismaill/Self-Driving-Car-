package com.example.carapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.carapp.R;
import com.example.carapp.bumps;
import com.example.carapp.driverProfile;

public class DriverActivity extends AppCompatActivity {
    private Button bumpsBTN, distanceBTN,profileBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        bumpsBTN = (Button) findViewById(R.id.bumps);
        distanceBTN = (Button) findViewById(R.id.distance);
        profileBTN = (Button) findViewById(R.id.profileBTN);
        bumpsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbumps();
            }
        });
        distanceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendistance();
            }
        });
        profileBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });
    }
    public void openbumps(){
        Intent intent = new Intent(this, bumps.class);
        startActivity(intent);
    }
    public void opendistance(){
        Intent intent = new Intent(this, bumps.class);
        startActivity(intent);
    }
    public void openProfile(){
        Intent intent = new Intent(this, driverProfile.class);
        startActivity(intent);
    }

    }

