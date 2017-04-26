package com.develivery.cem.deliveryservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.develivery.cem.deliveryservice.R;
import com.develivery.cem.deliveryservice.adapter.OrderAdapter;
import com.develivery.cem.deliveryservice.database.TokenDB;
import com.develivery.cem.deliveryservice.model.Order;
import com.develivery.cem.deliveryservice.utility.JsonParse;
import com.develivery.cem.deliveryservice.utility.RequestURL;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cem on 17.04.2017.
 */
public class ApprovedFragment extends Fragment {

    private OrderAdapter adapter;
    private ListView listView;
    private ArrayList<Order> orders;
    private TokenDB tokenDB;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_approved, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.listViewOnaylanmis);
        tokenDB = new TokenDB(getActivity());
        getAllOrders();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                Order order = orders.get(position);
                intent.putExtra("selectedOrder", new Gson().toJson(order));
                startActivity(intent);
            }
        });
    }

    public void getAllOrders(){
        StringRequest objectRequest = new StringRequest(Request.Method.GET, RequestURL.baseUrl.concat(RequestURL.ordersUrl),new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Order> approvedOrders = new ArrayList<>();
                JsonParse jsonParse = new JsonParse();
                orders = jsonParse.jsonParser(response);
                for (int i = 0; i < orders.size(); i++) {
                    if (orders.get(i).getStatus().toString().equals(" APPROVED")) {
                        approvedOrders.add(orders.get(i));
                    }
                }
                adapter = new OrderAdapter(getActivity(), approvedOrders);
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

}
