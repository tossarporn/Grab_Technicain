package com.example.phobia.grab_technicain;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jibble.simpleftp.SimpleFTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class Build_Store extends AppCompatActivity {
    private EditText name_storeEditText,repairEditText,start_timeEditText,end_timeEditText,costEditText,home_numberEditText,
                     streetEditText,districEditText,areaEditText,latEditText,lngEditText,tel_EditText;
    private Button okButton,backButton,locationButton;
    private TextInputLayout name_storeInputLayout,repairInputLayout,start_timeInputLayout,end_timeInputLayout,costInputLayout,home_numInputLayout
                            ,streetInputLayout,districInputLayout,areaInputLayout,latInputLayout, lngInputLayout,telInputLayout;
    private ImageView store_ImageView;
    private Boolean aBoolean = true;
    private String pathImageString, nameImageString;
    private Uri uri;
    private Vibrator vibrator;
    private String TAG = "Check Upload";
    private String TAG_SPINNER = "getSpinner";
    private Spinner repair_Spinner;
    private Spinner Area_Spinner;
    private String name_store,equipmentString,telString,time_startString,time_endString,begincostString,
                  home_memberString,streetString,district,areaString,latString,lngString;


    ArrayList<String> item = new ArrayList<>();
    ArrayList<String> item_index = new ArrayList<>();

    ArrayList<String> item_equipment = new ArrayList<>();
    ArrayList<String> index_equipment = new ArrayList<>();

    String lattitude ;
    String longitude;
    Animation animshake;
    static final int Request_Location = 1;
    LocationManager locationManager;

    myconfig config = new myconfig();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build__store);
        animshake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        bildwidget();
        buttoncontroller();
        ImageController();

        //ShowSpinner
        Getspinner_equipment();
        Getspinner_area();



    }


    private void Getspinner_area() {
        get_data data_area = new get_data(getApplicationContext());
        data_area.execute(config.getGet_Area());
        try {
            String str_area = data_area.get().toString();
            JSONArray get_all_area = new JSONArray(str_area);
            Log.d("TAG_SPINNER", "getSpinner_Area==>"+get_all_area.toString());
            for (int i = 0; i<get_all_area.length();i++) {
                JSONObject json_getArea = get_all_area.getJSONObject(i);

                item.add(json_getArea.getString("area_name"));
                item_index.add(json_getArea.getString("id"));
                Log.d("TAG_SPINNER", "getSpinner_Area==>"+get_all_area.toString());
            }
        }

        catch (Exception e) {
        }
        ArrayAdapter<String> areaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,item);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Area_Spinner.setAdapter(areaAdapter);

        Area_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Area_Spinner.setPrompt("เลือกเขตที่ต้องการ");
                Toast.makeText(Build_Store.this,"id"+item_index.get(i),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
                alertDialog.myDialog("กรุณากรอกข้อมูลให้ครบ","กรุณาเลือกเขต");
            }
        });
    }

    private void Getspinner_equipment() {
        get_data get_equipment = new get_data(getApplicationContext());
        get_equipment.execute(config.getEquipment());
        try {

            String str_equipment = get_equipment.get().toString();
            JSONArray all_areaJsonArray = new JSONArray(str_equipment);
            Log.d("TAG_SPINNER", "getSpinner_Equipment==>" + all_areaJsonArray.toString());

            for (int i = 0;i<all_areaJsonArray.length();i++) {
                JSONObject jsoon_areaObject = all_areaJsonArray.getJSONObject(i);

               item_equipment.add(jsoon_areaObject.getString("type_name"));
                index_equipment.add(jsoon_areaObject.getString("id"));

                Log.d("TAG_SPINNER", "getSpinner==>" +jsoon_areaObject.toString());
            }
        }

        catch (Exception dialog) {

        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,item_equipment);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repair_Spinner.setAdapter(adapter);

        repair_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String repair = repair_Spinner.getSelectedItem().toString();
                repair_Spinner.setPrompt("เลือกประเภทเครื่องใช้ไฟฟ้า");
                Toast.makeText(Build_Store.this,"id"+index_equipment.get(position),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
                alertDialog.myDialog("กรุณากรอกข้อมูลให้ครบ","กรุณาเลือกเขต");
            }
        });
    }

    private void ImageController() {
        store_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //choose image from SD card
                Intent intent = new Intent(Intent.ACTION_PICK);//Choose Image Or Picture from SDCard
                intent.setType("image/*");//Choose Image Or Picture all Type
                startActivityForResult(Intent.createChooser(intent,"โปรดเลือกรูปภาพ"),1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            aBoolean = false;
            uri = data.getData();
                //SetUp Image Choose To ImageView
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                    store_ImageView.setImageBitmap(bitmap);
            }
                catch (Exception e) {
                    e.printStackTrace();
            }
                //Find Path Image Choose
            String[] strings = new String[]{MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(uri, strings, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                pathImageString = cursor.getString(index);

            } else {
                pathImageString = uri.getPath();
            }
            Log.d("pathImage", "pathImage ==> " + pathImageString);
        }
    }

    private void buttoncontroller() {
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                submitform();

            }
            private void submitform() {

                if (!checkName()||!checkTel()||!checkStart_Time()||!checkEnd_time()||!checkCost()
                        ||!checkHome_number()||!checkStreet()||!checkDistric()||!checkLat()||!checkLng()) {
                    name_storeInputLayout.setErrorEnabled(false);
                    start_timeInputLayout.setErrorEnabled(false);
                    end_timeInputLayout.setErrorEnabled(false);
                    costInputLayout.setErrorEnabled(false);
                    home_numInputLayout.setErrorEnabled(false);
                    streetInputLayout.setErrorEnabled(false);
                    districInputLayout.setErrorEnabled(false);
                    latInputLayout.setErrorEnabled(false);
                    lngInputLayout.setErrorEnabled(false);
                    telInputLayout.setErrorEnabled(false);
                }//CheckErrorEditText

//                else if (repiarSpinner.getSelectedItem()=="Select Please") {
//                    MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
//                    alertDialog.myDialog("กรุณากรอกข้อมูลให้ครบ","กรุณาเลือกเขต");
//
//                }
                else if (aBoolean) {
                    MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
                    alertDialog.myDialog("ยังไม่เลือกรูปภาพ", "กรุณาเลือกรูปภาพ");
                    }//CheckImage

                    else {
//                        Toast.makeText(getApplicationContext(), "สมัครสมาชิคสำเร็จ", Toast.LENGTH_SHORT).show();
//                        UploadToServer();
                    myconfig myconfig = new myconfig();
                    get_data add_technician = new get_data(Build_Store.this);

                    try {
                        add_technician.execute(myconfig.getDetail_technician()
                                +"?name_store="+checkName()+"&"
                                +"ref_type="+repair_Spinner+"&"
                                +"ref_area="+Area_Spinner+"&"
                                +"home_number="+checkHome_number()+"&"
                                +"street="+checkStreet()+"&"
                                +"distric="+checkDistric()+"&"
                                +"imaeg_store="+store_ImageView+"&"
                                +"time_start="+checkStart_Time()+"&"
                                +"time_end="+checkEnd_time()+"&"
                                +"tel="+checkTel()+"&"
                                +"cost_begin="+checkCost()+"&"
                                +"lat="+checkLat()+"&"
                                +"lng="+checkLng()
                        );

                        String technician = add_technician.get().toString();
                        JSONObject statusObject = new JSONObject(technician);
                        Boolean statusBoolean = statusObject.getBoolean("status");
                        String message = statusObject.getString("message");

                        if (statusBoolean == true) {

                            Toast.makeText(Build_Store.this,message,Toast.LENGTH_LONG).show();
                        }

                        else {
                            Toast.makeText(Build_Store.this,message,Toast.LENGTH_LONG).show();
                        }

                    }
                    catch (Exception e) {
//                        Toast.makeText(Build_Store.this,"Add_Fail",Toast.LENGTH_LONG).show();
//                        Log.d("Add_Technician","Detail==>"+e.toString());
                    }

                    }//UploadToServer}
                }//Method submitform


            private boolean checkTel() {
                if (tel_EditText.getText().toString().trim().isEmpty()) {
                    telInputLayout.setErrorEnabled(true);
                    MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
                    alertDialog.myDialog("กรุณากรอกข้อมูลให้ครบ","กรุณากรอกเบอร์โทร");
                    requestFocus(tel_EditText);
                    return false;
                }
                telInputLayout.setErrorEnabled(false);
                return true;
            }


            private boolean checkLng() {
                if (lngEditText.getText().toString().trim().isEmpty()) {
                    lngInputLayout.setErrorEnabled(true);
                    MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
                    alertDialog.myDialog("กรุณากรอกข้อมูลให้ครบ","กรุณากดดูตำแหน่ง");
                    requestFocus(lngEditText);
                    return false;
                }
                lngInputLayout.setErrorEnabled(false);
                return true;
            }

            private boolean checkLat() {
                if (latEditText.getText().toString().trim().isEmpty()) {
                    latInputLayout.setErrorEnabled(true);
                    MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
                    alertDialog.myDialog("กรุณากรอกข้อมูลให้ครบ","กรุณากดดูตำแหน่ง");
                    requestFocus(latEditText);
                    return false;
                }
                latInputLayout.setErrorEnabled(false);
                return true;
            }

