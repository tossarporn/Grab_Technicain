package com.example.phobia.grab_technicain;

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

public class register extends AppCompatActivity {
    private Button backButton;
    private EditText userEditText, passwordEditText;
    private TextView customerTextView, technicainTextView;
    private String userString,passwordString;
    private int customer = 1;
    private int thecnician = 2;
    private String jsonresporn,message,data_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bindwidget();
        buttoncontroller();

    }
    private void buttoncontroller() {
        customerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();
                if (userString.equals("") || passwordString.equals("")) {
                    Toast.makeText(register.this,"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
                }
                else {
                    myconfig myconfig = new myconfig();
                    get_data get_data = new get_data(register.this);

                    try {
                        get_data.execute(myconfig.getRegister()
                                +"?user=" +userString+"&"
                                + "password=" + passwordString + "&"
                                + "status=" + customer
                        );

                        jsonresporn = get_data.get().toString();
                        Log.d("jsonresporn", "jsonresporn==>" +jsonresporn.toString());
                        JSONObject jsonObject = new JSONObject(jsonresporn);
                        //customer = jsonObject.getInt("status");
                        Boolean status_customer = jsonObject.getBoolean("status");
                        Log.d("jsonresporn", "jsonresporn==>" +status_customer.toString());
                        message = jsonObject.getString("message");
                        if (status_customer == true) {
                            Toast.makeText(register.this,message,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(register.this,login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(register.this,message,Toast.LENGTH_SHORT).show();
                        }
                        Log.d("customer", "customer==>"+customer);
                    } catch (Exception e) {
                        Log.d("customer", "customer==>"+e.toString());
                    }
                }
            }
        });
        technicainTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();
                if (userString.equals("") || passwordString.equals("")) {
                    Toast.makeText(register.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                }
                else {
                    myconfig myconfig = new myconfig();
                    get_data get_data = new get_data(register.this);
                    try {
                        get_data.execute(myconfig.getRegister()
                            +"?user="+userString+"&"
                                +"password="+passwordString+"&"
                                +"status="+thecnician
                        );

                        jsonresporn = get_data.get().toString();
                        JSONObject jsonObject = new JSONObject(jsonresporn);
                        Boolean status_technician = jsonObject.getBoolean("status");
                        message = jsonObject.getString("message");
                        if (status_technician == true) {
                            Toast.makeText(register.this,message,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(register.this,login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(register.this,message,Toast.LENGTH_SHORT).show();
                        }
                        Log.d("technician", "technician==>" + jsonresporn);
                    } catch (Exception e) {
                        Log.d("technician", "technician==>" + e.toString());
                    }
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
                userEditText.getText().clear();
                passwordEditText.getText().clear();
            }
        });
    }
    private void bindwidget() {
        backButton = (Button) findViewById(R.id.back);
        userEditText = (EditText) findViewById(R.id.passuser);
        passwordEditText = (EditText) findViewById(R.id.password);
        customerTextView = (TextView) findViewById(R.id.customer);
        technicainTextView = (TextView) findViewById(R.id.technicain);
    }
}
