package com.develivery.cem.deliveryservice.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.develivery.cem.deliveryservice.R;
import com.develivery.cem.deliveryservice.database.TokenDB;
import com.develivery.cem.deliveryservice.model.Order;
import com.google.gson.Gson;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cem on 18.04.2017.
 */
public class OrderDetailActivity extends Activity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private LinearLayout lytOrderStatu;
    private TextView txtSiparisDurum;
    private TextView txtPlanlananTarih;
    private TextView txtPlanlananSaati;
    private TextView txtUrun1Adi;
    private TextView txtUrun2Adi;
    private TextView txtUrun3Adi;
    private RadioButton radioOnaylandı;
    private RadioButton radioYolaCikti;
    private RadioButton radioTeslimEdildi;
    private RadioGroup radioBtnGrp;
    private Button btnLogout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        init();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Gson gson = new Gson();
            Order order = gson.fromJson(bundle.getString("selectedOrder"), Order.class);
            setOrderDetails(order);
            System.out.println("sttus: " + order.getStatus());
        }
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kaydetme islemi
            }
        });
        int selectedId = radioBtnGrp.getCheckedRadioButtonId();
    }

    private void setOrderDetails(Order order){

        if (order.getStatus().equals(" DELIVERED")) {
            lytOrderStatu.setBackgroundColor(Color.parseColor("#71bf44"));
            txtSiparisDurum.setText("ZAMANINDA TESLİM EDİLDİ");
        }else if (order.getStatus().equals(" APPROVED")) {
            lytOrderStatu.setBackgroundColor(Color.parseColor("#47abce"));
            txtSiparisDurum.setText("ONAYLANDI");
        }else{
            lytOrderStatu.setBackgroundColor(Color.parseColor("#ee1f3b"));
            txtSiparisDurum.setText("TESLİMAT ZAMANI AŞILDI");
        }

        Date deliveryDate = new Date(order.getDelivery_date());
        Format dateFormat = new SimpleDateFormat("dd.MM.yyyy/hh:mm");
        txtPlanlananTarih.setText(dateFormat.format(deliveryDate).split("/")[0]);
        txtPlanlananSaati.setText(dateFormat.format(deliveryDate).split("/")[1]);
        //txtUrun1Adi.setText(); product parse edilecek

    }

    private void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        lytOrderStatu = (LinearLayout) findViewById(R.id.lytSiparisDurum);
        txtSiparisDurum = (TextView) findViewById(R.id.txtSiparisDurum);
        txtPlanlananTarih = (TextView) findViewById(R.id.txtPlanlananTarih);
        txtPlanlananSaati = (TextView) findViewById(R.id.txtPlanlananSaati);
        txtUrun1Adi = (TextView) findViewById(R.id.txtUrun1Adi);
        txtUrun2Adi = (TextView) findViewById(R.id.txtUrun2Adi);
        txtUrun3Adi = (TextView) findViewById(R.id.txtUrun3Adi);
        radioOnaylandı = (RadioButton) findViewById(R.id.radioOnaylandı);
        radioYolaCikti = (RadioButton) findViewById(R.id.radioYolaCikti);
        radioTeslimEdildi = (RadioButton) findViewById(R.id.radioTeslimEdildi);
        radioBtnGrp = (RadioGroup) findViewById(R.id.radioBtnGrp);
        btnLogout = (Button) findViewById(R.id.btnLogout);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.orderActivity) {
            Intent ıntent = new Intent(this,OrderActivity.class );
            startActivity(ıntent);
        }else if (id == R.id.closeApp) {
            TokenDB tokenDB = new TokenDB(getApplicationContext());
            tokenDB.resetTable();
            finish();
        }else if(id == R.id.myAccountActivity){
            Intent ıntent = new Intent(this, MyAccountActivity.class);
            startActivity(ıntent);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
