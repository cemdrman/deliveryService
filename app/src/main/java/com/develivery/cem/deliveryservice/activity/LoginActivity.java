package com.develivery.cem.deliveryservice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.ActionBarPolicy;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.develivery.cem.deliveryservice.R;
import com.develivery.cem.deliveryservice.model.Staff;
import com.develivery.cem.deliveryservice.request.Demand;

public class LoginActivity extends Activity {

    private EditText txtEmail;
    private EditText txtSifre;
    private Button btnGiris;
    private Demand demand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialComponent();




        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String sifre = txtSifre.getText().toString();
                Staff staff = new Staff();
                staff.setEmail(email);
                staff.setPassword(sifre);
                //VolleyLog.d("login-request", "");
                //demand.sendRequestForLogin(staff, LoginActivity.this);
                Intent ıntent = new Intent(getApplicationContext(),OrderActivity.class);
                ıntent.putExtra("staffID", 1);
                startActivity(ıntent);
            }
        });
    }

    private void initialComponent(){
        txtEmail = (EditText) findViewById(R.id.email);
        txtSifre = (EditText) findViewById(R.id.password);
        btnGiris = (Button) findViewById(R.id.giris);

        //----

        demand = new Demand();
    }

}