//            private boolean checkArea() {
//                if (areaEditText.getText().toString().trim().isEmpty()) {
//                    areaInputLayout.setErrorEnabled(true);
//                    MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
//                    alertDialog.myDialog("กรุณากรอกข้อมูลให้ครบ","กรุณากรอกเขต");
//                    requestFocus(areaEditText);
//                    return false;
//                }
//                areaInputLayout.setErrorEnabled(false);
//                return true;
//            }

            private boolean checkDistric() {
                if (districEditText.getText().toString().trim().isEmpty()) {
                    districInputLayout.setErrorEnabled(true);
                    MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
                    alertDialog.myDialog("กรุณากรอกข้อมูลให้ครบ","กรุณากรอกแขวง");
                    requestFocus(districEditText);
                    return false;
                }
                districInputLayout.setErrorEnabled(false);
                return true;
            }

            private boolean checkStreet() {
                if (streetEditText.getText().toString().trim().isEmpty()) {
                    streetInputLayout.setErrorEnabled(true);
                    MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
                    alertDialog.myDialog("กรุณากรอกข้อมูลให้ครบ","กรุณากรอกชื่อถนน");
                    requestFocus(streetEditText);
                    return false;
                }
                streetInputLayout.setErrorEnabled(false);
                return true;
            }

            private boolean checkHome_number() {
                if (home_numberEditText.getText().toString().trim().isEmpty()) {
                    home_numInputLayout.setErrorEnabled(true);
                    MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
                    alertDialog.myDialog("กรุณากรอกข้อมูลให้ครบ","กรุณากรอกเลขที่บ้าน");
                    requestFocus(home_numberEditText);
                    return false;
                }
                home_numInputLayout.setErrorEnabled(false);
                return true;
            }

            private boolean checkCost() {
                if (costEditText.getText().toString().trim().isEmpty()) {
                    costInputLayout.setErrorEnabled(true);
                    MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
                    alertDialog.myDialog("กรุณากรอกข้อมูลให้ครบ","กรุณากรอกราคาเริ่มต้น");
                    requestFocus(costEditText);
                    return false;
                }
                costInputLayout.setErrorEnabled(false);
                return true;
            }

            private boolean checkName() {
                if (name_storeEditText.getText().toString().trim().isEmpty()) {
                    name_storeInputLayout.setErrorEnabled(true);
                    MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
                    alertDialog.myDialog("กรุณากรอกข้อมูลให้ครบ","กรุณากรอกชื่อร้าน");
                    requestFocus(name_storeEditText);
                    return false;
                }
                name_storeInputLayout.setErrorEnabled(false);
                return true;
            }

