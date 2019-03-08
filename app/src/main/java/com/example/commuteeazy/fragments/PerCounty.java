package com.example.commuteeazy.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.commuteeazy.R;
import com.example.commuteeazy.adapters.GridAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerCounty extends Fragment {

    private GridView counties;
    private GridAdapter adapter;
    private String[] names = {"Nairobi","Mombasa","Kisumu","Nakuru","Garissa"};
    private int[] images = {R.mipmap.nairobi_layer,R.mipmap.mombasa_layer,R.mipmap.kisumu_layer,R.mipmap.nakuru_layer,R.mipmap.garissa_layer};

    public PerCounty() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_per_county2, container, false);
        counties = view.findViewById(R.id.counties);
        adapter = new GridAdapter(images,names,getActivity());
        counties.setAdapter(adapter);
        return view;
    }

}
