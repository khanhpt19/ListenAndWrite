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

public class ResultAdapter2 extends RecyclerView.Adapter<ResultAdapter2.MyViewHolder> {

    private List<String> sectionList;

    public ResultAdapter2(List<String> sectionList) {
        this.sectionList = sectionList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtresult2;

        public MyViewHolder(View view) {
            super(view);
            txtresult2 = (TextView) view.findViewById(R.id.txtresult2);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result2, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txtresult2.setText((position+1)+": "+sectionList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }
}