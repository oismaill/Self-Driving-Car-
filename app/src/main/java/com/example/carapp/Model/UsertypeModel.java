package com.example.carapp.Model;

import android.content.Context;

import com.example.carapp.Controller.CallBacks.SelectUsertypeCallBack;
import com.example.carapp.Database.RequestApi;
import com.example.carapp.Database.VolleyCallBack;
import com.example.carapp.Entites.User;
import com.example.carapp.Entites.UserType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class UsertypeModel {

    RequestApi requestApi;

    public UsertypeModel(Context context) {
        this.requestApi = new RequestApi(context);
    }

    public void selectUsertype(Map<String, String> con, final SelectUsertypeCallBack selectUsertypeCallBack){
        requestApi.selectApi(new VolleyCallBack() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject obj = new JSONObject(result);
                    // error, meessage , array

                    if(obj.getBoolean("error")){
                        selectUsertypeCallBack.onFailer(obj.getString("message"));
                    }
                    else{
                        JSONArray arr = new JSONArray(obj.getJSONArray("usertype")); // array of json object
                        JSONObject userObject = (JSONObject) arr.get(0);

                        UserType userType = new UserType();
                        userType.setId(userObject.getInt("id"));
                        userType.setUserTypeName(userObject.getString("usertypename"));

                        selectUsertypeCallBack.onSuccess(userType);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable throwable) {
                selectUsertypeCallBack.onFailer(throwable.getMessage());
            }
        }, "usertype", con);
    }
}
