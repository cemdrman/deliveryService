package com.develivery.cem.deliveryservice.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.develivery.cem.deliveryservice.R;

/**
 * Created by cem on 18.04.2017.
 */
public class OrderDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        TextView textView = (TextView) findViewById(R.id.txtTestData);
        textView.setText(getIntent().getExtras().getString("test"));
    }
}
