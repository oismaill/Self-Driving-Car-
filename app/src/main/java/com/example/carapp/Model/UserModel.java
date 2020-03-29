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

import java.util.Map;

public class UserModel {

    RequestApi requestApi;
    UsertypeModel usertypeModel;
    CarModel carmodel;

    public UserModel(Context context) {
        this.requestApi = new RequestApi(context);
        this.usertypeModel = new UsertypeModel(context);
    }

    public void selectSingleUser(Map<String, String> con, final LoginCallBack loginCallBack){

        requestApi.selectApi(new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject obj = new JSONObject(result);
                    // error, meessage , array

                    if(obj.getBoolean("error")){
                        loginCallBack.onFailer(obj.getString("message"));
                    }
                    else{
                        JSONArray arr = new JSONArray(obj.getJSONArray("User")); // array of json object
                        final JSONObject userObject = (JSONObject) arr.get(0);

                        final User user = new User();
                        user.setId(userObject.getInt("ID"));
                        user.setFirstName(userObject.getString("firstname"));
                        user.setLastName(userObject.getString("lastname"));
                        user.setEmail(userObject.getString("email"));
                        user.setPassword(userObject.getString("password"));

                        // usertype object .. car
                        final Map<String, String> conUT = null;
                        conUT.put("ID", String.valueOf(userObject.getInt("UsertypeID")));
                        usertypeModel.selectUsertype(conUT, new SelectUsertypeCallBack() {
                            @Override
                            public void onSuccess(UserType userType) {
                                user.setUsertype(userType);

                                // start:: car

                                Map<String, String> conCar = null;
                                try {
                                    conCar.put("ID", String.valueOf(userObject.getInt("CarID")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                carmodel.selectCar(conCar, new SelectCarCallBack() {
                                    @Override
                                    public void onSuccess(Car car) {
                                        user.setUserCar(car);
                                        loginCallBack.onSuccess(user);
                                    }

                                    @Override
                                    public void onFailer(String error) {
                                        loginCallBack.onFailer(error);
                                    }
                                });

                                // end:: car
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
            public void onError(Throwable throwable) {
                loginCallBack.onFailer(throwable.getMessage());
            }
        }, "User", con);

    }
}
