package com.example.inno_09.eventbusstudy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by INNO-09 on 2016-05-16.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> items = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).setMyViewholder(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void fillList(List<String> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvText;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvText = (TextView)itemView.findViewById(R.id.tvText);
        }

        public void setMyViewholder(String str) {
            tvText.setText(str);
        }

    }
}
