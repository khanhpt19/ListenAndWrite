package com.example.khanh.listenwritedemo.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.module.Admob;
import com.example.khanh.listenwritedemo.module.Section;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.khanh.listenwritedemo.fragment.FramentListenWrite.MyPREFERENCES;

/**
 * Created by Administrator on 7/28/2017.
 */

public class ApplicationsAdapter extends RecyclerView.Adapter<ApplicationsAdapter.MyViewHolder> {

    private List<Admob> admobList;
    CallBack callBack;
    public ApplicationsAdapter(List<Admob> sectionList, CallBack callBack) {
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
        Admob admob = admobList.get(position);
        holder.imgApplications.setImageUrl(admob.getIcon_link(), ImageLoaderUtils.getImageLoaderUtils(holder.imgApplications.getContext()));
        holder.txtNameApplications.setText(admob.getName());
        holder.txtDesApplications.setText(admob.getDes());
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
        return admobList.size();
    }

    public interface CallBack {
        void OnClick(int indexx);

    }

}
