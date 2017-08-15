package com.example.khanh.listenwritedemo.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.fragment.FramentListenWrite;
import com.example.khanh.listenwritedemo.helper.CircleNetworkImageView;
import com.example.khanh.listenwritedemo.helper.ImageLoaderUtils;
import com.example.khanh.listenwritedemo.helper.SharePreferenceUtils;
import com.example.khanh.listenwritedemo.module.Practice;
import com.example.khanh.listenwritedemo.module.Section;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by khanh on 7/19/2017.
 */

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.MyViewHolder> {

    private List<Section> sectionList;
    CallBack callBack;
    private Context context;
    private SharedPreferences prefs;

    public SectionAdapter(List<Section> sectionList, CallBack callBack, Context context) {
        this.sectionList = sectionList;
        this.callBack = callBack;
        this.context = context;
        prefs = context.getSharedPreferences(FramentListenWrite.SCORE_PREFERENCE, MODE_PRIVATE);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleNetworkImageView imgSection;
        public TextView txtNameSection;
        public TextView txtTitle_Translate;
        public NumberProgressBar proressbarSection;
        public TextView txtNumphraseSection;

        public MyViewHolder(View view) {
            super(view);
            imgSection = (CircleNetworkImageView) view.findViewById(R.id.imgSection);
            txtNameSection = (TextView) view.findViewById(R.id.txtNameSection);
            txtTitle_Translate = (TextView) view.findViewById(R.id.txtTitle_Translate);
            txtNumphraseSection = (TextView) view.findViewById(R.id.txtNumphraseSection);
            proressbarSection = (NumberProgressBar) view.findViewById(R.id.proressbarSection);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Section section = sectionList.get(position);
        int numberCorrect = 0;
        for(Practice practice: section.getPhrases()){
            numberCorrect += prefs.getInt("score_" + section.getId() + "_" + practice.getId(), 0) > 0 ?  1: 0;
        }
        if(numberCorrect==0){
            holder.proressbarSection.setProgress(0);
            try {
                int correct = Integer.parseInt(SharePreferenceUtils.getString(context, "correct_"+section.getId()));
                if(correct!=0)
                holder.proressbarSection.setProgress(correct);
            } catch (Exception e) {
            }
        }
        else
            holder.proressbarSection.setProgress(numberCorrect);

        holder.imgSection.setImageUrl(section.getImage_url(), ImageLoaderUtils.getImageLoaderUtils(holder.imgSection.getContext()));
        holder.proressbarSection.setMax(section.getPhrases().size());
        holder.txtNameSection.setText(section.getTitle());
        holder.txtTitle_Translate.setText(section.getTitle_translate());
        holder.txtNumphraseSection.setText(section.getPhrases().size() + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
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
        void OnClick(int indexx);
    }

}
