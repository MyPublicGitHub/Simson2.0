package com.simson.www.ui.community.knowledge;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simson.www.R;
import com.simson.www.ui.adapter.HDACaseDiaryAdapter;
import com.simson.www.ui.adapter.TabViewPagerAdapter;
import com.simson.www.ui.adapter.TabViewPagerAdapterItem;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.core.presenter.BasePresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class KnowledgeFragment extends BasePresenterFragment {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void getBundle(Bundle bundle) {

    }


    @Override
    protected void initViews(View view) {
        ArrayList<String> titleData = new ArrayList<>();
        titleData.add("推荐");
        titleData.add("关注");
        titleData.add("全部");
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager(),
                TabViewPagerAdapterItem.createKnowledgeItemFragments(titleData));
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}
