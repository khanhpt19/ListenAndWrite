package com.example.khanh.listenwritedemo.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.module.Practice;

/**
 * Created by khanh on 7/18/2017.
 */

public class FramentListWrite extends FragmentBase {
    String  text;

    @Override
    protected void initDataDefault() {
        super.initDataDefault();

        Bundle bundle = getArguments();
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_list_write;
    }

    public static FramentListWrite newInstance(Practice practice) {
        FramentListWrite fragmenT2 = new FramentListWrite();
        Bundle bundle = new Bundle();

        bundle.putString("TEXT",practice.getText());

        fragmenT2.setArguments(bundle);
        return fragmenT2;
    }
}
