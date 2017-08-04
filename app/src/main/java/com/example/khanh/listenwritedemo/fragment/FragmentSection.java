package com.example.khanh.listenwritedemo.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
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
import com.example.khanh.listenwritedemo.helper.SharePreferenceUtils;
import com.example.khanh.listenwritedemo.helper.ViewDialog;
import com.example.khanh.listenwritedemo.module.Config;
import com.example.khanh.listenwritedemo.module.Language;
import com.example.khanh.listenwritedemo.module.ListLang;
import com.example.khanh.listenwritedemo.module.Section;
import com.example.khanh.listenwritedemo.request.TaskSection;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.khanh.listenwritedemo.fragment.FragmentLanguage.PREFERENCES;

/**
 * Created by khanh on 7/18/2017.
 */

public class FragmentSection extends FragmentBase implements SectionAdapter.CallBack {
    @InjectView(R.id.adsContainer)
    FrameLayout adsContainer;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recycler_view)
    RecyclerView recycler_view;
    private SectionAdapter mAdapter;
    List<Section> sectionList = new ArrayList();
    String name ;
    String code;
    @InjectView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    SharedPreferences spreferences;
    SharedPreferences prefs;
    int MYPRE=0;
    int dem = 0;
    Config config;
    @Override
    protected void initDataDefault() {
        super.initDataDefault();

        toolbar.setTitle("English Speaking");
        mainActivity.setUpToolbar(toolbar);

        toolbar.inflateMenu(R.menu.menu);
        config = new Gson().fromJson(SharePreferenceUtils.getString(getContext(), "SPLASH"), Config.class);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.mnMore) {
                    if (checkConnect() == true) {

                        final ArrayList<ListLang> listLang = config.getListLang();
                        String[] items = new String[listLang.size()];
                        for (int i = 0; i < listLang.size(); i++) {
                            items[i] = listLang.get(i).getName();
                        }

                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Choose your native language");
                        builder.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                code=listLang.get(which).getCode();
                                loadData(code);

                                spreferences = getContext().getSharedPreferences(String.valueOf(MYPRE), MODE_PRIVATE);
                                SharedPreferences.Editor editor = spreferences.edit();
                                editor.putString("code", listLang.get(which).getCode());
                                editor.commit();
//                                SharePreferenceUtils.setString(mainActivity,"CODE",code);
                            }
                        });
                        builder.show();

//                        mainActivity.onOpenFragment(FragmentLanguage.newInstance(name), true);
//                        dem++;
                    } else {
                        ViewDialog viewDialog = new ViewDialog();
                        viewDialog.showDialog(mainActivity, "Your device is not connected Internet. Please connect Internet and restart app", 2);
                    }
                }
                return false;

            }
        });

//        SharedPreferences prefs1 = getContext().getSharedPreferences(String.valueOf(PREFERENCES), Context.MODE_PRIVATE);
//        codenal = prefs1.getString("code", "");
//        SharePreferenceUtils.getString(mainActivity,"code");
//        toast(SharePreferenceUtils.getString(mainActivity,"code"));
        final SharedPreferences prefs = mainActivity.getSharedPreferences(String.valueOf(MYPRE), MODE_PRIVATE);

        name= prefs.getString("code",null);
        loadData(name);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (checkConnect() == true) {
                    name= prefs.getString("code",null);
                    loadData(name);
                    loadData(name);
                } else {
                    ViewDialog viewDialog = new ViewDialog();
                    viewDialog.showDialog(mainActivity, "Your device is not connected Internet. Please connect Internet and restart app", 2);
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        mainActivity.initBannerAds(adsContainer);
    }

    private void loadList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler_view.setLayoutManager(layoutManager);

        mAdapter = new SectionAdapter(sectionList, this, mainActivity);

        recycler_view.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void loadData(String codenal) {
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
                ViewDialog viewDialog = new ViewDialog();
                viewDialog.showDialog(mainActivity, "Your device is not connected Internet. Please connect Internet and restart app", 2);
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
        if (checkConnect() == true) {
            mainActivity.onOpenFragment(FramentListenWrite.newInstance(sectionList.get(index)), true);
//            mainActivity.checkToShowInterstitialAds();
        } else {
            ViewDialog viewDialog = new ViewDialog();
            viewDialog.showDialog(mainActivity, "Your device is not connected Internet. Please connect Internet and restart app", 2);
        }

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

    public boolean checkConnect() {
        ConnectivityManager cm = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
