package com.example.phobia.grab_technicain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;


public class customer extends FragmentActivity implements OnMapReadyCallback {
    private String jsonresMap ;
    private String text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;

        Double lat = 13.757792;
        Double lng = 100.492984;
        LatLng Bangkok = new LatLng(lat, lng);
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-13.757792, 100.492984);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        myconfig config = new myconfig();
        try {
            get_data allmap = new get_data(getApplicationContext());
            allmap.execute(config.getShow_all_marker());
            jsonresMap = allmap.get().toString();
            JSONArray jsonArray = new JSONArray(jsonresMap);

//            Log.d("map","get json =>"+jsonresMap+" size"+jsonArray.length());

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
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.ic_magnifier://id form directory menu
//                    Search search = new Search();//Class fragment
//                    android.app.FragmentManager manager = getFragmentManager();
//                    //manager.beginTransaction().replace(R.id.map,search,search);//id form FrameLayout
//                    return true;
//            }
//            return false;
//        }
//    };
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_bar);
//        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//    }

