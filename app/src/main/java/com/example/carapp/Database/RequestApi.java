package com.example.carapp.Database;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.HashMap;
import java.util.Map;

public class RequestApi {

    private Context context;

    public RequestApi(Context context) {
        this.context = context;
    }

    public void selectApi(final VolleyCallBack callback, final String tableName, Map<String, String> conditions) {

        Gson gson = new Gson();
        final String con = gson.toJson(conditions);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "C:/xampp/htdocs/Selfdrivingcar/select.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        callback.onError(error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("table", tableName);
                param.put("conditions", con);

                return param;
            }
        };

        VolleySingleton.getnInstance(context).addRequestQue(stringRequest);
    }

    public void insertApi(final VolleyCallBack callback, final String tableName, Map<String, String> data) {

        Gson gson = new Gson();
        final String insert_data = gson.toJson(data);


        StringRequest stringRequest = new StringRequest(Request.Method.POST,"C:/xampp/htdocs/Selfdrivingcar/insert.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        callback.onError(error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("table", tableName);
                param.put("data", insert_data);

                return param;
            }
        };

        VolleySingleton.getnInstance(context).addRequestQue(stringRequest);
    }

    public void updateApi(final String tableName, Map<String, String> data, Map<String, String> conditions) {}

    public void deleteApi(final VolleyCallBack callback, final String tableName, Map<String, String> conditions) {

        Gson gson = new Gson();
        final String insert_data = gson.toJson(conditions);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "C:/xampp/htdocs/Selfdrivingcar/delete.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        callback.onError(error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("table", tableName);
                param.put("data", insert_data);

                return param;
            }
        };

        VolleySingleton.getnInstance(context).addRequestQue(stringRequest);

    }

}


