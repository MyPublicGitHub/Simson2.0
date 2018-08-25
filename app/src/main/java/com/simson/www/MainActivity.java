package com.simson.www;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.simson.www.ui.base.BaseActivity;
import com.simson.www.ui.home.HomeFragment;
import com.simson.www.utils.BottomNavigationViewHelper;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(0);
                    return true;
                case R.id.navigation_community:
                    switchFragment(1);
                    return true;
                case R.id.navigation_hospital:
                    switchFragment(2);
                    return true;
                case R.id.navigation_shop:
                    switchFragment(3);
                    return true;
                case R.id.navigation_mine:
                    switchFragment(4);
                    return true;
            }
            return false;
        }
    };

    public ArrayList<Fragment> mFragments;
    private int mLastFgIndex;
    /**
     * 切换fragment
     */
    public void switchFragment(int position) {
        if (position >= mFragments.size()) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded()) {
            ft.add(R.id.container, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }

    @Override
    protected void receiveEvent(Object object) {

    }

    @Override
    protected String registerEvent() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean initToolbar() {
        return false;
    }

    @Override
    protected void getIntent(Intent intent) {

    }

    @Override
    protected void initViews() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        //mFragments.add(new CommunityFragment());
        switchFragment(0);
    }

}
