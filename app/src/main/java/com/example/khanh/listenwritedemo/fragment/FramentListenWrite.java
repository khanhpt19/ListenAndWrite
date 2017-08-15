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
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.helper.AnimHelper;
import com.example.khanh.listenwritedemo.adapter.MyFragmentPagerAdapter;
import com.example.khanh.listenwritedemo.helper.NonSwipeableViewPager;
import com.example.khanh.listenwritedemo.helper.SharePreferenceUtils;
import com.example.khanh.listenwritedemo.module.Practice;
import com.example.khanh.listenwritedemo.module.Section;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by khanh on 7/18/2017.
 */

public class FramentListenWrite extends FragmentBase {
    @InjectView(R.id.viewpager)
    NonSwipeableViewPager viewpager;
    @InjectView(R.id.imgCheck)
    ImageView imgCheck;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;
    @InjectView(R.id.btnHelp)
    Button btnHelp;
    @InjectView(R.id.btnNext)
    Button btnNext;
    @InjectView(R.id.txtNumCorrects)
    TextView txtNumCorrects;
    @InjectView(R.id.ripple)
    RippleBackground ripple;
    @InjectView(R.id.tv_hint)
    TextView tvhint;
    @InjectView(R.id.txtShowResult)
    TextView txtShowResult;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private ArrayList<Fragment> listFragment = new ArrayList<>();

    SharedPreferences sharedpreferences;
    public static final String SCORE_PREFERENCE = "score";
    int dem = 0;
    Section section;
    @InjectView(R.id.txtAnswer)
    EditText txtAnswer;
    Handler mHandler = new Handler();

