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
import com.example.khanh.listenwritedemo.helper.CircleNetworkImageView;
import com.example.khanh.listenwritedemo.helper.ImageLoaderUtils;
import com.example.khanh.listenwritedemo.helper.SharePreferenceUtils;
import com.example.khanh.listenwritedemo.module.Section;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.khanh.listenwritedemo.fragment.FramentListenWrite.MyPREFERENCES;

/**
 * Created by khanh on 7/19/2017.
 */

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.MyViewHolder> {

    private List<Section> sectionList;
    CallBack callBack;
    private Context context;
    public SectionAdapter(List<Section> sectionList, CallBack callBack, Context context) {
        this.sectionList = sectionList;
        this.callBack = callBack;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleNetworkImageView imgSection;
        public TextView txtNameSection;
        public TextView txtTitle_Translate;
        public NumberProgressBar proressbarSection;

        public MyViewHolder(View view) {
            super(view);
            imgSection= (CircleNetworkImageView) view.findViewById(R.id.imgSection);
            txtNameSection = (TextView) view.findViewById(R.id.txtNameSection);
            txtTitle_Translate = (TextView) view.findViewById(R.id.txtTitle_Translate);
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

        SharedPreferences prefs = context.getSharedPreferences(String.valueOf(MyPREFERENCES), MODE_PRIVATE);
        int corrects = prefs.getInt("corrects_" + section.getId(), 0);

        holder.imgSection.setImageUrl(section.getImage_url(), ImageLoaderUtils.getImageLoaderUtils(holder.imgSection.getContext()));
        holder.proressbarSection.setMax(section.getPhrases().size());
        holder.txtNameSection.setText(section.getTitle()+" ("+section.getPhrases().size()+")");
        holder.txtTitle_Translate.setText(section.getTitle_translate());

        holder.proressbarSection.setProgress(corrects);

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
