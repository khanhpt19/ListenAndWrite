package com.example.khanh.listenwritedemo.helper;

import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.khanh.listenwritedemo.module.Config;
import com.example.khanh.listenwritedemo.R;
import com.facebook.ads.AbstractAdListener;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;


/**
 * Created by chungbn on 03/12/2015.
 */
public class ActivityBase extends AppCompatActivity {

    private Config config;

    private AdView adView;
    private com.facebook.ads.InterstitialAd interstitialAdFB;
    private com.facebook.ads.AdView adViewFB;
    private static final String FACEBOOK_TAG = "fb";
    private static final String ADMOB_TAG = "admob";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        config = new Gson().fromJson(SharePreferenceUtils.getString(getBaseContext(), "SPLASH"), Config.class);
    }

    public void initBannerAds(FrameLayout containerLayout) {
        if (config.getAdsSetting().getAdsCat().equalsIgnoreCase(FACEBOOK_TAG)) {
            initAdsBannerFB(containerLayout);
        } else {
            initAdsBannerAdmob(containerLayout);
        }
    }

    public void checkToShowInterstitialAds() {
        long timeViewBefore = 0;
        try {
            timeViewBefore = Long.parseLong(SharePreferenceUtils.getString(getBaseContext(), "TIMEVIEW"));
        } catch (Exception e) {
            timeViewBefore = 0;
        }
        // check time beetwen 2 show ads
        log("timetoload",System.currentTimeMillis() - timeViewBefore+"");
        if (System.currentTimeMillis() - timeViewBefore < config.getAdsSetting().getDsecond() * 1000) {
            return;
        }
        if (config.getAdsSetting().getAdsCat().equalsIgnoreCase(FACEBOOK_TAG)) {
            loadInterstitialAdsFB();
        } else {
            loadInterstitialAdmobAds();
        }
    }

    private void initAdsBannerAdmob(final FrameLayout containerLayout) {
        AdRequest.Builder builder = new AdRequest.Builder();

        for (String device : getResources().getStringArray(R.array.device_admob)) {
            builder.addTestDevice(device);
        }
        AdRequest adRequest = builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        if (adView == null) {
            // create and add AdsView
            adView = new AdView(this);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            containerLayout.addView(adView, params);
        }
        String adsId = config.getAdsSetting().getAdmobBanner();
        if (StringUtils.isEmpty(adsId)) {
            adsId = getString(R.string.admob_banner_def);
        }
        Log.d("SetAdsId", "SetAdsIdBanner:" + adsId);
        try {
            if (StringUtils.isEmpty(adView.getAdUnitId())) {
                adView.setAdUnitId(adsId);
            }
            adView.setAdSize(AdSize.SMART_BANNER);
            adView.setAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(int errorCode) {
                    Log.i("My Ads", "Ads Failed to Load");
                    containerLayout.setVisibility(View.GONE);
                }

                @Override
                public void onAdLoaded() {
                    Log.i("My Ads", "Ads loaded");
                    containerLayout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAdOpened() {
                    super.onAdOpened();
                    toast("Thanks for your support");
                }
            });
            adView.loadAd(adRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initAdsBannerFB(final FrameLayout containerLayout) {
        String adsId = config.getAdsSetting().getFbBanner();
        if (StringUtils.isEmpty(adsId)) {
            adsId = getString(R.string.fbads_banner_def);
        }
        for (String device : getResources().getStringArray(R.array.device_fb)) {
            com.facebook.ads.AdSettings.addTestDevice(device);
        }
        // Instantiate an AdView view
        adViewFB = new com.facebook.ads.AdView(this, adsId, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        // Add the ad view to your activity layout
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        containerLayout.addView(adViewFB, params);

        adViewFB.setAdListener(new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                Log.i("My Facebook Ads", "Ads Failed to Load, error Code=" + adError.getErrorCode());
                containerLayout.setVisibility(View.GONE);
                if (adError.getErrorCode() != AdError.NETWORK_ERROR_CODE) {
                    initAdsBannerAdmob(containerLayout);
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.i("My Facebook Ads", "Ads loaded");
                containerLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdClicked(Ad ad) {
                toast("Thanks for your support");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        });
        // Request to load an ad
        adViewFB.loadAd();
    }

    private void loadInterstitialAdmobAds() {
        String adsId = config.getAdsSetting().getAdmobInter();
        if (StringUtils.isEmpty(adsId)) {
            adsId = getString(R.string.admob_interstitial_def);
        }
        Log.i("InterstitialAds", "Loading with id= " + adsId);
        try {
            final InterstitialAd interstitialAd = new InterstitialAd(this);
            interstitialAd.setAdUnitId(adsId);
            AdRequest.Builder builder = new AdRequest.Builder();

            for (String device : getResources().getStringArray(R.array.device_admob)) {
                builder.addTestDevice(device);
            }
            AdRequest adRequest = builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
            Log.i("InterstitialAds", "Starting load ads...");
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(int errorCode) {
                    super.onAdFailedToLoad(errorCode);
                    Log.i("InterstitialAds", "Ad failed to load");
                }

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    Log.i("InterstitialAds", "Ad loaded");
                    SharePreferenceUtils.setString(getBaseContext(), "TIMEVIEW", String.valueOf(System.currentTimeMillis()));
                    interstitialAd.show();
                }
            });
            interstitialAd.loadAd(adRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadInterstitialAdsFB() {
        String adsId = config.getAdsSetting().getFbInter();
        if (StringUtils.isEmpty(adsId)) {
            adsId = getString(R.string.fbads_interestitial_def);
        }
        Log.i("InterstitialAds Facebok", "Loading with id= " + adsId);
        interstitialAdFB = new com.facebook.ads.InterstitialAd(this, adsId);
        interstitialAdFB.setAdListener(new AbstractAdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                super.onError(ad, adError);
                Log.i("InterstitialAds Facebok", "Ad failed to load, error code=" + adError.getErrorCode());
                if (adError.getErrorCode() != AdError.NETWORK_ERROR_CODE) {
                    loadInterstitialAdmobAds();
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                super.onAdLoaded(ad);
                Log.i("InterstitialAds Facebok", "Ad loaded");
                SharePreferenceUtils.setString(getBaseContext(), "TIMEVIEW", String.valueOf(System.currentTimeMillis()));
                interstitialAdFB.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                super.onAdClicked(ad);
                toast("Thanks for your support");
            }
        });
        interstitialAdFB.loadAd();
    }

    @Override
    protected void onDestroy() {
        if (adViewFB != null) {
            adViewFB.destroy();
        }
        if (interstitialAdFB != null) {
            interstitialAdFB.destroy();
        }
        super.onDestroy();
    }

    public void toast(String text) {
        try {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toast(int resId) {
        try {
            Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void log(String tag, String text) {
        Log.d(tag, text);
    }

}
