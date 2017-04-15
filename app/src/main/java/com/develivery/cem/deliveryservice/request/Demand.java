package com.develivery.cem.deliveryservice.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.develivery.cem.deliveryservice.model.Staff;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cem on 16.04.2017.
 */
public class Demand {

    public void sendRequestForLogin(Staff staff){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(com.android.volley.Request.Method.POST, "",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("", "onErrorResponse: " + error.getMessage());

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("email", "email");
                params.put("password", "sifre");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("header1", "header1");
                headers.put("header2", "header2");

                return headers;
            }
        };
    }
}
