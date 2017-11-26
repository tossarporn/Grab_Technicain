package com.example.phobia.grab_technicain;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Build_Store extends AppCompatActivity {
    private ImageView create_storeImageView;
    private EditText name_storeEditText,repairEditText,start_timeEditText,end_timeEditText,costEditText,home_numberEditText,
                     streetEditText,districEditText,areaEditText,latEditText,lngEditText;
    private Button okButton,backButton,locationButton;
    private TextInputLayout name_storeInputLayout,repairInputLayout,start_timeInputLayout,end_timeInputLayout,costInputLayout,home_numInputLayout
                            ,streetInputLayout,districInputLayout,areaInputLayout,latInputLayout, lngInputLayout;
    private Vibrator vibrator;
    Animation animshake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build__store);
        animshake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        bildwidget();
        buttoncontroller();
    }

    private void buttoncontroller() {
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitform();
            }

            private void submitform() {

                if (!checkName()) {
                    name_storeEditText.setAnimation(animshake);
                    name_storeEditText.startAnimation(animshake);
                    vibrator.vibrate(120);
                    return;
                }
                if (!checkRepair()) {
                    repairEditText.setAnimation(animshake);
                    repairEditText.startAnimation(animshake);
                    vibrator.vibrate(120);
                    return;
                }
                if (!checkStart_Time()) {
                    start_timeEditText.setAnimation(animshake);
                    start_timeEditText.startAnimation(animshake);
                    vibrator.vibrate(120);
                    return;
                }
                if (!checkEnd_time()) {
                    end_timeEditText.setAnimation(animshake);
                    end_timeEditText.startAnimation(animshake);
                    vibrator.vibrate(120);
                    return;
                }
                if (!checkCost()) {
                    costEditText.setAnimation(animshake);
                    costEditText.startAnimation(animshake);
                    vibrator.vibrate(120);
                    return;
                }
                if (!checkHome_number()) {
                    costEditText.setAnimation(animshake);
                    costEditText.startAnimation(animshake);
                    vibrator.vibrate(120);
                    return;
                }
                if (!checkStreet()) {
                    costEditText.setAnimation(animshake);
                    costEditText.startAnimation(animshake);
                    vibrator.vibrate(120);
                    return;
                }
                if (!checkDistric()) {
                    costEditText.setAnimation(animshake);
                    costEditText.startAnimation(animshake);
                    vibrator.vibrate(120);
                    return;
                }
                if (!checkArea()) {
                    costEditText.setAnimation(animshake);
                    costEditText.startAnimation(animshake);
                    vibrator.vibrate(120);
                    return;
                }
                if (!checkLat()) {
                    costEditText.setAnimation(animshake);
                    costEditText.startAnimation(animshake);
                    vibrator.vibrate(120);
                    return;
                }
                if (!checkLng()) {
                    costEditText.setAnimation(animshake);
                    costEditText.startAnimation(animshake);
                    vibrator.vibrate(120);
                    return;
                }

                name_storeInputLayout.setErrorEnabled(false);
                repairInputLayout.setErrorEnabled(false);
                start_timeInputLayout.setErrorEnabled(false);
                end_timeInputLayout.setErrorEnabled(false);
                costInputLayout.setErrorEnabled(false);
                home_numInputLayout.setErrorEnabled(false);
                streetInputLayout.setErrorEnabled(false);
                districInputLayout.setErrorEnabled(false);
                areaInputLayout.setErrorEnabled(false);
                latInputLayout.setErrorEnabled(false);
                lngInputLayout.setErrorEnabled(false);
                Toast.makeText(getApplicationContext(), "You are successfully Registered !!", Toast.LENGTH_SHORT).show();
            }

            private boolean checkLng() {
                if (lngEditText.getText().toString().trim().isEmpty()) {
                    lngInputLayout.setErrorEnabled(true);
                    lngEditText.setError(getString(R.string.err_msg_start_location));
                    requestFocus(lngEditText);
                    return false;
                }
                lngInputLayout.setErrorEnabled(false);
                return true;
            }

            private boolean checkLat() {
                if (latEditText.getText().toString().trim().isEmpty()) {
                    latInputLayout.setErrorEnabled(true);
                    latEditText.setError(getString(R.string.err_msg_start_location));
                    requestFocus(latEditText);
                    return false;
                }
                latInputLayout.setErrorEnabled(false);
                return true;
            }

            private boolean checkArea() {
                if (areaEditText.getText().toString().trim().isEmpty()) {
                    areaInputLayout.setErrorEnabled(true);
                    areaEditText.setError(getString(R.string.err_msg_required));
                    requestFocus(areaEditText);
                    return false;
                }
                areaInputLayout.setErrorEnabled(false);
                return true;
            }

            private boolean checkDistric() {
                if (districEditText.getText().toString().trim().isEmpty()) {
                    districInputLayout.setErrorEnabled(true);
                    districEditText.setError(getString(R.string.err_msg_required));
                    requestFocus(districEditText);
                    return false;
                }
                districInputLayout.setErrorEnabled(false);
                return true;
            }

            private boolean checkStreet() {
                if (streetEditText.getText().toString().trim().isEmpty()) {
                    streetInputLayout.setErrorEnabled(true);
                    streetEditText.setError(getString(R.string.err_msg_required));
                    requestFocus(streetEditText);
                    return false;
                }
                streetInputLayout.setErrorEnabled(false);
                return true;
            }

            private boolean checkHome_number() {
                if (home_numberEditText.getText().toString().trim().isEmpty()) {
                    home_numInputLayout.setErrorEnabled(true);
                    home_numberEditText.setError(getString(R.string.err_msg_required));
                    requestFocus(home_numberEditText);
                    return false;
                }
                home_numInputLayout.setErrorEnabled(false);
                return true;
            }

            private boolean checkCost() {
                if (costEditText.getText().toString().trim().isEmpty()) {
                    costInputLayout.setErrorEnabled(true);
                    costEditText.setError(getString(R.string.err_msg_required));
                    requestFocus(costEditText);
                    return false;
                }
                costInputLayout.setErrorEnabled(false);
                return true;
            }


            private boolean checkName() {
                if (name_storeEditText.getText().toString().trim().isEmpty()) {
                    name_storeInputLayout.setErrorEnabled(true);
                    name_storeEditText.setError(getString(R.string.err_msg_required));
                    requestFocus(name_storeEditText);
                    return false;
                }
                name_storeInputLayout.setErrorEnabled(false);
                return true;
            }

            private boolean checkRepair() {
//                String repair = repairEditText.getText().toString().trim();
                if (repairEditText.getText().toString().trim().isEmpty()) {
                    repairInputLayout.setErrorEnabled(true);
                    repairEditText.setError(getString(R.string.err_msg_required));
                    requestFocus(repairEditText);
                    return false;
                }
                repairInputLayout.setErrorEnabled(false);
                return true;
            }

            private boolean checkStart_Time() {
                if (start_timeEditText.getText().toString().trim().isEmpty()) {
                    start_timeInputLayout.setErrorEnabled(true);
                    start_timeInputLayout.setError(getString(R.string.err_msg_required));
                    requestFocus(start_timeEditText);
                    return false;
                }
                start_timeInputLayout.setErrorEnabled(false);
                return true;
            }
            private boolean checkEnd_time() {
                if (end_timeEditText.getText().toString().trim().isEmpty()) {
                    end_timeInputLayout.setErrorEnabled(true);
                    end_timeEditText.setError(getString(R.string.err_msg_required));
                    requestFocus(end_timeEditText);
                    return false;
                }
                end_timeInputLayout.setErrorEnabled(false);
                return true;
            }


