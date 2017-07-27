package com.example.khanh.listenwritedemo.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.adapter.AnimHelper;
import com.example.khanh.listenwritedemo.adapter.MyFragmentPagerAdapter;
import com.example.khanh.listenwritedemo.module.Section;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by khanh on 7/18/2017.
 */

public class FramentListenWrite extends FragmentBase {
    @InjectView(R.id.viewpager)
    NonSwipeableViewPager viewpager;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;
    @InjectView(R.id.btnHelp)
    Button btnHelp;
    @InjectView(R.id.txtNumCorrects)
    TextView txtNumCorrects;
    @InjectView(R.id.ripple)
    RippleBackground ripple;
    @InjectView(R.id.tv_hint)
    TextView tvhint;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private ArrayList<Fragment> listFragment = new ArrayList<>();

    SharedPreferences sharedpreferences;
    public static final int MyPREFERENCES = 0;
    public static final List<Integer> MyPREFERENCES1 = new ArrayList<>();

    int index;
    Section section;
    @InjectView(R.id.txtAnswer)
    EditText txtAnswer;
    Handler mHandler = new Handler();
    int ncorrects = 0, nmistakes = 0;

    List<String> acorrects= new ArrayList<>();
    List<String>amistakes= new ArrayList<>();
    @Override
    protected void initDataDefault() {
        super.initDataDefault();

        Bundle bundle = getArguments();
        section = (Section) bundle.getSerializable("SECTION");
        index = section.getId();
        toolbar.setTitle(section.getTitle());
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onBackPressed();
            }
        });
        loadList();

        progressbar.setMax(section.getPhrases().size());
        txtNumCorrects.setText("0/" + section.getPhrases().size());
    }


    private void loadList() {
        if (section == null) {
            return;
        }
        listFragment = new ArrayList<>();
        for (int i = 0; i < section.getPhrases().size(); i++) {
            listFragment.add(FramentListWrite.newInstance(section.getPhrases().get(i)));
        }
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), listFragment);
        viewpager.setAdapter(myFragmentPagerAdapter);
    }

    @Override
    protected void initViews(View view) {

    }

    public static FramentListenWrite newInstance(Section section) {
        FramentListenWrite fragmenT2 = new FramentListenWrite();
        Bundle bundle = new Bundle();

        bundle.putSerializable("SECTION", section);

        fragmenT2.setArguments(bundle);
        return fragmenT2;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_write_listen;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void showSoftKeyboard(Activity activity, EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    @OnClick({R.id.btnNext, R.id.btnHelp, R.id.imgHinh, R.id.txtAnswer})
    public void onClick(View v) {
        int k = viewpager.getCurrentItem();

        if (v.getId() == R.id.imgHinh) {
            try {
                Uri uri = Uri.parse(section.getPhrases().get(k).getAudio_url().toString());
                MediaPlayer player = new MediaPlayer();
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setDataSource(mainActivity, uri);
                player.prepare();
                player.start();
                ripple.startRippleAnimation();
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        ripple.stopRippleAnimation();
                    }
                });
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        if (v.getId() == R.id.txtAnswer) {
            showSoftKeyboard(mainActivity, txtAnswer);
        }

        if (v.getId() == R.id.btnHelp) {
            tvhint.setText(section.getPhrases().get(k).getText_translate().toString());
            AnimHelper.reveal(btnHelp, tvhint, tvhint.getWidth());
            btnHelp.setVisibility(View.GONE);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isAlive()) {
                    btnHelp.setVisibility(View.VISIBLE);
                    AnimHelper.hideReveal(btnHelp, tvhint, tvhint.getWidth());
                    }
                }
            }, 3000);
        }

        String a = txtAnswer.getText().toString();
        String b = section.getPhrases().get(k).getText().toString();
        if (v.getId() == R.id.btnNext) {
            if (b.contains(".") || b.contains(",") || b.contains("?") || b.contains(";")|| b.contains("!")) {
                if (a.toUpperCase().equalsIgnoreCase(b.substring(0, b.length() - 1).toUpperCase())) {
                    ncorrects++;
                    acorrects.add(b);
                } else {
                    nmistakes++;
                    amistakes.add(b);
                }
            } else {
                if (a.toUpperCase().equalsIgnoreCase(b.toUpperCase())) {
                    ncorrects++;
                    acorrects.add(b);
                } else {
                    nmistakes++;
                    amistakes.add(b);
                }
            }

            new Thread(new Runnable() {
                public void run() {
                    while (ncorrects < section.getPhrases().size()) {
                        mHandler.post(new Runnable() {
                            public void run() {
                                progressbar.setProgress(ncorrects + nmistakes);
                                txtNumCorrects.setText((ncorrects + nmistakes) + "/" + section.getPhrases().size());
                            }
                        });
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            if (k == section.getPhrases().size() - 1) {
                mainActivity.onOpenFragment(FragmentResult.newInstance(acorrects, amistakes), true);

                sharedpreferences = getContext().getSharedPreferences(String.valueOf(MyPREFERENCES), Context.MODE_APPEND);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("indexs", index);
                section.setCorrects(ncorrects);
                editor.putInt("corrects", section.getCorrects());
                editor.commit();
            }

            viewpager.setCurrentItem(k + 1);
            txtAnswer.setText("");
            try {
                Uri uri = Uri.parse(section.getPhrases().get(k + 1).getAudio_url().toString());
                MediaPlayer player = new MediaPlayer();
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setDataSource(mainActivity, uri);
                player.prepare();
                player.start();
                ripple.startRippleAnimation();
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        ripple.stopRippleAnimation();
                    }
                });
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    private boolean isAlive() {
        return getActivity() != null;
    }

}
