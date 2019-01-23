package com.simson.www.ui.home.cases;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.simson.www.R;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.ui.adapter.TabViewPagerAdapter;
import com.simson.www.ui.adapter.TabViewPagerAdapterItem;
import com.simson.www.ui.base.BasePresenterActivity;

import java.util.List;

import butterknife.BindView;

public class CaseActivity extends BasePresenterActivity<CasePresenter, CaseContract.View> implements CaseContract.View {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_case;
    }

    @Override
    protected void initData() {
        mPresenter.getItemType();
    }

    @Override
    public void setItemType(List<ItemTypeBean> bean) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getSupportFragmentManager(),
                TabViewPagerAdapterItem.createCaseItemFragments(bean));
        if (viewPager == null) return;
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("案例");
        return true;
    }

    @Override
    protected CasePresenter createPresenter() {
        return new CasePresenter();
    }

}
