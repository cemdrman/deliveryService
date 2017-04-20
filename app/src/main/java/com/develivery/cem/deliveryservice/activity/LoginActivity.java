package com.develivery.cem.deliveryservice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.develivery.cem.deliveryservice.R;
import com.develivery.cem.deliveryservice.database.TokenDB;
import com.develivery.cem.deliveryservice.model.Staff;
import com.develivery.cem.deliveryservice.request.Demand;
import com.develivery.cem.deliveryservice.request.RequestURL;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {

    private EditText txtEmail;
    private EditText txtSifre;
    private Button btnGiris;
    private Demand demand;
    private TokenDB tokenDB;
    private String token;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        if (tokenDB.getRowCount() > 0) {
            System.out.println();
            Intent ıntent = new Intent(LoginActivity.this,OrderActivity.class );
            System.out.println("token" + tokenDB.getToken());
            System.out.println("staffID" + tokenDB.getID());
            ıntent.putExtra("token",tokenDB.getToken());
            ıntent.putExtra("staffID",tokenDB.getID());
            startActivity(ıntent);
        }else{
            btnGiris.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = txtEmail.getText().toString();
                    String sifre = txtSifre.getText().toString();
                    if (email.equals("") || sifre.equals("")) {
                        Toast.makeText(getApplicationContext(), "Lütfen Bilgilerizi Tam Giriniz!", Toast.LENGTH_LONG).show();
                    }else{
                        Staff staff = new Staff();
                        staff.setEmail(email);
                        staff.setPassword(sifre);
                        login(staff);
                    }
                }
            });
        }
    }

    private void login(final Staff staff){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, RequestURL.baseUrl.concat(RequestURL.loginUrl),null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("response:" + response);

                try {
                    token = response.getString("token");
                    id = response.getInt("id");
                    System.out.println("staff token:" + token);
                    TokenDB db = new TokenDB(getApplicationContext());
                    db.saveToken(token,id);
                    Intent ıntent = new Intent(getApplicationContext(),OrderActivity.class);
                    ıntent.putExtra("staffID", id);
                    ıntent.putExtra("token", token);
                    startActivity(ıntent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                Toast.makeText(getApplicationContext(), "Geçersiz email yada şifre!", Toast.LENGTH_LONG).show();
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
                    jsonObject.accumulate("email",staff.getEmail());
                    jsonObject.accumulate("password", staff.getPassword());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String string_json = jsonObject.toString();
                System.out.println("request json body:" + string_json);
                return string_json.getBytes();
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init(){
        txtEmail = (EditText) findViewById(R.id.email);
        txtSifre = (EditText) findViewById(R.id.password);
        btnGiris = (Button) findViewById(R.id.giris);

        //----

        tokenDB = new TokenDB(getApplicationContext());
    }

}