//            private boolean checkRepair() {
////                int selectedItemOfMySpinner = repiarSpinner.getSelectedItemPosition();
////                String actualPositionOfMySpinner = (String) repiarSpinner.getItemAtPosition(selectedItemOfMySpinner);
////                if (actualPositionOfMySpinner.isEmpty()) {
//////                    MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
//////                    alertDialog.myDialog("กรุณากรอกข้อมูลให้ครบ", "5678");
////
////                    requestFocus(repiarSpinner);
//                    return false;
//                }
//                start_timeInputLayout.setErrorEnabled(false);
//                return true;
//            }

            private boolean checkStart_Time() {
                if (start_timeEditText.getText().toString().trim().isEmpty()) {
                    start_timeInputLayout.setErrorEnabled(true);
                    MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
                    alertDialog.myDialog("กรุณากรอกข้อมูลให้ครบ","กรุณากรอกเวลาเปิด");
                    requestFocus(start_timeEditText);
                    return false;
                }
                start_timeInputLayout.setErrorEnabled(false);
                return true;
            }
            private boolean checkEnd_time() {
                if (end_timeEditText.getText().toString().trim().isEmpty()) {
                    end_timeInputLayout.setErrorEnabled(true);
                    MyAlertDialog alertDialog = new MyAlertDialog(Build_Store.this);
                    alertDialog.myDialog("กรุณากรอกข้อมูลให้ครบ","กรุณากรอกเวลาปิด");
                    requestFocus(end_timeEditText);
                    return false;
                }
                end_timeInputLayout.setErrorEnabled(false);
                return true;
            }



