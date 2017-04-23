package com.develivery.cem.deliveryservice.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.develivery.cem.deliveryservice.R;
import com.develivery.cem.deliveryservice.model.Order;
import java.util.ArrayList;

/**
 * Created by cem on 21.04.2017.
 */
public class OrderAdapter extends BaseAdapter {

    private LayoutInflater ınflater;
    private ArrayList<Order> orders;

    public OrderAdapter(Activity activity, ArrayList<Order> orders) {
        ınflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.orders = orders;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Order getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        satirView = ınflater.inflate(R.layout.order_list,null);
        TextView orderNo = (TextView) satirView.findViewById(R.id.txtOrderNo);
        TextView orderDate = (TextView) satirView.findViewById(R.id.txtOrderDate);
        TextView orderHour = (TextView) satirView.findViewById(R.id.txtOrderHour);

        Order order = orders.get(position);
        orderNo.setText(String.valueOf(order.getId()));
        orderDate.setText(String.valueOf(order.getDelivery_date()));
        orderHour.setText(String.valueOf(order.getApproved_at()));

        return satirView;
    }
}
