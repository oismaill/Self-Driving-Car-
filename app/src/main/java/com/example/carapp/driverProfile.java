package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.carapp.View.baseProfile;

public class driverProfile extends baseProfile {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);
        this.context=getApplicationContext();
        setBasicSettings();
        displayProfileData();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void validateChange() {

    }

    @Override
    public void displayProfileData() {

    }

    @Override
    public void updateProfileData() {

    }
}
