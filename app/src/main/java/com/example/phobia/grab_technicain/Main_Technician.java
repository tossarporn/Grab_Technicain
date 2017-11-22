package com.example.phobia.grab_technicain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main_Technician extends AppCompatActivity {
    private TextView create_store,chartTextView,logoutTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__technician);
        blidwidget();
        button_controller();
    }

    private void button_controller() {
        create_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Build_Store.class));
            }
        });
        chartTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Chart.class));
            }
        });
        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),login.class));
                finish();
            }
        });
    }


    private void blidwidget() {
        create_store = (TextView) findViewById(R.id.build_store);
        chartTextView = (TextView) findViewById(R.id.chart);
        logoutTextView = (TextView) findViewById(R.id.out);
    }
}