    @Override
    protected void initDataDefault() {
        super.initDataDefault();
        sharedpreferences = getContext().getSharedPreferences(SCORE_PREFERENCE, MODE_PRIVATE);
        Bundle bundle = getArguments();
        section = (Section) bundle.getSerializable("SECTION");
        toolbar.setTitle(section.getTitle());
        toolbar.setNavigationIcon(R.drawable.ic_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });
        loadList();
        toolbar.inflateMenu(R.menu.menu_list_add);
        updateScoreStatus();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                showDialogStatus();
                return false;
            }
        });
        progressbar.setMax(section.getPhrases().size());
        // check psda
        int position = 0;
        for(int i = 0; i< section.getPhrases().size();i++){
            if(getScore(section.getPhrases().get(i).getId()) == 0){
                position = i;
                break;
            }
        }
        if(position > 0){
            onSeekingToPractice(position);
        }
    }


    private void updateScoreStatus(){
        // for per pratice list
//        int numberCorrect = 0;
//        for(Practice practice: section.getPhrases()){
//            numberCorrect += getScore(practice.getId());
//        }
//        txtNumCorrects.setText(numberCorrect+ "/" + section.getPhrases().size());
    }

    private void onSeekingToPractice(int postion){
        viewpager.setCurrentItem(postion);
        txtAnswer.setText("");
        playmedia(postion);
        btnNext.setText("Done");
        btnNext.setBackgroundResource(R.drawable.round_rect);
        txtShowResult.setText("");
        imgCheck.setVisibility(View.INVISIBLE);
        progressbar.setProgress(viewpager.getCurrentItem());
    }


    private void showDialogStatus(){
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        builder.setTitle("Result");

        builder.show();
    }

    public void goToHome() {
//        FragmentManager fm = getSupportFragmentManager();
        FragmentManager fm = mainActivity.getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    private void loadList() {
        if (section == null) {
            mainActivity.onBackPressed();
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
        FramentListenWrite fragment = new FramentListenWrite();
        Bundle bundle = new Bundle();

        bundle.putSerializable("SECTION", section);

        fragment.setArguments(bundle);
        return fragment;
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

    @OnClick({R.id.btnNext, R.id.btnHelp, R.id.imgHinh, R.id.txtAnswer, R.id.btnMean})
    public void onClick(View v) {
        if (v.getId() == R.id.imgHinh) {
            playmedia(viewpager.getCurrentItem());
        }
        int k = viewpager.getCurrentItem();
        if (v.getId() == R.id.txtAnswer) {
            showSoftKeyboard(mainActivity, txtAnswer);
        }

        if (v.getId() == R.id.btnHelp) {
            tvhint.setText(section.getPhrases().get(k).getText().toString());
            AnimHelper.reveal(btnHelp, tvhint, tvhint.getWidth());
            btnHelp.setVisibility(View.GONE);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isAlive) {
                        btnHelp.setVisibility(View.VISIBLE);
                        AnimHelper.hideReveal(btnHelp, tvhint, tvhint.getWidth());
                    }
                }
            }, 3000);
        }

        String a = txtAnswer.getText().toString();
        String b = section.getPhrases().get(k).getText().toString();

        if (v.getId() == R.id.btnMean) {
            if (section.getPhrases().get(k).getText_translate() != null)
                txtShowResult.setText(section.getPhrases().get(k).getText_translate());
            else
                txtShowResult.setText(section.getPhrases().get(k).getText());
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imgCheck.setVisibility(View.INVISIBLE);
                    txtShowResult.setText("");
                }
            }, 5000);
        }

        if (v.getId() == R.id.btnNext) {
            dem++;
            if (dem % 2 == 1) {
                btnNext.setText("Next");
                btnNext.setBackgroundResource(R.drawable.round_rect2);

                if ((b.contains(".") || b.contains(",") || b.contains("?") || b.contains(";") || b.contains("!"))) {
                    if (a.length() == 0 || a == "") {
                        mistake(b);
                    } else if (a.substring((a.length() - 1), a.length()).contains(" ")) {
                        if ((a.substring(0, a.length() - 1).toUpperCase()).equalsIgnoreCase(b.substring(0, b.length() - 1).toUpperCase())) {
                            correct(b);
                        } else {
                            mistake(b);
                        }
                    } else {
                        if ((a.toUpperCase()).equalsIgnoreCase(b.substring(0, b.length() - 1).toUpperCase())) {
                            correct(b);
                        } else {
                            mistake(b);
                        }
                    }
                } else {
                    if (a.length() == 0 || a == "") {
                        mistake(b);
                    } else if (a.substring((a.length() - 1), a.length()).contains(" ")) {
                        if ((a.substring(0, a.length() - 1).toUpperCase()).equalsIgnoreCase(b.toUpperCase())) {
                            correct(b);
                        } else {
                            mistake(b);
                        }
                    } else {
                        if ((a.toUpperCase()).equalsIgnoreCase(b.toUpperCase())) {
                            correct(b);
                        } else {
                            mistake(b);
                        }
                    }
                }
                updateScoreStatus();
            } else {
                onSeekingToPractice(k+1);
            }

            if (k == section.getPhrases().size() - 1) {
                showDialogStatus();
            }
        }
    }

    public void correct(String b1) {
        MediaPlayer mp = MediaPlayer.create(mainActivity, R.raw.right);
        mp.start();
        imgCheck.setVisibility(View.VISIBLE);
        imgCheck.setImageResource(R.drawable.ic_done);
        txtShowResult.setText(section.getPhrases().get(viewpager.getCurrentItem()).getText().toString());
        txtShowResult.setTextColor(getResources().getColor(R.color.colorGreen));
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgCheck.setVisibility(View.INVISIBLE);
                txtShowResult.setText("");
            }
        }, 10000);
        setScore(section.getPhrases().get(viewpager.getCurrentItem()).getId(), 1);
    }

    public void mistake(String b1) {
        MediaPlayer mp = MediaPlayer.create(mainActivity, R.raw.wrong);
        mp.start();

        imgCheck.setVisibility(View.VISIBLE);
        imgCheck.setImageResource(R.drawable.ic_clear);
        txtShowResult.setText(section.getPhrases().get(viewpager.getCurrentItem()).getText().toString());
        txtShowResult.setTextColor(getResources().getColor(R.color.colorRed));
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgCheck.setVisibility(View.INVISIBLE);
                txtShowResult.setText("");
            }
        }, 10000);
        setScore(section.getPhrases().get(viewpager.getCurrentItem()).getId(), -1);
    }

    public void playmedia(int kb) {
        try {
            Uri uri = Uri.parse(section.getPhrases().get(kb).getAudio_url().toString());
            final MediaPlayer player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(mainActivity, uri);
            player.prepare();
            player.start();
            ripple.startRippleAnimation();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    ripple.stopRippleAnimation();
                    player.release();
                }
            });
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void setScore(int practiceId, int score){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("score_" + section.getId() + "_" + practiceId, score);
        editor.apply();
    }

    private int getScore(int practiceId){
        return sharedpreferences.getInt("score_" + section.getId() + "_" + practiceId, 0);
    }

}
