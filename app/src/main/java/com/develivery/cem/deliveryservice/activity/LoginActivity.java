package com.develivery.cem.deliveryservice.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initial();

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String sifre = txtSifre.getText().toString();
                Staff staff = new Staff();
                staff.setEmail(email);
                staff.setPassword(sifre);



                /**
                 * splah ekranda db boş mu? --> dolu olması token var demektir
                 * db boş ise --> login ekranı --> bilgiler alınıp request atılır
                 * db dolu ise --> order ekranı
                 */


                demand.sendRequestForLogin(staff);
                //demand.getAllProducts(getApplicationContext());

                Intent ıntent = new Intent(getApplicationContext(),OrderActivity.class);
                ıntent.putExtra("staffID", 1);
                startActivity(ıntent);
            }
        });
    }

    private void initial(){
        txtEmail = (EditText) findViewById(R.id.email);
        txtSifre = (EditText) findViewById(R.id.password);
        btnGiris = (Button) findViewById(R.id.giris);

        //----
        demand = new Demand(getApplicationContext());
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

}
