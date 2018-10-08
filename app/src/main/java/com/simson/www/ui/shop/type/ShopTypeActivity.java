package com.simson.www.ui.shop.type;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.simson.www.R;
import com.simson.www.ui.adapter.MyViewPageAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.post.comment.MyCommentFragment;
import com.simson.www.ui.shop.detail.detail.DetailFragment;
import com.simson.www.ui.shop.detail.procedure.ProcedureFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class ShopTypeActivity extends BasePresenterActivity {


    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_type;
    }

    @Override
    protected void initViews() {
        ArrayList<Fragment> frag = new ArrayList<>();
        frag.add(DetailFragment.newInstance("2018009"));
        frag.add(ProcedureFragment.newInstance("2018009"));
        ArrayList<String> titleData = new ArrayList<>();
        titleData.add("商品详情");
        titleData.add("预约流程");
        MyViewPageAdapter adapter = new MyViewPageAdapter(getSupportFragmentManager(), titleData, frag);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
