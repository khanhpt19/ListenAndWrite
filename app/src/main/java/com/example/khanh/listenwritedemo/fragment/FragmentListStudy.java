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
import com.example.khanh.listenwritedemo.adapter.ListStudyAdapter;
import com.example.khanh.listenwritedemo.adapter.SectionAdapter;
import com.example.khanh.listenwritedemo.helper.SharePreferenceUtils;
import com.example.khanh.listenwritedemo.helper.ViewDialog;
import com.example.khanh.listenwritedemo.module.Practice;
import com.example.khanh.listenwritedemo.module.Section;
import com.example.khanh.listenwritedemo.request.TaskSection;
import static com.example.khanh.listenwritedemo.fragment.FramentListenWrite.MyPREFERENCESSTUDY;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.InjectView;

/**
 * Created by Administrator on 8/2/2017.
 */

public class FragmentListStudy extends FragmentBase implements SectionAdapter.CallBack{
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.rv_list_study)
    RecyclerView rv_list_study;
    private ListStudyAdapter mAdapter;
    List<String> liststudy= new ArrayList<>();

    @Override
    protected void initDataDefault() {
        super.initDataDefault();

        toolbar.setTitle("List study");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onBackPressed();
            }
        });
        Bundle bundle=getArguments();
        Section msection= (Section) bundle.getSerializable("list");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        rv_list_study.setLayoutManager(layoutManager);
        List<String> correct = bundle.getStringArrayList("correct");
        SharePreferenceUtils.setString(mainActivity,"list",correct.toString());
//        SharedPreferences prefs = mainActivity.getSharedPreferences(String.valueOf(MyPREFERENCESSTUDY), mainActivity.MODE_PRIVATE);
//        String text=prefs.getString("TEXT",null);
        liststudy.add(SharePreferenceUtils.getString(mainActivity,"list"));
        mAdapter = new ListStudyAdapter( correct);
//        mAdapter = new ListStudyAdapter(liststudy);
        rv_list_study.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_list_study;
    }

    public static FragmentListStudy newInstance(List<String> a) {

        FragmentListStudy fragmentResult = new FragmentListStudy();
        Bundle bundle = new Bundle();

        bundle.putStringArrayList("correct", (ArrayList<String>) a);

        fragmentResult.setArguments(bundle);
        return fragmentResult;
    }

    @Override
    public void OnClick(int indexx) {

    }
}
