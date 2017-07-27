package com.example.khanh.listenwritedemo.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.module.Language;
import com.example.khanh.listenwritedemo.module.Section;

import java.util.List;

import static com.example.khanh.listenwritedemo.fragment.FramentListenWrite.MyPREFERENCES;

/**
 * Created by Administrator on 7/27/2017.
 */

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.MyViewHolder> {

    private List<Language> sectionList;
    CallBack callBack;

    public LanguageAdapter(List<Language> sectionList, CallBack callBack) {
        this.sectionList = sectionList;
        this.callBack = callBack;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtlanguage;
        public MyViewHolder(View view) {
            super(view);
            txtlanguage = (TextView) view.findViewById(R.id.txtlanguage);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_language, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Language language=sectionList.get(position);
        holder.txtlanguage.setText(language.getName());
        holder.txtlanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.OnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }


    public interface CallBack {
        void OnClick(int index);
    }

}
