package com.example.commuteeazy.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.commuteeazy.R;
import com.example.commuteeazy.adapters.listAdapter;

import java.util.Arrays;
import java.util.List;

public class ProfilePage extends AppCompatActivity {

    List<String> placeNames = Arrays.asList("Place one","Place two","Place three","Place four","Terminus one","Terminus two");
    ListAdapter adapter;
    ListView views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        views = findViewById(R.id.places);
        adapter = new listAdapter(placeNames,this);
        views.setAdapter(adapter);
    }
}
