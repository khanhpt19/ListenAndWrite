package com.example.khanh.listenwritedemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khanh.listenwritedemo.R;

import java.util.List;

/**
 * Created by Administrator on 8/8/2017.
 */

public class ListAdapterWrong extends RecyclerView.Adapter<ListAdapterWrong.MyViewHolder> {
    private List<String> listStudy;
    private Context context;
    CallBack callBack;

    public ListAdapterWrong(List<String> listStudy,CallBack callBack) {
        this.listStudy = listStudy;
        this.callBack=callBack;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_list_text_wrong;
        public MyViewHolder(View view) {
            super(view);
            txt_list_text_wrong = (TextView) view.findViewById(R.id.txt_list_text_wrong);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_study_wrong, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.txt_list_text_wrong.setText(listStudy.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.OnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listStudy.size();
    }

    public interface CallBack {
        void OnClick(int indexx);
    }
}