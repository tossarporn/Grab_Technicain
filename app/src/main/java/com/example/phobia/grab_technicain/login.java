package com.example.phobia.grab_technicain;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class login extends AppCompatActivity {
    private Button loginButton, cancelButton;
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    private TextView registerTextView;
    private int customer = 1;
    private int thecnician  = 2;
    private String jsonrespone2,message, data_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindwidget();
        buttoncontroller();
    }
    private void buttoncontroller() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();
                    if (userString.equals("") || passwordString.equals("")) {
                        Toast.makeText(login.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_LONG).show();
                    } else {
                        myconfig myconfig = new myconfig();
                        get_data get_data = new get_data(login.this);
                        try {
                                
                        }
                        catch (Exception e) {
                        }
                    }
                }

        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEditText.getText().clear();
                passwordEditText.getText().clear();
            }
        });
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });
    }

    private void bindwidget() {
        loginButton = (Button) findViewById(R.id.login);
        cancelButton = (Button) findViewById(R.id.cancel);
        userEditText = (EditText) findViewById(R.id.user);
        passwordEditText = (EditText) findViewById(R.id.password);
        registerTextView = (TextView) findViewById(R.id.allregister);
    }
}
