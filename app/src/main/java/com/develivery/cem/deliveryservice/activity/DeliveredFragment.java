package com.develivery.cem.deliveryservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.develivery.cem.deliveryservice.R;
import com.develivery.cem.deliveryservice.adapter.OrderAdapter;
import com.develivery.cem.deliveryservice.database.TokenDB;
import com.develivery.cem.deliveryservice.model.Order;
import com.develivery.cem.deliveryservice.request.RequestURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cem on 17.04.2017.
 */
public class DeliveredFragment extends Fragment {

    private OrderAdapter adapter;
    private ListView listView;
    private TokenDB tokenDB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_delivered, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.listViewDelivered);

        tokenDB = new TokenDB(getActivity());
        getAllOrders();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "You clicked at position: " + (position + 1), Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(getActivity(), DesActivity.class);
                intent.putExtra("string", "go to another Activity from ListView position: " + (position + 1));
                startActivity(intent);*/
            }
        });
    }

    public void getAllOrders(){

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, RequestURL.baseUrl.concat(RequestURL.ordersUrl), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response.toString());
                ArrayList<Order> orders = parseJson(response);
                ArrayList<Order> takenOffOrders = new ArrayList<>();
                for (Order o : orders  ) {
                    System.out.println(o.getId()  + o.getStatus() + " " + o.getContact());
                }
                System.out.println("orders size:" + orders.size());
                for (int i = 0; i < orders.size(); i++) {
                    if (orders.get(i).getStatus().toString().equals(" DELIVERED")) {
                        takenOffOrders.add(orders.get(i));
                        System.out.println("içerdeyim");
                    }

                }
                adapter = new OrderAdapter(getActivity(), takenOffOrders);
                listView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Authorization","Bearer "+ tokenDB.getToken());
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(objectRequest);

    }

    private ArrayList<Order> parseJson(JSONArray response){
        ArrayList<Order> orders = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Order order = new Order();
            try {
                JSONObject rootJsonObject = response.getJSONObject(i);
                order.setId(rootJsonObject.getInt("id"));
                JSONArray products = rootJsonObject.getJSONArray("products");//******
                order.setDelivery_price((float) rootJsonObject.getDouble("delivery_price"));
                order.setContact(rootJsonObject.getString("contact"));
                order.setStatus(rootJsonObject.getString("status"));
                order.setStaff_id(rootJsonObject.getInt("staff_id"));
                /*order.setDelivery_date( rootJsonObject.getInt("delivery_date")); delivery date can be long!
                order.setTaken_of_at((Timestamp) rootJsonObject.getClass("taken_off_at"));
                order.setDelivered_at((Timestamp) rootJsonObject.getClass("delivered_at"));
                order.setApproved_at((Timestamp) rootJsonObject.getClass("approved_at"));
                order.setCreated_at((Timestamp) rootJsonObject.getClass("created_at"));
                order.setUpdated_at((Timestamp) rootJsonObject.getClass("updated_at"));*/
                order.setProduct_details(rootJsonObject.getJSONArray("product_details"));
                orders.add(order);
                System.out.println("parse edildi: " + order.getId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

}
