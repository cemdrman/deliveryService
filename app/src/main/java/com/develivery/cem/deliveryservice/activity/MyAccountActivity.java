package com.develivery.cem.deliveryservice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.develivery.cem.deliveryservice.R;
import com.develivery.cem.deliveryservice.database.TokenDB;
import com.develivery.cem.deliveryservice.model.Staff;
import com.develivery.cem.deliveryservice.request.RequestURL;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MyAccountActivity extends Activity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private TextView txtStaffName;
    private TextView txtStaffEmail;
    private TextView txtStaffOrderCount;
    private Button btnLogout;
    private Staff staff;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        int id =  getIntent().getExtras().getInt("staffID");
        String token = getIntent().getExtras().getString("token");
        init();
        getAccountInfo(token,id);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TokenDB tokenDB = new TokenDB(getApplicationContext());
                tokenDB.resetTable();
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }

    private void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayoutAccount);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_for_account);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
        txtStaffName = (TextView) findViewById(R.id.txtStaffName);
        txtStaffEmail = (TextView) findViewById(R.id.txtStaffEmail);
        txtStaffOrderCount = (TextView) findViewById(R.id.txtStaffOrderCount);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        staff = new Staff();
    }

    private void getAccountInfo(final String token, int id){

        JsonObjectRequest staffRequest = new JsonObjectRequest(Request.Method.GET, RequestURL.baseUrl.concat(RequestURL.staffUrl).concat("/" + id), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                try {
                    txtStaffEmail.setText(response.getString("email"));
                    txtStaffName.setText(response.getString("name"));
                    txtStaffOrderCount.setText(String.valueOf(staff.getTotal_deliveries()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
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
        Volley.newRequestQueue(getApplicationContext()).add(staffRequest);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.close) {
            TokenDB tokenDB = new TokenDB(getApplicationContext());
            tokenDB.resetTable();
            finish();
        }else if(id == R.id.my_account){
            Intent ıntent = new Intent(this, MyAccountActivity.class);
            ıntent.putExtra("staffID",getIntent().getExtras().getInt("staffID"));
            ıntent.putExtra("token",getIntent().getExtras().getString("token"));
            startActivity(ıntent);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
