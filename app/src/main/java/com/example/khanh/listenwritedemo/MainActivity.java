package com.example.khanh.listenwritedemo;

import android.annotation.TargetApi;
import android.app.ApplicationErrorReport;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.khanh.listenwritedemo.fragment.FragmentLanguage;
import com.example.khanh.listenwritedemo.fragment.FragmentSection;

import java.io.PrintWriter;
import java.io.StringWriter;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */

    private boolean doubleBackToExitPressedOnce = false;
    private Fragment curFragment;
    private String categoryCurrent;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.inject(this); // Init views
        onOpenFragment(new FragmentSection(), false);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //StasusBar Color
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorBlue));
        }
    }

    public void setUpToolbar(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    public void onOpenFragment(Fragment fragment, boolean isAddToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        curFragment = fragment;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAddToBackStack) {
            fragmentTransaction.replace(R.id.container, fragment).addToBackStack(null);
        } else {
            fragmentTransaction.replace(R.id.container, fragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_listening) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.yobimi.bbclearningenglish")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.yobimi.bbclearningenglish")));
            }
        }else if (id == R.id.nav_chat) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.yobimi.chatenglish")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.yobimi.chatenglish")));
            }
        } else if (id == R.id.nav_letlearn) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.yobimi.voaletlearnenglish.englishgrammar.englishspeak")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.yobimi.voaletlearnenglish.englishgrammar.englishspeak")));
            }
        } else if (id == R.id.nav_rate) {
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            try {
                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.yobimi.bbclearningenglish")));
            } catch (android.content.ActivityNotFoundException anfe) {
                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.yobimi.bbclearningenglish")));
            }
        } else if (id == R.id.nav_application) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=6042401600798620557")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=6042401600798620557")));
            }

        } else if (id == R.id.nav_follow) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/learningenglish.yobimi/")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/learningenglish.yobimi/")));
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressWarnings("unused")
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void reportABug() {
        try {
            int i = 3 / 0;
        } catch (Exception e) {
            ApplicationErrorReport report = new ApplicationErrorReport();
            report.packageName = report.processName = getApplication().getPackageName();
            report.time = System.currentTimeMillis();
            report.type = ApplicationErrorReport.TYPE_CRASH;
            report.systemApp = false;

            ApplicationErrorReport.CrashInfo crash = new ApplicationErrorReport.CrashInfo();
            crash.exceptionClassName = e.getClass().getSimpleName();
            crash.exceptionMessage = e.getMessage();

            StringWriter writer = new StringWriter();
            PrintWriter printer = new PrintWriter(writer);
            e.printStackTrace(printer);

            crash.stackTrace = writer.toString();

            StackTraceElement stack = e.getStackTrace()[0];
            crash.throwClassName = stack.getClassName();
            crash.throwFileName = stack.getFileName();
            crash.throwLineNumber = stack.getLineNumber();
            crash.throwMethodName = stack.getMethodName();

            report.crashInfo = crash;

            Intent intent = new Intent(Intent.ACTION_APP_ERROR);
            intent.putExtra(Intent.EXTRA_BUG_REPORT, report);
            startActivity(intent);
        }
    }

    // Optional for press again to exit app
    // @Override
    // public void onBackPressed() {
    //     FragmentManager fm = getSupportFragmentManager();

    //     if (fm.getBackStackEntryCount() > 0) {
    //         superBackPress();
    //         return;
    //     }
    //     if (doubleBackToExitPressedOnce || fm.getBackStackEntryCount() != 0) {
    //         super.onBackPressed();
    //         return;
    //     }

    //     this.doubleBackToExitPressedOnce = true;
    //     Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

    //     new Handler().postDelayed(new Runnable() {

    //         @Override
    //         public void run() {
    //             doubleBackToExitPressedOnce = false;
    //         }
    //     }, 2000);
    // }

    // public void superBackPress() {
    //     super.onBackPressed();
    // }
}