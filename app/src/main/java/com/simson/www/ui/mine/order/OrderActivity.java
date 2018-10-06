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

public class OrderActivity extends BasePresenterActivity{
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
        frag.add(OrderFragment.newInstance("1"));//status：1待支付；2已支付；空全部
        frag.add(OrderFragment.newInstance("2"));
        frag.add(OrderFragment.newInstance(""));
        ArrayList<String> titleData = new ArrayList<>();
        titleData.add("待付款");
        titleData.add("待发货");
        titleData.add("全部");
        MyViewPageAdapter adapter = new MyViewPageAdapter(getSupportFragmentManager(),titleData,frag);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        if (mStatus.equals("1")){
            viewPager.setCurrentItem(0);
        }else if (mStatus.equals("2")){
            viewPager.setCurrentItem(1);
        }else {
            viewPager.setCurrentItem(2);
        }
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
