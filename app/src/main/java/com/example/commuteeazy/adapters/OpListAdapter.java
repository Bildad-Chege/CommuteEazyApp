package com.example.commuteeazy.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.commuteeazy.R;

import java.util.List;

public class OpListAdapter extends RecyclerView.Adapter<OpListAdapter.OpListViewHolder> {

    List<String> operatorNames;
    LayoutInflater inflater;
    private AdapterView.OnItemClickListener listener;

    public OpListAdapter(Context context,List<String> operatorNames) {
        this.operatorNames = operatorNames;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public OpListAdapter.OpListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.operator_element,viewGroup);
        return new OpListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OpListViewHolder opListViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return operatorNames.size();
    }

    public class OpListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tv;
        public OpListViewHolder(View items) {
            super(items);
            tv = items.findViewById(R.id.operator_name);
            items.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
