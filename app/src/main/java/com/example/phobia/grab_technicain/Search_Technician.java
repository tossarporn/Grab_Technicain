package com.example.phobia.grab_technicain;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

public class Search_Technician extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String area_String;
    myconfig myconfig = new myconfig();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__technician);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.technician_map);
        mapFragment.getMapAsync(this);



    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng center = null;

        area_String = getIntent().getStringExtra("area_id");
        get_data all_technician = new get_data(getApplicationContext());
        all_technician.execute(myconfig.getShow_all_marker()+"?area_id="+area_String);

        try {
            String str_all_thecnician = all_technician.get().toString();
            Log.d("str_all_thecnician", " str_all_thecnician=>" + str_all_thecnician);
            JSONArray all_thecnician_array = new JSONArray(str_all_thecnician);

            if (all_thecnician_array.length() == 0) {
                Toast.makeText(Search_Technician.this, "ไม่มีร้านซ่อมอุปกรณ์ไฟฟ้าอยู่ในเขตนี้ครับ", Toast.LENGTH_LONG).show();
            }

            for (int i =0 ;i<all_thecnician_array.length();i++ ){

                JSONObject json_map_tec = all_thecnician_array.getJSONObject(i);
                Log.d("map","get json =>"+json_map_tec.getString("name_store").toString());
                Double lat_loca = json_map_tec.getDouble("lat");
                Double lng_loca = json_map_tec.getDouble("lng");
                center = new LatLng(lat_loca,lng_loca);
                LatLng location_store = new LatLng(lat_loca, lng_loca);
                String name_store = json_map_tec.getString("name_store");
                String type = json_map_tec.getString("type_name");
                String area = json_map_tec.getString("area_name");
                mMap.addMarker(new MarkerOptions().position(location_store).title(name_store).snippet("รับซ้อม "+type+" ("+area+")"));
            }

            move_cam(center);

        } catch (Exception e) {

        }

    }

    public void  move_cam(LatLng latLng){
        // Add a marker
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        //Zoom
        mMap.animateCamera(CameraUpdateFactory
                .newLatLngZoom(latLng, 12));
    }

}
