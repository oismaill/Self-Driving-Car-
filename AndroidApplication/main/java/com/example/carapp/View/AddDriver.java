package com.example.carapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carapp.Controller.UserController;
import com.example.carapp.Database.VolleyCallBack;
import com.example.carapp.R;

public class AddDriver extends AppCompatActivity {
    private EditText driverFN, driverLN, driverEmail;
    private Button addBTN;

    private UserController userController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);

        userController = new UserController(this);

        driverFN = (EditText) findViewById(R.id.driverFirstname);
        driverLN = (EditText) findViewById(R.id.driverLasttname);
        driverEmail = (EditText) findViewById(R.id.driverEmail);

        addBTN = findViewById(R.id.addBTN);

        //validation = true
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDriver(driverFN.getText().toString(), driverLN.getText().toString(), driverEmail.getText().toString());
            }
        });

    }

    private void addDriver(String firstName, String lastName, String email){

        userController.insertUser(firstName, lastName, email, new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
            }
        });

    }
}
