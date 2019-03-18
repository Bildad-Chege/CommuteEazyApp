package com.example.commuteeazy.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.commuteeazy.Config;
import com.example.commuteeazy.DO.Feed;
import com.example.commuteeazy.R;
import com.example.commuteeazy.adapters.UpdatesAdapter;
import com.example.commuteeazy.network.UserClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Updates extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public Updates() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_updates, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.updates);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        new LoadFeeds(getActivity(),recyclerView).execute();
        return view;
    }

    public class LoadFeeds extends AsyncTask<String,String,String>{

        private ProgressDialog dialog = new ProgressDialog(getActivity());
        private Context context;
        private RecyclerView recyclerView;

        public LoadFeeds(Context context, RecyclerView view) {
            this.context = context;
            this.recyclerView = view;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Loading feeds. Please Wait...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Config.hostName).addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            UserClient client = retrofit.create(UserClient.class);
            Call<List<Feed>> call = client.getAllUpdates();
            call.enqueue(new Callback<List<Feed>>() {
                @Override
                public void onResponse(Call<List<Feed>> call, Response<List<Feed>> response) {
                    List<Feed> feeds = response.body();
                    UpdatesAdapter adapter = new UpdatesAdapter(context,feeds);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Feed>> call, Throwable t) {
                    Toast.makeText(context,"Check your network",Toast.LENGTH_LONG).show();
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
