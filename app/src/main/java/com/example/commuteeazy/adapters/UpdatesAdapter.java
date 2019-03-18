package com.example.commuteeazy.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.commuteeazy.DO.Feed;
import com.example.commuteeazy.R;


import java.util.List;

public class UpdatesAdapter extends RecyclerView.Adapter<UpdatesAdapter.ViewHolder> {

    Context context;
    List<Feed> feeds;

    public UpdatesAdapter(Context context, List<Feed> feeds) {
        this.context = context;
        this.feeds = feeds;
    }

    @NonNull
    @Override
    public UpdatesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.update,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.feed.setText(feeds.get(i).getFeed());
        if(feeds.get(i).getUser()!=null){
            viewHolder.postBy.setText(feeds.get(i).getUser().getFirstName());
        } else if(feeds.get(i).getOperator()!=null) {
            viewHolder.postBy.setText(feeds.get(i).getOperator().getName());
        }
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView feed;
        TextView postBy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            feed = (TextView) itemView.findViewById(R.id.update_text);
            postBy = (TextView) itemView.findViewById(R.id.name);
        }
    }

}
