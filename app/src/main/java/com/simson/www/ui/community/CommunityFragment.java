package com.simson.www.ui.community;


import android.content.Intent;
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
import com.simson.www.ui.community.circle.FriendCircleFragment;
import com.simson.www.ui.community.circle.save.SaveFriendCircleActivity;
import com.simson.www.ui.community.expert.ExpertFragment;
import com.simson.www.ui.community.expert.save.NewQuestionsActivity;
import com.simson.www.ui.community.knowledge.KnowledgeFragment;
import com.simson.www.ui.community.knowledge.search.SearchKnowledgeActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class CommunityFragment extends BasePresenterFragment {

    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initViews(View view) {

        ArrayList<Fragment> frag = new ArrayList<>();
        frag.add(new KnowledgeFragment());
        frag.add(new ExpertFragment());
        frag.add(new FriendCircleFragment());
        ArrayList<String> titleData = new ArrayList<>();
        titleData.add("科普知识");
        titleData.add("专家提问");
        titleData.add("发友圈");
        MyViewPageAdapter adapter = new MyViewPageAdapter(getChildFragmentManager(), titleData, frag);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    ivMenu.setImageResource(R.mipmap.ic_search);
                } else {
                    ivMenu.setImageResource(R.drawable.ic_camera_white_24dp);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.tv_search, R.id.iv_menu, R.id.ll_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
            case R.id.ll_search:
                startActivity(new Intent(getContext(),SearchKnowledgeActivity.class));
                break;
            case R.id.iv_menu:
                if (viewPager.getCurrentItem() == 0) {
                    ToastUtils.showToast("搜索");
                } else if (viewPager.getCurrentItem() == 1) {
                    startActivity(new Intent(getActivity(), NewQuestionsActivity.class));
                } else if (viewPager.getCurrentItem() == 2) {
                    startActivity(new Intent(getActivity(), SaveFriendCircleActivity.class));
                }
                break;
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected void getBundle(Bundle bundle) {

    }
}
