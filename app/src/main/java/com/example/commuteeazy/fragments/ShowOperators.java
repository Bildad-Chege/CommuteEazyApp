package com.example.commuteeazy.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.commuteeazy.Config;
import com.example.commuteeazy.DO.Operator;
import com.example.commuteeazy.R;
import com.example.commuteeazy.activities.HomePage;
import com.example.commuteeazy.adapters.ShowAdapter;
import com.example.commuteeazy.network.UserClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowOperators extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;


    public ShowOperators() {
        // Required empty public constructor
    }

//    public static void setOperators(List<Operator> operators) {
//        ShowOperators.operators = operators;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_operators, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        new DisplayData(recyclerView,getActivity()).execute();
        return view;
    }

    public class DisplayData extends AsyncTask<String,String,String>{

        private ProgressDialog dialog = new ProgressDialog(getActivity());
        private RecyclerView recyclerView;
        private Context context;

        public DisplayData() {

        }

        public DisplayData(RecyclerView recyclerView, Context context) {
            this.recyclerView = recyclerView;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Loading data. Please wait...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            Retrofit.Builder builder= new Retrofit.Builder().baseUrl(Config.hostName).addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            UserClient client = retrofit.create(UserClient.class);
            Call<List<Operator>> call = client.getAllOperators();
            call.enqueue(new Callback<List<Operator>>() {
                @Override
                public void onResponse(Call<List<Operator>> call, Response<List<Operator>> response) {
                    List<Operator> operators = response.body();
                    ShowAdapter adapter = new ShowAdapter(operators,context);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Operator>> call, Throwable t) {
                    Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
        }
    }

}
