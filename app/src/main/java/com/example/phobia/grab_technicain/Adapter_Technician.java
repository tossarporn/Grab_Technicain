package com.example.phobia.grab_technicain;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Phobia on 9/28/2017.
 */

public class Adapter_Technician extends BaseAdapter {

    private String[] detail_name_store, detail_type;
    private Context context;
    private TextView name_store,date,price, type;
    private ImageView imageView;
    private Button show_detail_Button;
    private RatingBar ratingBar;

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

        //bind widget
        name_store = (TextView) view1.findViewById(R.id.name_store);
        type = (TextView) view1.findViewById(R.id.type);
        imageView = (ImageView) view1.findViewById(R.id.launc);
        ratingBar = (RatingBar) view1.findViewById(R.id.GetRatBar);

        //Click ButtonListView
        show_detail_Button = (Button) view1.findViewById(R.id.button_detail);
        show_detail_Button.setFocusable(false);
        show_detail_Button.setClickable(false);

        //show data
        name_store.setText(detail_name_store[i]);
        type.setText(detail_type[i]);
        return view1;
    }

}
