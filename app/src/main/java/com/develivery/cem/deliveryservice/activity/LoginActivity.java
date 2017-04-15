package com.develivery.cem.deliveryservice.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.develivery.cem.deliveryservice.R;
import com.develivery.cem.deliveryservice.model.Staff;
import com.develivery.cem.deliveryservice.request.Demand;

import org.json.JSONObject;

public class LoginActivity extends Activity {

    private EditText txtEmail;
    private EditText txtSifre;
    private Button btnGiris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialComponent();
        final Demand demand = new Demand();

        RequestQueue requestQueue = Volley.newRequestQueue(this);


        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String sifre = txtSifre.getText().toString();
                Staff staff = new Staff();
                staff.setEmail(email);
                staff.setPassword(sifre);
                demand.sendRequestForLogin(staff);

            }
        });
    }

    private void initialComponent(){
        txtEmail = (EditText) findViewById(R.id.email);
        txtSifre = (EditText) findViewById(R.id.password);
        btnGiris = (Button) findViewById(R.id.giris);
    }

    private void parseResponse(JSONObject s){

    }




}
