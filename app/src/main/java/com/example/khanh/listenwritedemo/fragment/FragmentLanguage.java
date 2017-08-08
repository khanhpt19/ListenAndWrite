package com.example.khanh.listenwritedemo.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.adapter.LanguageAdapter;
import com.example.khanh.listenwritedemo.adapter.SectionAdapter;
import com.example.khanh.listenwritedemo.module.Language;
import com.example.khanh.listenwritedemo.module.Section;
import com.example.khanh.listenwritedemo.request.TaskLanguage;
import com.example.khanh.listenwritedemo.request.TaskSection;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 7/27/2017.
 */

public class FragmentLanguage extends FragmentBase implements LanguageAdapter.CallBack {
    @InjectView(R.id.rv_language)
    RecyclerView rv_language;
    private LanguageAdapter mAdapter;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    List<Language> languagelist = new ArrayList();
    SharedPreferences spreferences;
    public static final int PREFERENCES = 0;

    @Override
    protected void initDataDefault() {
        super.initDataDefault();
        toolbar.setTitle("Choose language");
        toolbar.setNavigationIcon(R.drawable.ic_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onBackPressed();
            }
        });
        loadData();
    }

    private void loadList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_language.setLayoutManager(layoutManager);

        mAdapter = new LanguageAdapter(languagelist, this);

        rv_language.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void loadData() {
        TaskLanguage taskQuestion = new TaskLanguage(getContext());
        taskQuestion.request(new Response.Listener<ArrayList<Language>>() {
            @Override
            public void onResponse(ArrayList<Language> response) {
                languagelist = response;
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
        return R.layout.fragment_language;
    }

    public static FragmentLanguage newInstance(String name) {
        FragmentLanguage fragmenT2 = new FragmentLanguage();
        Bundle bundle = new Bundle();

        bundle.putString("INDEX", name);

        fragmenT2.setArguments(bundle);
        return fragmenT2;
    }

    @Override
    public void OnClick(int index) {
        toast("Language: " + languagelist.get(index).getName().toString() + "");

        spreferences = getContext().getSharedPreferences(String.valueOf(PREFERENCES), MODE_PRIVATE);
        SharedPreferences.Editor editor = spreferences.edit();
        editor.putString("code", languagelist.get(index).getCode().toString());
        editor.commit();

        mainActivity.onOpenFragment(FragmentSection.newInstance(languagelist.get(index).getCode().toString()), true);
    }
}
