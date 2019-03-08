package com.example.commuteeazy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.commuteeazy.R;

import java.util.List;

public class listAdapter extends BaseAdapter {

    List<String> placeNames;
    Context context;
    LayoutInflater inflater;


    public listAdapter(List<String> placeNames, Context context) {
        this.placeNames = placeNames;
        this.context = context;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return placeNames.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.list_element,null);
        TextView tv = convertView.findViewById(R.id.place_name);
        tv.setText(placeNames.get(position));
        return convertView;
    }
}
