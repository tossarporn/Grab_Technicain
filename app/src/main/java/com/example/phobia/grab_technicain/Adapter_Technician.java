package com.example.phobia.grab_technicain;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Phobia on 9/28/2017.
 */

public class Adapter_Technician extends BaseAdapter {

   private String[] detail_name_store, detail_type;
    private Context context;
    private TextView date,price, type;

    public Adapter_Technician(String[] detail_name_store, String[] detail_type, Context context) {

        this.detail_name_store = detail_name_store;
        this.detail_type = detail_type;
        this.context = context;
    }

    @Override
    public int getCount() {
        return detail_name_store.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.activity_detail_technician, viewGroup, false);
        type = (TextView) view1.findViewById(R.id.type);
        type.setText(detail_type[i]);
        return view1;
    }
}
