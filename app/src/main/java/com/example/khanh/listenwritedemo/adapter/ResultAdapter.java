package com.example.khanh.listenwritedemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khanh.listenwritedemo.R;

import java.util.List;

/**
 * Created by khanh on 7/26/2017.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {

    private List<String> sectionList;

    public ResultAdapter(List<String> sectionList) {
        this.sectionList = sectionList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtresult;

        public MyViewHolder(View view) {
            super(view);
            txtresult = (TextView) view.findViewById(R.id.txtresult);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txtresult.setText((position+1)+": "+sectionList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }
}