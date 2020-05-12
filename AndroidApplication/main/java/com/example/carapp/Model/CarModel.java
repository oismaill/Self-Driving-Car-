package com.example.carapp.Model;

import android.content.Context;

import com.example.carapp.Controller.CallBacks.SelectCarCallBack;
import com.example.carapp.Controller.CallBacks.SelectUsertypeCallBack;
import com.example.carapp.Database.RequestApi;
import com.example.carapp.Database.VolleyCallBack;
import com.example.carapp.Entites.Anomaly;
import com.example.carapp.Entites.Car;
import com.example.carapp.Entites.User;
import com.example.carapp.Entites.UserType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CarModel {


    RequestApi requestApi;

    public CarModel(Context context) {
        this.requestApi = new RequestApi(context);
    }

    public void selectCar(HashMap<String, String> con, final SelectCarCallBack selectCarCallBack){
        requestApi.selectApi(new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                try {
                    JSONObject obj = new JSONObject(result);
                    // error, meessage , array

                    if(obj.getBoolean("error")){
                        selectCarCallBack.onFailer(obj.getString("message"));
                    }
                    else{
                        JSONArray arr = obj.getJSONArray("car"); // array of json object
                        JSONObject userObject = (JSONObject) arr.get(0);

                        Car car = new Car();
                        car.setId(userObject.getInt("ID"));
                        car.setSerialNumber(userObject.getString("SerialNum"));
                        car.setModelName(userObject.getString("ModelName"));

                        //getAllAnomalies(car, selectCarCallBack);

                        selectCarCallBack.onSuccess(car);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                selectCarCallBack.onFailer(error);
            }
        }, "car", con);
    }

}


