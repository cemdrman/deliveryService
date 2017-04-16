package com.develivery.cem.deliveryservice.request;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.develivery.cem.deliveryservice.model.Staff;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cem on 16.04.2017.
 */
public class Demand {
    private String baseUrl = "";
    private RequestQueue requestQueue;
    public void sendRequestForLogin(final Staff staff, Context context){
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("email", staff.getEmail());
                params.put("password", staff.getPassword());

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return createBasicAuthHeader("user", "password");
            }

            private Map<String, String> createBasicAuthHeader(String username, String password) {
                Map<String, String> headerMap = new HashMap<String, String>();

                String credentials = username + ":" + password;
                String encodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                headerMap.put("Authorization", "Basic " + encodedCredentials);

                return headerMap;
            }

        };
        requestQueue.add(stringRequest);
    }

}
