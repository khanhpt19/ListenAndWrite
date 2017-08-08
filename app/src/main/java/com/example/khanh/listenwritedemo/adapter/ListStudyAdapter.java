package com.example.khanh.listenwritedemo.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.khanh.listenwritedemo.MainActivity;
import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.helper.CircleNetworkImageView;
import com.example.khanh.listenwritedemo.helper.ImageLoaderUtils;
import com.example.khanh.listenwritedemo.helper.SharePreferenceUtils;
import com.example.khanh.listenwritedemo.module.Practice;
import com.example.khanh.listenwritedemo.module.Section;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static android.content.Context.MODE_PRIVATE;
import static com.example.khanh.listenwritedemo.fragment.FramentListenWrite.MyPREFERENCESSTUDY;


/**
 * Created by Administrator on 8/2/2017.
 */

public class ListStudyAdapter extends RecyclerView.Adapter<ListStudyAdapter.MyViewHolder> {
    private List<String> listStudy;
    private Context context;
    CallBack callBack;
    public ListStudyAdapter(List<String> listStudy, CallBack callBack) {
        this.listStudy = listStudy;
        this.callBack=callBack;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_list_text;
        public MyViewHolder(View view) {
            super(view);
            txt_list_text = (TextView) view.findViewById(R.id.txt_list_text);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_study, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.txt_list_text.setText(listStudy.get(position));

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