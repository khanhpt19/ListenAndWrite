package com.example.khanh.listenwritedemo.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.adapter.AppUtils;
import com.example.khanh.listenwritedemo.adapter.ApplicationsAdapter;
import com.example.khanh.listenwritedemo.module.Admob;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 7/28/2017.
 */

public class FragmentFollow extends FragmentBase{
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void initDataDefault() {
        super.initDataDefault();
        toolbar.setTitle("Follow us");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onBackPressed();
            }
        });

    }
    @Override
    protected void initViews(View view) {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_follow;
    }
    public static FragmentFollow newInstance(int index) {

        FragmentFollow fragmentResult = new FragmentFollow();
        Bundle bundle = new Bundle();

        bundle.putInt("INDEX",index);

        fragmentResult.setArguments(bundle);
        return fragmentResult;
    }

    @OnClick({R.id.loYoutube,R.id.loTwitter,R.id.loFacebook})
    public void onClick(View v){
        if(v.getId()==R.id.loYoutube){
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC8G7tu6_GI2WvTl00gevxXg")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC8G7tu6_GI2WvTl00gevxXg")));
            }
        }else if(v.getId()==R.id.loTwitter){
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/yobimie")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/yobimie")));
            }
        }else if(v.getId()==R.id.loFacebook){
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/learningenglish.yobimi/")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/learningenglish.yobimi/")));
            }
        }
    }
}
