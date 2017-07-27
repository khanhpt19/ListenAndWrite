package com.example.khanh.listenwritedemo.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;

import com.example.khanh.listenwritedemo.R;

/**
 * Created by Yobimi on 12/21/2016.
 */

public class AnimHelper {

    private AnimHelper() {
    }

    public static void startPulseAnimation(View view, int repeatTime, int duration) {
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("scaleX", 0.8f),
                PropertyValuesHolder.ofFloat("scaleY", 0.8f));
        scaleDown.setDuration(duration);
        scaleDown.setRepeatCount(repeatTime);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDown.start();
    }

    public static void setVisible(View view) {
        view.setVisibility(View.VISIBLE);
        AlphaAnimation animation1 = new AlphaAnimation(0f, 1.0f);
        animation1.setDuration(1000);
        view.startAnimation(animation1);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void reveal(View pivot, View myView, float finalRadius) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = (int) (pivot.getX() + pivot.getWidth() / 2);
            int cy = (int) (pivot.getY() + pivot.getHeight() / 2);

            // create the animator for this view (the start radius is zero)
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

            // make the view visible and start the animation
            myView.setVisibility(View.VISIBLE);
            anim.start();
        } else {
            slideInRightAnimation(myView);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void hideReveal(View pivot, final View myView, float initialRadius) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = (int) (pivot.getX() + pivot.getWidth() / 2);
            int cy = (int) (pivot.getY() + pivot.getHeight() / 2);

            // create the animation (the final radius is zero)
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

            // make the view invisible when the animation is done
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            });
            anim.start();
        } else {
            slideOutRightAnimation(myView);
        }
    }

    public static void slideInRightAnimation(View view) {
        view.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(view.getContext(),
                R.anim.slide_in_right);
        view.startAnimation(animation);
    }


    public static void slideOutRightAnimation(final View view) {
        Animation animation = AnimationUtils.loadAnimation(view.getContext(),
                R.anim.slide_out_right);
        animation.setAnimationListener(new SimpleAnimation() {
            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
        view.startAnimation(animation);
    }

    public static void fadeIn(View tvMsg, int duration) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(duration);
    }

    public static void fadeOut(View view, int duration) {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setDuration(duration);
    }

    public static void fadeInAndOut(final View view, int existTime) {
        view.setVisibility(View.VISIBLE);
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(500);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(existTime);
        fadeOut.setDuration(500);
        fadeOut.setAnimationListener(new SimpleAnimation() {
            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }
        });

        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);
        view.setAnimation(animation);
    }

    public static class SimpleAnimation implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}


// Example:
//        AnimHelper.reveal(ivHint, tvHint, tvHint.getWidth());
//        ivHint.setVisibility(View.GONE);
//        uiHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (isAlive()) {
//                    ivHint.setVisibility(View.VISIBLE);
//                    AnimHelper.hideReveal(ivHint, tvHint, tvHint.getWidth());
//                }
//            }
//        }, 3000);