//            private  boolean isValidRepair(String repair) {
//                return !TextUtils.isEmpty(repair) && android.util.Patterns.EMAIL_ADDRESS.matcher(repair).matches();
//            }

            private void requestFocus(View view) {
                if (view.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Main_Technician.class));
            }
        });
    }

    private void bildwidget() {
        name_storeInputLayout = (TextInputLayout) findViewById(R.id.name);
        repairInputLayout = (TextInputLayout) findViewById(R.id.repair_equipment);
        start_timeInputLayout = (TextInputLayout) findViewById(R.id.time_work_begin);
        end_timeInputLayout = (TextInputLayout) findViewById(R.id.time_work_end);
        costInputLayout = (TextInputLayout) findViewById(R.id.cost_begin);
        home_numInputLayout = (TextInputLayout) findViewById(R.id.house_number);
        streetInputLayout = (TextInputLayout) findViewById(R.id.streert_address);
        districInputLayout = (TextInputLayout) findViewById(R.id.distric_address);
        areaInputLayout = (TextInputLayout) findViewById(R.id.area_address);
        latInputLayout = (TextInputLayout) findViewById(R.id.lat_location);
        lngInputLayout = (TextInputLayout) findViewById(R.id.lng_location);
        name_storeEditText = (EditText) findViewById(R.id.name_store);
        repairEditText = (EditText) findViewById(R.id.repair);
        start_timeEditText = (EditText) findViewById(R.id.start_time);
        end_timeEditText = (EditText) findViewById(R.id.end_time);
        costEditText = (EditText) findViewById(R.id.begin_cost);
        home_numberEditText = (EditText) findViewById(R.id.home_number);
        districEditText = (EditText) findViewById(R.id.district);
        streetEditText = (EditText) findViewById(R.id.street);
        areaEditText = (EditText) findViewById(R.id.area);
        latEditText = (EditText) findViewById(R.id.lat_store);
        lngEditText = (EditText) findViewById(R.id.lng_store);
        locationButton = (Button) findViewById(R.id.location_store);
        okButton = (Button) findViewById(R.id.ok);
        backButton = (Button) findViewById(R.id.back);
    }
}
