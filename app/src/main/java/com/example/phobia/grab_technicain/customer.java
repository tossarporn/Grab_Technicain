package com.example.phobia.grab_technicain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class customer extends AppCompatActivity implements OnMapReadyCallback {
    ArrayList<String> item = new ArrayList<>();
    SpinnerDialog spinnerDialog;


    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ic_magnifier:
                    spinnerDialog.showSpinerDialog();

                    return true;
                case R.id.ic_heart:
                    Intent heartIntent = new Intent(customer.this,Favorite.class);
                    startActivity(heartIntent);
                    return true;
                case R.id.ic_person:
                    Intent aboutmeIntent = new Intent(customer.this,About_me.class);
                    startActivity(aboutmeIntent);
                    return true;

            }

            return false;
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
       SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
       mapFragment.getMapAsync(this);
        iniItem();
        spinnerDialog = new SpinnerDialog(customer.this,item,"Select Area");
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                Toast.makeText(customer.this,"select"+item,Toast.LENGTH_LONG).show();
            }
        });
 }

    private void iniItem() {
        for(int i=0;i<100;i++){
            item.add("itme"+i);
        }
    }

    @Override
   public void onMapReady(GoogleMap googleMap) {
       GoogleMap mMap = googleMap;

       Double lat = 13.757792;
       Double lng = 100.492984;
       LatLng Bangkok = new LatLng(lat, lng);
       // Add a marker in Sydney and move the camera
       LatLng sydney = new LatLng(-13.757792, 100.492984);
       mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
       myconfig config = new myconfig();
       try {
           get_data allmap = new get_data(getApplicationContext());
           allmap.execute(config.getShow_all_marker());
           String jsonresMap = allmap.get().toString();
           JSONArray jsonArray = new JSONArray(jsonresMap);

           Log.d("map","get json =>"+jsonresMap+" size"+jsonArray.length());

           for (int i =0 ;i<jsonArray.length();i++ ){

               JSONObject json_map_tec = jsonArray.getJSONObject(i);
               Log.d("map","get json =>"+json_map_tec.getString("name_store").toString());
               Double lat_loca = json_map_tec.getDouble("lat");
               Double lng_loca = json_map_tec.getDouble("lng");
               LatLng location_store = new LatLng(lat_loca, lng_loca);
               String name_store = json_map_tec.getString("name_store");
               String type = json_map_tec.getString("type_name");
               String area = json_map_tec.getString("area_name");
               mMap.addMarker(new MarkerOptions().position(location_store).title(name_store).snippet("รับซ้อม "+type+" ("+area+")"));
           }

       }catch (Exception e){
           Log.d("map","get json =>"+e.toString());
       }
       // Add a marker
       mMap.moveCamera(CameraUpdateFactory.newLatLng(Bangkok));

       //Zoom
       mMap.animateCamera(CameraUpdateFactory
               .newLatLngZoom(Bangkok, 9));

   }
}


