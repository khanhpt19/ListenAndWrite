package com.example.khanh.listenwritedemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.helper.ImageLoaderUtils;
import com.example.khanh.listenwritedemo.module.Config;

import java.util.List;

/**
 * Created by Administrator on 7/28/2017.
 */

public class ApplicationsAdapter extends RecyclerView.Adapter<ApplicationsAdapter.MyViewHolder> {

    private Config admobList;
    CallBack callBack;
    public ApplicationsAdapter(Config sectionList, CallBack callBack) {
        this.admobList = sectionList;
        this.callBack = callBack;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public NetworkImageView imgApplications;
        public TextView txtNameApplications;
        public TextView txtDesApplications;

        public MyViewHolder(View view) {
            super(view);
            imgApplications= (NetworkImageView) view.findViewById(R.id.imgApplications);
            txtNameApplications= (TextView) view.findViewById(R.id.txtNameApplications);
            txtDesApplications= (TextView) view.findViewById(R.id.txtDesApplications);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_applications, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.imgApplications.setImageUrl(admobList.getRcmApp().get(position).getIconLink(), ImageLoaderUtils.getImageLoaderUtils(holder.imgApplications.getContext()));
        holder.txtNameApplications.setText(admobList.getRcmApp().get(position).getName());
        holder.txtDesApplications.setText(admobList.getRcmApp().get(position).getDes());
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
        return admobList.getRcmApp().size();
    }

    public interface CallBack {
        void OnClick(int indexx);

    }

}
