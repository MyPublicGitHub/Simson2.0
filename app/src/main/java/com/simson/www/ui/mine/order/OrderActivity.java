package com.simson.www.ui.mine.order;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.simson.www.R;
import com.simson.www.ui.adapter.MyViewPageAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.order.item.OrderFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class OrderActivity extends BasePresenterActivity {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    String mStatus;//status：1待支付；2已支付；空全部

    @Override
    protected void getIntent(Intent intent) {
        mStatus = intent.getStringExtra("status");
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected void initViews() {

        ArrayList<Fragment> frag = new ArrayList<>();
        frag.add(OrderFragment.newInstance(""));
        frag.add(OrderFragment.newInstance("1"));//status：1待支付；2已支付；空全部
        frag.add(OrderFragment.newInstance("2"));
        frag.add(OrderFragment.newInstance("3"));
        ArrayList<String> titleData = new ArrayList<>();
        titleData.add("全部");
        titleData.add("未付款");
        titleData.add("已付款");
        titleData.add("待评价");
        MyViewPageAdapter adapter = new MyViewPageAdapter(getSupportFragmentManager(), titleData, frag);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        if (mStatus.equals("")) {
            viewPager.setCurrentItem(0);
        } else if (mStatus.equals("1")) {
            viewPager.setCurrentItem(1);
        } else if (mStatus.equals("2")) {
            viewPager.setCurrentItem(2);
        } else {
            viewPager.setCurrentItem(3);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mStatus="";
                }else if (position == 1) {
                    mStatus="1";
                }else if (position == 2) {
                    mStatus="2";
                }else if (position == 3) {
                    mStatus="3";
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("订单");
        return true;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}
