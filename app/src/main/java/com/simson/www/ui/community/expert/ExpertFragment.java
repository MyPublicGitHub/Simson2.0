package com.simson.www.ui.community.expert;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.community.DoctorBean;
import com.simson.www.net.bean.home.HospitalBean;
import com.simson.www.ui.adapter.ExpertCommunityAdapter;
import com.simson.www.ui.adapter.TabViewPagerAdapter;
import com.simson.www.ui.adapter.TabViewPagerAdapterItem;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.core.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ExpertFragment extends BasePresenterFragment<ExpertPresenter, ExpertContract.View> implements ExpertContract.View {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.rc_header)
    RecyclerView recyclerView;
    private int mPage = 1;
    ExpertCommunityAdapter adapter;

    @Override
    protected void getBundle(Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_expert;
    }
    @Override
    protected void initViews(View view) {
        setRefresh();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter = new ExpertCommunityAdapter(null);
        recyclerView.setAdapter(adapter);
//        adapter.bindToRecyclerView(recyclerView);
//        adapter.setEmptyView(R.layout.list_empty_view);
        ArrayList<String> titleData = new ArrayList<>();
        titleData.add("推荐");
        titleData.add("关注");
        titleData.add("全部");
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager(),
                TabViewPagerAdapterItem.createExpertItemFragments(titleData));
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        mPresenter.getDoctorList();

    }

    @Override
    public void showDoctorList(DoctorBean beans) {
        if (beans == null&&beans.getList()!=null) {
            return;
        }
        List<DoctorBean.DoctorItemBean> bean = beans.getList();
        if (mPage == 1) {
            adapter.replaceData(bean);

        } else {
            adapter.addData(bean);
        }
        if (bean.size() == 0) {
            mRefreshLayout.setNoMoreData(true);
        }
    }

    @Override
    public void getHospitalList(List<HospitalBean> bean) {

    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.getDoctorList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getDoctorList();
            refreshLayout.finishLoadMore();
        });
    }
    @Override
    protected ExpertPresenter createPresenter() {
        return new ExpertPresenter();
    }


    @Override
    public int getPage() {
        return mPage;
    }

    @Override
    public String hospitalId() {
        return null;
    }


}
