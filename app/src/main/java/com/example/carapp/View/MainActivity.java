package com.example.carapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carapp.Controller.CallBacks.LoginCallBack;
import com.example.carapp.Controller.UserController;
import com.example.carapp.Entites.User;
import com.example.carapp.R;

public class MainActivity extends AppCompatActivity {
    private Button loginBTN;
    private EditText emailET, passwordET;
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userController = new UserController(this);

        loginBTN = findViewById(R.id.login);
        emailET = findViewById(R.id.loginemail);
        passwordET = findViewById(R.id.loginpassword);

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(emailET.getText().toString(), passwordET.getText().toString());
            }
        });
    }

    public void login(String email, String password){

        userController.userLogin(email, password, new LoginCallBack() {
            @Override
            public void onSuccess(User user) {
                if(user.getUsertype().getId() == 1){ // admin
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(intent);
                }else{ // driver
                    Intent intent = new Intent(getApplicationContext(), DriverActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailer(String error) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG);
            }
        });

    }

}
