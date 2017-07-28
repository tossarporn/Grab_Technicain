package com.example.phobia.grab_technicain;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

public class Search extends AppCompatActivity {
    private Toolbar Toolbar;
    private Spinner area,type;
    private String jsonArea;
        private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_magnifier:
//                        Intent searchIntent = new Intent(Search.this, customer.class);
//                        startActivity(searchIntent);
                      Toast.makeText(Search.this,"Search",Toast.LENGTH_LONG).show();
                      return true;
                case R.id.ic_heart:
                    Intent heartIntent = new Intent(Search.this,Favorite.class);
                    startActivity(heartIntent);
                    return true;
                case R.id.ic_person:
                    Intent aboutmeIntent = new Intent(Search.this,About_me.class);
                    startActivity(aboutmeIntent);
                    return true;

                }

                return false;
            }

        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_view);
//        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        //bindwidgetspinner
//        area = (Spinner) findViewById(R.id.area);
//        type = (Spinner) findViewById(R.id.type);
//
//        //TitleBar
        Toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar);
        Toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(Toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //spiner for database

       try {
           myconfig myconfig = new myconfig();
           get_data showarea = new get_data(getApplicationContext());
           showarea.execute(myconfig.getShow_AreaInbangkok());
           String jsonstr = showarea.get().toString();
           Log.d("jsonstr", "jsonstr=>" + jsonstr);
           //JSONArray jsonArray = new JSONArray(jsonArea);

//           for (int i=0;i<jsonArray.length();i++) {
//               JSONObject json_map_aera = jsonArray.getJSONObject(i);
//               //Log.d("area","getArea==>"+json_map_aera.getString("area_name").toString());
//               String area_name = json_map_aera.getString("area_name");
////               String name_type =json_map_aera.getString("")
//
//           }

       } catch (Exception e) {
           Log.d("area","getArea==>"+e.toString());

       }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
   }
}

