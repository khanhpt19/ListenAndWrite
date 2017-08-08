package com.example.khanh.listenwritedemo.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.adapter.ListAdapterWrong;
import com.example.khanh.listenwritedemo.adapter.ListStudyAdapter;
import com.example.khanh.listenwritedemo.adapter.SectionAdapter;
import com.example.khanh.listenwritedemo.helper.SharePreferenceUtils;
import com.example.khanh.listenwritedemo.helper.ViewDialog;
import com.example.khanh.listenwritedemo.module.Practice;
import com.example.khanh.listenwritedemo.module.Section;
import com.example.khanh.listenwritedemo.request.TaskSection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static com.example.khanh.listenwritedemo.fragment.FramentListenWrite.MyPREFERENCESSTUDY;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.InjectView;

/**
 * Created by Administrator on 8/2/2017.
 */

public class FragmentListStudy extends FragmentBase implements ListStudyAdapter.CallBack, ListAdapterWrong.CallBack {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.rv_list_study)
    RecyclerView rv_list_study;
    @InjectView(R.id.rv_list_study_wrong)
    RecyclerView rv_list_study_wrong;
    private ListStudyAdapter mAdapter;
    private ListAdapterWrong nAdapter;
    Section section;
    int k;

    @Override
    protected void initDataDefault() {
        super.initDataDefault();

        toolbar.setTitle("List study");
        toolbar.setNavigationIcon(R.drawable.ic_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onBackPressed();
            }
        });

        Bundle bundle = getArguments();
        section = (Section) bundle.getSerializable("section");
        k = bundle.getInt("k");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_list_study.setLayoutManager(layoutManager);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        rv_list_study_wrong.setLayoutManager(layoutManager2);

        List<String> corrects = new Gson().fromJson(SharePreferenceUtils.getString(getContext(), "listcorrect_" + section.getId()), new TypeToken<ArrayList<String>>() {}.getType());
        List<String> mistakes = new Gson().fromJson(SharePreferenceUtils.getString(getContext(), "listmistake_" + section.getId()), new TypeToken<ArrayList<String>>() {}.getType());
        mAdapter = new ListStudyAdapter(corrects, this);
        nAdapter = new ListAdapterWrong(mistakes, this);
        if (SharePreferenceUtils.getString(mainActivity, "sizecorrect_" + section.getId()) != null)
            rv_list_study.setAdapter(mAdapter);
        if (SharePreferenceUtils.getString(mainActivity, "sizemistake_" + section.getId()) != null)
            rv_list_study_wrong.setAdapter(nAdapter);
        mAdapter.notifyDataSetChanged();
        nAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_list_study;
    }

    public static FragmentListStudy newInstance(Section section, List<String> a, List<String> b, int k) {

        FragmentListStudy fragmentResult = new FragmentListStudy();
        Bundle bundle = new Bundle();

        bundle.putSerializable("section", section);
        bundle.putStringArrayList("correct", (ArrayList<String>) a);
        bundle.putStringArrayList("mistake", (ArrayList<String>) b);
        bundle.putInt("k", k);

        fragmentResult.setArguments(bundle);
        return fragmentResult;
    }

    @Override
    public void OnClick(int indexx) {
//        mainActivity.onOpenFragment(FramentListenWrite.newInstance2(k,section.getId()), true);
        toast("Clicked");
    }
}
