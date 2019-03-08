package com.example.commuteeazy.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commuteeazy.R;
import com.example.commuteeazy.activities.HomePage;
import com.example.commuteeazy.adapters.ShowAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowOperators extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private ShowAdapter adapter;
    private List<String> names = Arrays.asList("Latema","Starbus","Molo-Line","Dakabo","Blueline",
            "Latema","Starbus","Molo-Line","Dakabo","Blueline",
            "Latema","Starbus","Molo-Line","Dakabo","Blueline");

    public ShowOperators() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_operators, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new ShowAdapter(names, getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }

}
