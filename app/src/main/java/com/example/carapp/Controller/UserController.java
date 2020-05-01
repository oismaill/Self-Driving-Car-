package com.example.carapp.Controller;

import android.content.Context;
import android.util.Log;

import com.example.carapp.Controller.CallBacks.LoginCallBack;
import com.example.carapp.Database.VolleyCallBack;
import com.example.carapp.Entites.User;
import com.example.carapp.Model.UserModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UserController {

    private UserModel userModel;
    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnmABCDEFGHIJKLMNOPQRSTUVWXYZ@#$%&*_-.";

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

    public void insertUser(String firstName, String lastName, String email, final VolleyCallBack insertCallBack){

        HashMap<String, String> con = new HashMap<>();
        con.put("Firstname", firstName);
        con.put("Lastname", lastName);
        con.put("Email", email);
        con.put("Password", generatePassword(10)); // auto generated later
        con.put("UsertypeID", "2");

        userModel.insertUser(con, new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {
                insertCallBack.onSuccess(result);
            }

            @Override
            public void onError(String error) {
                insertCallBack.onError(error);
            }
        });

    }
    private static String generatePassword(final int passwordSize){
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(passwordSize);

        for(int i=0;i<passwordSize;++i){
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }
        return sb.toString();
    }
}
