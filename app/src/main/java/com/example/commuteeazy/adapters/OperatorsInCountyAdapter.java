package com.example.commuteeazy.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.commuteeazy.R;
import com.example.commuteeazy.activities.ProfilePage;

import java.util.List;

public class OperatorsInCountyAdapter extends RecyclerView.Adapter<OperatorsInCountyAdapter.ViewHolder> {

    List<String> names;
    Context context;

    public OperatorsInCountyAdapter(List<String> names, Context context) {
        this.names = names;
        this.context = context;
    }

    @NonNull
    @Override
    public OperatorsInCountyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.operator_in_county_recyclerview_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv.setText(names.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfilePage.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv = itemView.findViewById(R.id.operators_in_county_name);
        }
    }

}