//            private void UploadToServer() {
//                try {
//                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
//                            .Builder()
//                            .permitAll()
//                            .build();
//                    StrictMode.setThreadPolicy(policy);//Request ThreadPolicy
//
//                    SimpleFTP simpleFTP = new SimpleFTP();
//                    simpleFTP.connect("127.0.0.1",21,"root","root");
//                    simpleFTP.bin();
//                    simpleFTP.cwd("img_store");
//                    simpleFTP.stor(new File(pathImageString));
//                    simpleFTP.disconnect();
//
//                    nameImageString = "http://127.0.0.1:8081/Final_Project/img_store"+pathImageString.substring(pathImageString.lastIndexOf("/"));
//                    Log.d(TAG,"Image==>"+nameImageString);
//
//                }
//
//                catch (Exception e) {
//                    Log.d(" SimpleFTP", " SimpleFTP upload ==> " + e.toString());
//                }
            //}//Method UploadToServer

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
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    buildAlertMessageNoGps();
                }

                else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    getLocation();
                }
            }

            private void getLocation() {
                if (ActivityCompat.checkSelfPermission(Build_Store.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                        (Build_Store.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(Build_Store.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_Location);

                }

                else {
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);
                        if (location!=null) {
                            Double lat = location.getLatitude();
                            Double lng = location.getLongitude();
                             lattitude = String.valueOf(lat);
                             longitude = String.valueOf(lng);
                            latEditText.setText(" "+lat);
                            lngEditText.setText(" "+lng);
                        }
                        else if (location1 != null) {
                            Double lat = location.getLatitude();
                            Double lng = location.getLongitude();
                             lattitude = String.valueOf(lat);
                             longitude = String.valueOf(lng);
                            latEditText.setText("" + lat);
                            lngEditText.setText("" + lng);
                        }
                          else if (location2 != null) {
                            Double lat = location.getLatitude();
                            Double lng = location.getLongitude();
                             lattitude = String.valueOf(lat);
                             longitude = String.valueOf(lng);
//                                Log.d("location", "location==>"+lat.toString());
                            latEditText.setText("" + lat);
                            lngEditText.setText("" + lng);
                        }
                          else {
                            Toast.makeText(Build_Store.this, "ไม่สามรถระบุตำแหน่งของคุณได้", Toast.LENGTH_SHORT).show();
                        }
                }
            }

            private void buildAlertMessageNoGps() {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Build_Store.this);
                builder.setMessage("ต้องการระบุตำแหน่งของคุณ")
                        .setCancelable(false)
                        .setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            }
                        })
                        .setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                dialog.cancel();
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();
        }
        });
    }

    private void bildwidget() {
        name_storeInputLayout = (TextInputLayout) findViewById(R.id.name);
        start_timeInputLayout = (TextInputLayout) findViewById(R.id.time_work_begin);
        end_timeInputLayout = (TextInputLayout) findViewById(R.id.time_work_end);
        costInputLayout = (TextInputLayout) findViewById(R.id.cost_begin);
        home_numInputLayout = (TextInputLayout) findViewById(R.id.house_number);
        streetInputLayout = (TextInputLayout) findViewById(R.id.streert_address);
        districInputLayout = (TextInputLayout) findViewById(R.id.distric_address);
//        areaInputLayout = (TextInputLayout) findViewById(R.id.area_spinner);
        latInputLayout = (TextInputLayout) findViewById(R.id.lat_location);
        lngInputLayout = (TextInputLayout) findViewById(R.id.lng_location);
        telInputLayout = (TextInputLayout) findViewById(R.id.Tel);

        repair_Spinner = (Spinner) findViewById(R.id.Maintenance_spinner);
        Area_Spinner = (Spinner) findViewById(R.id.area_spinner);

        name_storeEditText = (EditText) findViewById(R.id.name_store);
        start_timeEditText = (EditText) findViewById(R.id.start_time);
        end_timeEditText = (EditText) findViewById(R.id.end_time);
        costEditText = (EditText) findViewById(R.id.begin_cost);
        home_numberEditText = (EditText) findViewById(R.id.home_number);
        districEditText = (EditText) findViewById(R.id.district);
        streetEditText = (EditText) findViewById(R.id.street);
//        areaEditText = (EditText) findViewById(R.id.area);
        latEditText = (EditText) findViewById(R.id.lat_store);
        lngEditText = (EditText) findViewById(R.id.lng_store);
        tel_EditText = (EditText) findViewById(R.id.Tel_technician);
        locationButton = (Button) findViewById(R.id.location_store);
        store_ImageView = (ImageView) findViewById(R.id.picture_store);

        okButton = (Button) findViewById(R.id.ok);
        backButton = (Button) findViewById(R.id.back);
    }
}
