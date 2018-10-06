package com.simson.www.ui.main;

import android.graphics.Paint;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.CommunityFragment;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.home.HomeFragment;
import com.simson.www.ui.hospital.HospitalFragment;
import com.simson.www.ui.mine.MineFragment;
import com.simson.www.ui.shop.ShopFragment;
import com.simson.www.utils.BottomNavigationViewHelper;
import com.simson.www.utils.SPUtils;

import java.util.ArrayList;

public class MainActivity extends BasePresenterActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
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
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new CommunityFragment());
        mFragments.add(new HospitalFragment());
        mFragments.add(new ShopFragment());
        mFragments.add(new MineFragment());
        switchFragment(0);
//        TextView textView = helper.getView(R.id.tv_original);
//        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        SPUtils.put(Const.USER_INFO.CUSTOMER_ID,"2018090115357871316905625");
    }

}
