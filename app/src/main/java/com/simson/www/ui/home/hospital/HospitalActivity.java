package com.simson.www.ui.home.hospital;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.simson.www.R;
import com.simson.www.net.bean.home.CityListBean;
import com.simson.www.ui.adapter.TabViewPagerAdapter;
import com.simson.www.ui.adapter.TabViewPagerAdapterItem;
import com.simson.www.ui.base.BasePresenterActivity;

import java.util.List;

import butterknife.BindView;

public class HospitalActivity extends BasePresenterActivity<HospitalActivityPresenter, HospitalActivityContract.View>
        implements HospitalActivityContract.View {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_hospital;
    }


    @Override
    protected void initData() {
        mPresenter.getCityList();
    }

    @Override
    public void setCityList(List<CityListBean> bean) {
        if (bean == null) return;
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getSupportFragmentManager(),
                TabViewPagerAdapterItem.createHospitalItemFragments(bean));
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("新生医院");
        return true;
    }

    @Override
    protected HospitalActivityPresenter createPresenter() {
        return new HospitalActivityPresenter();
    }

}
