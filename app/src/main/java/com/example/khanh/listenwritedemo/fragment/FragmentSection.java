package com.example.khanh.listenwritedemo.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.adapter.SectionAdapter;
import com.example.khanh.listenwritedemo.module.Language;
import com.example.khanh.listenwritedemo.module.Section;
import com.example.khanh.listenwritedemo.request.TaskSection;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

import static com.example.khanh.listenwritedemo.fragment.FragmentLanguage.PREFERENCES;

/**
 * Created by khanh on 7/18/2017.
 */

public class FragmentSection extends FragmentBase implements SectionAdapter.CallBack {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recycler_view)
    RecyclerView recycler_view;
    private SectionAdapter mAdapter;
    List<Section> sectionList = new ArrayList();
    String name = "";
    String codenal;
    @InjectView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    int dem=0;

    @Override
    protected void initDataDefault() {
        super.initDataDefault();

        toolbar.setTitle("English Speaking");
        mainActivity.setUpToolbar(toolbar);

        toolbar.inflateMenu(R.menu.menu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.mnMore) {
                    mainActivity.onOpenFragment(FragmentLanguage.newInstance(name), true);
                    dem++;
                }
                return false;
            }
        });

            SharedPreferences prefs1 = getContext().getSharedPreferences(String.valueOf(PREFERENCES), Context.MODE_PRIVATE);
            codenal = prefs1.getString("code", "");

        loadData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
//        mainActivity.initBannerAds(adsContainer);
    }

    private void loadList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler_view.setLayoutManager(layoutManager);

        mAdapter = new SectionAdapter(sectionList, this, mainActivity);

        recycler_view.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void loadData() {
            TaskSection taskQuestion = new TaskSection(getContext(), codenal);
            taskQuestion.request(new Response.Listener<ArrayList<Section>>() {
                @Override
                public void onResponse(ArrayList<Section> response) {
                    sectionList = response;
                    loadList();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
        }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_section;
    }

    @Override
    public void OnClick(int index) {
        mainActivity.onOpenFragment(FramentListenWrite.newInstance(sectionList.get(index)), true);
        mainActivity.checkToShowInterstitialAds();
    }

    public static FragmentSection newInstance(String code) {
        FragmentSection fragmentSection = new FragmentSection();

        Bundle bundle = new Bundle();

        bundle.putString("CODE", code);

        fragmentSection.setArguments(bundle);
        return fragmentSection;
    }

    public static FragmentSection newInstance2() {
        FragmentSection fragmentSection = new FragmentSection();
        return fragmentSection;
    }

}
