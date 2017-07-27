package com.example.khanh.listenwritedemo.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.example.khanh.listenwritedemo.MainActivity;
import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.module.Section;

import java.util.List;

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
        public ProgressBar proressbarSection;
        public TextView txtPerCentSection;

        public MyViewHolder(View view) {
            super(view);
            imgSection= (CircleNetworkImageView) view.findViewById(R.id.imgSection);
            txtNameSection = (TextView) view.findViewById(R.id.txtNameSection);
            txtPerCentSection = (TextView) view.findViewById(R.id.txtPerCentSection);
            proressbarSection = (ProgressBar) view.findViewById(R.id.proressbarSection);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        SharedPreferences prefs = context.getSharedPreferences(String.valueOf(MyPREFERENCES), Context.MODE_APPEND);
        int index = prefs.getInt("indexs", 0);
        int corrects = prefs.getInt("corrects", 0);

        Section section = sectionList.get(position);
        section.setCorrects(corrects);
//        Toast.makeText(context,sectionList.get(position).getPhrases().get(index).getId(),Toast.LENGTH_SHORT).show();
//        Section msection= sectionList.get();
//        msection.setCorrects(corrects);
    
        holder.txtNameSection.setText(section.getTitle());
        holder.imgSection.setImageUrl(section.getImage_url(), ImageLoaderUtils.getImageLoaderUtils(holder.imgSection.getContext()));
        holder.proressbarSection.setMax(section.getPhrases().size());

        holder.proressbarSection.setProgress(section.getCorrects());
        holder.txtPerCentSection.setText(section.getCorrects() + "/" + section.getPhrases().size());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.OnClick(position);
            }
        });
    }


//    public SharedPreferences getSharedPreferences(String name, int mode) {
//        return mBase.getSharedPreferences(name, mode);
//    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }


    public interface CallBack {
        void OnClick(int index);
    }

}
