package com.example.commuteeazy.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.commuteeazy.R;
import com.example.commuteeazy.activities.HomePage;
import com.example.commuteeazy.fragments.OperatorsInCounty;

public class GridAdapter extends BaseAdapter {

    private int[] images;
    private String[] countyNames;
    private HomePage context;
    private LayoutInflater inflater;
    private Activity parent;

    public GridAdapter(int[] images, String[] countyNames, Context context) {
        this.images = images;
        this.countyNames = countyNames;
        this.context = (HomePage)context;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        convertView = inflater.inflate(R.layout.grid_element,null);
        TextView tv = convertView.findViewById(R.id.county_name);
        tv.setText(countyNames[position]);
        ImageView iv = convertView.findViewById(R.id.county_image);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                OperatorsInCounty operators = new OperatorsInCounty();
                transaction.replace(R.id.layout_per_county,operators);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        iv.setImageResource(images[position]);
        return convertView;
    }
}
