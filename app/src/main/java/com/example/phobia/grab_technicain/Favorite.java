package com.example.phobia.grab_technicain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class Favorite extends AppCompatActivity {
        private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ic_magnifier:
                    Intent searchIntent = new Intent(Favorite.this,Search.class);
                    startActivity(searchIntent);
                    return true;
                case R.id.ic_heart:
                    Intent heartIntent = new Intent(Favorite.this,Favorite.class);
                    startActivity(heartIntent);
                    return true;
                case R.id.ic_person:
                    Intent aboutmeIntent = new Intent(Favorite.this,About_me.class);
                    startActivity(aboutmeIntent);
                    return true;

            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }
}
