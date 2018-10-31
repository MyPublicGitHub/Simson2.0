package com.simson.www.ui.mine.subscribe;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.simson.www.R;
import com.simson.www.ui.adapter.MyViewPageAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.circle.FriendCircleFragment;
import com.simson.www.ui.community.expert.ExpertFragment;
import com.simson.www.ui.community.knowledge.KnowledgeFragment;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.test.HospitalTestFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class SubscribeActivity extends BasePresenterActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_subscribe;
    }

    @Override
    protected void initViews() {
        ArrayList<Fragment> frag = new ArrayList<>();
        frag.add(new SubscribeFragment());
        frag.add(new HospitalTestFragment());
        ArrayList<String> titleData = new ArrayList<>();
        titleData.add("预约");
        titleData.add("到院检测");
        MyViewPageAdapter adapter = new MyViewPageAdapter(getSupportFragmentManager(), titleData, frag);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    protected boolean initToolbar() {
        mTitle.setText("我的预约");
        return true;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}
