package com.simson.www.ui.community;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.ui.adapter.MyViewPageAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.core.presenter.BasePresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class CommunityFragment extends BasePresenterFragment {


    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void getBundle(Bundle bundle) {

    }

    @Override
    protected void initViews(View view) {
        ArrayList<String> titleDatas = new ArrayList<>();
        titleDatas.add("科普知识");
        titleDatas.add("专家提问");
        titleDatas.add("蜕变日记");
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new KnowledgeFragment());
        fragmentList.add(new ExpertFragment());
        fragmentList.add(new DiaryFragment());
        MyViewPageAdapter myViewPageAdapter = new MyViewPageAdapter(getActivity().getSupportFragmentManager(), titleDatas, fragmentList);
        viewPager.setAdapter(myViewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(myViewPageAdapter);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.tv_city, R.id.tv_search, R.id.iv_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                break;
            case R.id.tv_search:
                break;
            case R.id.iv_menu:
                break;
        }
    }
}
