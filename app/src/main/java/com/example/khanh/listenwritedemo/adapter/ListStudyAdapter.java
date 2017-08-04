package com.example.khanh.listenwritedemo.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static android.content.Context.MODE_PRIVATE;
import static com.example.khanh.listenwritedemo.fragment.FramentListenWrite.MyPREFERENCESSTUDY;


/**
 * Created by Administrator on 8/2/2017.
 */

public class ListStudyAdapter extends RecyclerView.Adapter<ListStudyAdapter.MyViewHolder> {
    private List<String> sectionList;
    private Context context;
    public ListStudyAdapter(List<String> sectionList) {
        this.sectionList = sectionList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_list_text;
//        public TextView txt_list_text_translate;
//        public ImageView img_play_list_study;
        public MyViewHolder(View view) {
            super(view);
            txt_list_text = (TextView) view.findViewById(R.id.txt_list_text);
//            txt_list_text_translate= (TextView) view.findViewById(R.id.txt_list_text_translate);
//            img_play_list_study= (ImageView) view.findViewById(R.id.img_play_list_study);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_study, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        SharedPreferences prefs = context.getSharedPreferences(String.valueOf(MyPREFERENCESSTUDY), context.MODE_PRIVATE);
//        String text=prefs.getString("TEXT",null);
//        List<String> liststudy= new ArrayList<>();
//        liststudy.add(text);
//        holder.txt_list_text.setText((position+1)+": "+liststudy.get(position).toString());


        holder.txt_list_text.setText((position+1)+": "+sectionList.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }
}