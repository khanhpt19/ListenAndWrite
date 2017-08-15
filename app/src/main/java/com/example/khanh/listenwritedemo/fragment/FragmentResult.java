package com.example.khanh.listenwritedemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.adapter.ResultAdapter;
import com.example.khanh.listenwritedemo.adapter.ResultAdapter2;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by khanh on 7/25/2017.
 */

public class FragmentResult extends FragmentBase {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.rv1)
    RecyclerView rv1;
    @InjectView(R.id.rv2)
    RecyclerView rv2;
    private ResultAdapter mAdapter;
    private ResultAdapter2 mAdapter2;

    @Override
    protected void initDataDefault() {
        super.initDataDefault();
        Bundle bundle = getArguments();
        toolbar.setTitle("Result");
        toolbar.setNavigationIcon(R.drawable.ic_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onBackPressed();
                mainActivity.onOpenFragment(FragmentSection.newInstance2(),true);
            }
        });

        List<String> correct = bundle.getStringArrayList("correct");
        List<String> mistakes = bundle.getStringArrayList("mistakes");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv1.setLayoutManager(layoutManager);
        mAdapter = new ResultAdapter(correct);
        rv1.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        rv2.setLayoutManager(layoutManager2);
        mAdapter2 = new ResultAdapter2(mistakes);
        rv2.setAdapter(mAdapter2);
        mAdapter2.notifyDataSetChanged();
    }

    @Override
    protected void initViews(View view) {

    }

    public static FragmentResult newInstance(List<String> a, List<String> b) {
        FragmentResult fragmentResult = new FragmentResult();
        Bundle bundle = new Bundle();

        bundle.putStringArrayList("correct", (ArrayList<String>) a);
        bundle.putStringArrayList("mistakes", (ArrayList<String>) b);

        fragmentResult.setArguments(bundle);
        return fragmentResult;
    }

    @Override
    protected int getLayout() {
        return R.layout.result_layout;
    }
}
