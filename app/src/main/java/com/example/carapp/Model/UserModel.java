package com.example.carapp.Model;

import android.content.Context;

import com.example.carapp.Controller.CallBacks.LoginCallBack;
import com.example.carapp.Controller.CallBacks.SelectCarCallBack;
import com.example.carapp.Controller.CallBacks.SelectUsertypeCallBack;
import com.example.carapp.Database.RequestApi;
import com.example.carapp.Database.VolleyCallBack;
import com.example.carapp.Entites.Car;
import com.example.carapp.Entites.User;
import com.example.carapp.Entites.UserType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserModel {

    RequestApi requestApi;
    UsertypeModel usertypeModel;
    CarModel carmodel;

    public UserModel(Context context) {
        this.requestApi = new RequestApi(context);
        this.usertypeModel = new UsertypeModel(context);
        this.carmodel  = new CarModel(context);
    }

    public void selectSingleUser(HashMap<String, String> con, final LoginCallBack loginCallBack){

        requestApi.selectApi(new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {
System.out.println(result);
                try {
                    JSONObject obj = new JSONObject(result);
                    // error, meessage , array

                    if(obj.getBoolean("error")){
                        loginCallBack.onFailer(obj.getString("message"));
                    }
                    else{
                        JSONArray arr = obj.getJSONArray("users"); // array of json object
                        final JSONObject userObject = (JSONObject) arr.get(0);

                        final User user = new User();
                        user.setId(userObject.getInt("ID"));
                        user.setFirstName(userObject.getString("Firstname"));
                        user.setLastName(userObject.getString("Lastname"));
                        user.setEmail(userObject.getString("Email"));
                        user.setPassword(userObject.getString("Password"));

                        // usertype object .. car
                        final HashMap<String, String> conUT = new HashMap<>();
                        conUT.put("ID", String.valueOf(userObject.getInt("UsertypeID")));
                        usertypeModel.selectUsertype(conUT, new SelectUsertypeCallBack() {
                            @Override
                            public void onSuccess(UserType userType) {
                                user.setUsertype(userType);
                               loginCallBack.onSuccess(user);
                            }

                            @Override
                            public void onFailer(String error) {

                                loginCallBack.onFailer(error);
                            }
                        });

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                loginCallBack.onFailer(error);
            }
        }, "users", con);

    }

    public void insertUser(HashMap<String, String> con, final VolleyCallBack insertCallBack ){

        requestApi.insertApi(new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {

                System.out.println(result);

                try {
                    JSONObject object = new JSONObject(result);

                    if(object.getBoolean("error")){
                        insertCallBack.onError(object.getString("message")); //check 3al db
                    }else{
                        insertCallBack.onSuccess(object.getString("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {
                insertCallBack.onError(error);//network,API
            }
        }, "users",con);

    }
}
