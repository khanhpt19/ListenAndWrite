package com.example.khanh.listenwritedemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.khanh.listenwritedemo.MainActivity;

import butterknife.ButterKnife;


/**
 * Created by chungbn on 03/10/2015.
 */
public abstract class FragmentBase extends Fragment {

    private boolean savedWhenInStackFlag = false;
    private Bundle bundleSavedWhenInStack;

    private boolean restoreFromBackStackFlag = false;

    protected MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //settingPrefs = SettingPrefs.getInstance(getContext());
        if (savedInstanceState != null) {
            bundleSavedWhenInStack = savedInstanceState;
            savedWhenInStackFlag = true;
        }
    }

    @Override

    public void onActivityCreated(Bundle savedInstanceState) {
        // disable onBackStack
        savedWhenInStackFlag = false;
        super.onActivityCreated(savedInstanceState);
        if (restoreFromBackStackFlag) {
            initDataWhenRestoreFromStack();
        } else if (savedInstanceState == null) {
            initDataWhenCreateNew();
        } else {
            initDataWhenSavedState(savedInstanceState);
        }
        // fragment is shown -> mark back stack flag. When Fragment is obtained from the stack,
        // local variables of fragment is kept.
        restoreFromBackStackFlag = true;
        mainActivity = (MainActivity) getActivity();
        initDataDefault();
    }

    /**
     * Called after screen rotation. Should retrieve saved data<br>
     * Ex: mId = savedInstanceState.getInt("id");
     *
     * @param savedInstanceState
     */
    protected void initDataWhenSavedState(Bundle savedInstanceState) {
        // do not thing
    }

    /**
     * Called when create new instant. Data should be pass via get and set Argument<br>
     * Ex: mId = getArguments().getInt("id");
     */
    protected void initDataWhenCreateNew() {

    }

    /**
     * Called when fragment is bring to front from stack. Field variables still exist.<br>
     * Ex: mId keep value before
     */
    protected void initDataWhenRestoreFromStack() {

    }

    /**
     * Always call
     */
    protected void initDataDefault() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), null);
        ButterKnife.inject(this, view);
        initViews(view);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (savedWhenInStackFlag && bundleSavedWhenInStack != null) {
            outState.putAll(bundleSavedWhenInStack);
            return;
        }
        onSaveFragmentState(outState);
    }

    /**
     * Should save variables here:<br>
     * Ex: outState.putInt("id", mId);
     *
     * @param outState
     */
    public void onSaveFragmentState(Bundle outState) {
        // do nothing
    }

    protected abstract void initViews(View view);

    protected abstract int getLayout();

    protected String getTagName(){
        return this.getClass().getSimpleName();
    }

    public void toast(String text) {
        try {
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            //AnalyticsSender.onException(e);
        }
    }
}
