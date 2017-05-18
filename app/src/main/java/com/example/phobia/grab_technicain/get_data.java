package com.example.phobia.grab_technicain;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by Phobia on 5/15/2017.
 */

public class get_data extends AsyncTask<String,Void,String> {
    private Context context;
    //Constructor
    public get_data(Context context) {
        this.context = context;
    }//end

    @Override
    protected String doInBackground(String... strings) {

        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[0]).build();
            Response response = okHttpClient.newCall(request).execute();
            String resJSON = response.body().string();
            Log.d("get_data", "get_data==>" + resJSON);
            return resJSON;
        } catch (Exception e) {
            Log.d("get_data", "get_data==>" + e.toString());
            return null;
        }
    }
}
