package com.develivery.cem.deliveryservice.request;

import android.content.Context;
import android.preference.PreferenceActivity;
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
import com.develivery.cem.deliveryservice.model.Order;
import com.develivery.cem.deliveryservice.model.Product;
import com.develivery.cem.deliveryservice.model.Staff;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cem on 16.04.2017.
 */
public class Demand {

    /**
     * Demand class is just for sending requests
     */

    private String token;
    private Context context;

    public Demand(Context context) {
        this.context = context;
    }

    public Staff sendRequestForLogin(final Staff staff){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, RequestURL.baseUrl.concat(RequestURL.loginUrl),null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("response:" + response);
                try {
                    token = response.getString("token");
                    System.out.println("staff token:" + token);
                    staff.setId(response.getInt("id"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
            @Override
            public byte[] getBody() {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.accumulate("email","cemdrman@gmail.com");
                    jsonObject.accumulate("password", "7b47997d");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String string_json = jsonObject.toString();
                System.out.println("request json body:" + string_json);
                return string_json.getBytes();
            }
        };
        requestQueue.add(stringRequest);

        return staff;
    }

    public ArrayList<Product> getAllProducts (){
        ArrayList<Product> products = new ArrayList<>();

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, RequestURL.baseUrl.concat(RequestURL.productsUrl),null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("response:" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Authorization","Bearer "+token);
                System.out.println(params.get("Authorization"));
                return params;
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);
        return products;
    }

    public Product getProduct(int id){
        Product product = new Product();

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, RequestURL.baseUrl.concat(RequestURL.productUrl).concat("/" + id), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        return product;
    }

    public Staff getStaffInfo(int id){
        Staff staff = new Staff();

        JsonObjectRequest staffRequest = new JsonObjectRequest(Request.Method.GET, RequestURL.baseUrl.concat(RequestURL.staffUrl).concat("/" + id), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization","Bearer " + token);
                return headers;
            }
        };
        Volley.newRequestQueue(context).add(staffRequest);
        return staff;
    }

    public ArrayList<Order> getAllOrders(){
        ArrayList<Order> products = new ArrayList<>();

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, RequestURL.baseUrl.concat(RequestURL.ordersUrl), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        return products;
    }

    public ArrayList<Order> getOrder(int id){
        ArrayList<Order> products = new ArrayList<>();

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, RequestURL.baseUrl.concat(RequestURL.orderUrl).concat("/"+id), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        return products;
    }

}
