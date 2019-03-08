package com.example.commuteeazy.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commuteeazy.R;
import com.example.commuteeazy.adapters.OperatorsInCountyAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OperatorsInCounty extends Fragment {

    RecyclerView operatorsInCounty;
    OperatorsInCountyAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<String> names = Arrays.asList("Operator1","Operator2","Operator3","Operator4","Operator5",
            "Operator1","Operator2","Operator3","Operator4","Operator5","Operator1","Operator2","Operator3","Operator4","Operator5");

    public OperatorsInCounty() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_operators_in_county, container, false);
        operatorsInCounty = (RecyclerView) view.findViewById(R.id.operators_in_county);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new OperatorsInCountyAdapter(names,getActivity());
        operatorsInCounty.setLayoutManager(layoutManager);
        operatorsInCounty.setAdapter(adapter);
        return view;
    }

}
