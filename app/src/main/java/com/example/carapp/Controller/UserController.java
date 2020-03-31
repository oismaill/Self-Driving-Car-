package com.example.carapp.Controller;

import android.content.Context;
import android.util.Log;

import com.example.carapp.Controller.CallBacks.LoginCallBack;
import com.example.carapp.Entites.User;
import com.example.carapp.Model.UserModel;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    private UserModel userModel;

    public UserController(Context context) {
        this.userModel = new UserModel(context);
    }

    public void userLogin(String email, String pass, final LoginCallBack loginCallBack){ // call interface C - V

        HashMap<String, String> con = new HashMap<>(); // 3shan a7ot el haga fe key(Name) w value(sara) -- quary automatic created
        con.put("email", email);
        con.put("password", pass);
        con.put("isdeleted", "0");

        // select * from Users where ""email"" = email

        userModel.selectSingleUser(con, new LoginCallBack() { // imp interface ben M - C
            @Override
            public void onSuccess(User user) {
                loginCallBack.onSuccess(user);
            }

            @Override
            public void onFailer(String error) {
                loginCallBack.onFailer(error);
            }
        });

    }
}
