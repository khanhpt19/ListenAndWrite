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
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khanh.listenwritedemo.R;
import com.example.khanh.listenwritedemo.helper.AnimHelper;
import com.example.khanh.listenwritedemo.adapter.MyFragmentPagerAdapter;
import com.example.khanh.listenwritedemo.helper.NonSwipeableViewPager;
import com.example.khanh.listenwritedemo.helper.SharePreferenceUtils;
import com.example.khanh.listenwritedemo.module.Section;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;
import static com.example.khanh.listenwritedemo.R.color.colorOrane;

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
    public static final int MyPREFERENCES = 0;
    public static final int MyPREFERENCESSTUDY = 0;
    int index, dem = 0, dem1 = 0;
    Section section;
    @InjectView(R.id.txtAnswer)
    EditText txtAnswer;
    Handler mHandler = new Handler();
    int ncorrects = 0, nmistakes = 0, k,h=0;

    List<String> listcorrect = new ArrayList<>();
    List<String> listmistake = new ArrayList<>();

    List<String> acorrects = new ArrayList<>();
    List<String> amistakes = new ArrayList<>();

    @Override
    protected void initDataDefault() {
        super.initDataDefault();

        Bundle bundle = getArguments();
        section = (Section) bundle.getSerializable("SECTION");

        h=bundle.getInt("current");

        index = section.getId();
        toolbar.setTitle(section.getTitle());
        toolbar.setNavigationIcon(R.drawable.ic_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
//                mainActivity.onBackPressed();
            }
        });
        loadList();
        toolbar.inflateMenu(R.menu.menu_list_add);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (SharePreferenceUtils.getString(mainActivity, "sizecorrect_" + index) != null
                        || SharePreferenceUtils.getString(mainActivity, "sizemistake_" + index) != null)
                    mainActivity.onOpenFragment(FragmentListStudy.newInstance(section, listcorrect, listmistake, k), true);
                return false;
            }
        });
        progressbar.setMax(section.getPhrases().size());
        txtNumCorrects.setText("0/" + section.getPhrases().size());
    }


    public void goToHome() {
//        FragmentManager fm = getSupportFragmentManager();
        FragmentManager fm = mainActivity.getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
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

    public static FramentListenWrite newInstance2(Section section,int k) {
        FramentListenWrite fragmenT2 = new FramentListenWrite();
        Bundle bundle = new Bundle();

        bundle.putSerializable("SECTION", section);
        bundle.putInt("current",k);

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

    @OnClick({R.id.btnNext, R.id.btnHelp, R.id.imgHinh, R.id.txtAnswer, R.id.btnMean})
    public void onClick(View v) {
//        if(h==0)
            k = viewpager.getCurrentItem();
//        else
//            k=h;

        if (v.getId() == R.id.imgHinh) {
            playmedia(k);
        }

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
                    if (isAlive()) {
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
            }, 9000);
        }

        if (v.getId() == R.id.btnNext) {
            dem++;
            if (dem % 2 == 1) {
                btnNext.setText("Next");
                btnNext.setBackgroundResource(R.color.colorGreen);
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
                runthread();
            } else {
                viewpager.setCurrentItem(k + 1);
                txtAnswer.setText("");
                playmedia(k + 1);
                btnNext.setText("Done");
                btnNext.setBackgroundResource(colorOrane);
                txtShowResult.setText("");
                imgCheck.setVisibility(View.INVISIBLE);
                dem1++;
            }

            if (k == section.getPhrases().size() - 1) {
                mainActivity.onOpenFragment(FragmentResult.newInstance(acorrects, amistakes), true);
                sharepref();
            }
            SharePreferenceUtils.setString(mainActivity, "correct_" + section.getId(), String.valueOf(ncorrects));
        }
    }

    public void correct(String b1) {
        MediaPlayer mp = MediaPlayer.create(mainActivity, R.raw.right);
        mp.start();
        ncorrects++;
        acorrects.add(b1);

        listmistake = new Gson().fromJson(SharePreferenceUtils.getString(getContext(), "listmistake_" + section.getId()), new TypeToken<ArrayList<String>>() {
        }.getType());
        listcorrect = new Gson().fromJson(SharePreferenceUtils.getString(getContext(), "listcorrect_" + section.getId()), new TypeToken<ArrayList<String>>() {
        }.getType());
        List<String> correct2 = new ArrayList<>();
        List<String> mistake2 = new ArrayList<>();
        try {
            for (int i = 0; i < listcorrect.size(); i++) {
                if (!listcorrect.get(i).equals(b1)) {
                    correct2.add(listcorrect.get(i));
                }
            }
            for (int i = 0; i < listmistake.size(); i++) {
                if (!listmistake.get(i).equals(b1)) {
                    mistake2.add(listmistake.get(i));
                }
            }
        } catch (Exception e) {
        }
        correct2.add(b1);
        SharePreferenceUtils.setString(mainActivity,"kk_"+section.getId(),String.valueOf(k));
        SharePreferenceUtils.setString(mainActivity, "sizecorrect_" + section.getId(), correct2.size() + "");
        SharePreferenceUtils.setString(mainActivity, "listmistake_" + section.getId(), new Gson().toJson(mistake2));
        SharePreferenceUtils.setString(mainActivity, "listcorrect_" + section.getId(), new Gson().toJson(correct2));

        imgCheck.setVisibility(View.VISIBLE);
        imgCheck.setImageResource(R.drawable.ic_done);
        txtShowResult.setText(section.getPhrases().get(k).getText().toString());
        txtShowResult.setTextColor(getResources().getColor(R.color.colorGreen));
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgCheck.setVisibility(View.INVISIBLE);
                txtShowResult.setText("");
            }
        }, 20000);

    }

    public void mistake(String b1) {
        MediaPlayer mp = MediaPlayer.create(mainActivity, R.raw.wrong);
        mp.start();
        nmistakes++;
        amistakes.add(b1);

        listmistake = new Gson().fromJson(SharePreferenceUtils.getString(getContext(), "listmistake_" + section.getId()), new TypeToken<ArrayList<String>>() {
        }.getType());
        listcorrect = new Gson().fromJson(SharePreferenceUtils.getString(getContext(), "listcorrect_" + section.getId()), new TypeToken<ArrayList<String>>() {
        }.getType());

        List<String> correct2 = new ArrayList<>();
        List<String> mistake2 = new ArrayList<>();
        try {
            for (int i = 0; i < listcorrect.size(); i++) {
                if (!listcorrect.get(i).equals(b1)) {
                    correct2.add(listcorrect.get(i));
                }
            }
            for (int i = 0; i < listmistake.size(); i++) {
                if (!listmistake.get(i).equals(b1)) {
                    mistake2.add(listmistake.get(i));
                }
            }
        } catch (Exception e) {
        }
        mistake2.add(b1);
        SharePreferenceUtils.setString(mainActivity,"kk_"+section.getId(),String.valueOf(k));
        SharePreferenceUtils.setString(mainActivity, "sizemistake_" + section.getId(), mistake2.size() + "");
        SharePreferenceUtils.setString(mainActivity, "listmistake_" + section.getId(), new Gson().toJson(mistake2));
        SharePreferenceUtils.setString(mainActivity, "listcorrect_" + section.getId(), new Gson().toJson(correct2));

        imgCheck.setVisibility(View.VISIBLE);
        imgCheck.setImageResource(R.drawable.ic_clear);
        txtShowResult.setText(section.getPhrases().get(k).getText().toString());
        txtShowResult.setTextColor(getResources().getColor(R.color.colorRed));
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgCheck.setVisibility(View.INVISIBLE);
                txtShowResult.setText("");
            }
        }, 20000);
    }

    public void runthread() {
        new Thread(new Runnable() {
            public void run() {
                while (ncorrects < section.getPhrases().size()) {
                    mHandler.post(new Runnable() {
                        public void run() {
                            progressbar.setProgress(ncorrects + nmistakes);
                            txtNumCorrects.setText((ncorrects) + "/" + section.getPhrases().size());
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

    public void sharepref() {
        sharedpreferences = getContext().getSharedPreferences(String.valueOf(MyPREFERENCES), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("index", index);
        editor.putInt("corrects_" + index, ncorrects);
        editor.commit();
    }

    private boolean isAlive() {
        return getActivity() != null;
    }
}
