package com.simson.www.ui.home.hospital.item;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.net.bean.mine.CaseBean;
import com.simson.www.ui.adapter.HospitalItemAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.home.hospital.detail.HospitalDetailActivity;

import java.util.List;

import butterknife.BindView;


public class HospitalItemFragment extends BasePresenterFragment<HospitalItemPresenter, HospitalItemContract.View>
        implements HospitalItemContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    String mCityId;

    @Override
    protected void getBundle(Bundle bundle) {
        mCityId = getArguments().getString("cityId");
    }

    public static HospitalItemFragment newInstance(String cityId) {
        Bundle args = new Bundle();
        args.putString("cityId", cityId);
        HospitalItemFragment fragment = new HospitalItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hospital_item;
    }

    HospitalItemAdapter adapter;

    @Override
    protected void initViews(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HospitalItemAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<HospitalItemBean> bean = (List<HospitalItemBean>) adapter.getData();
            String hospitalId = bean.get(position).getHospital_id();
            startActivity(new Intent(getContext(), HospitalDetailActivity.class)
                    .putExtra("hospitalId", hospitalId));
        });
        setRefresh();
        mPresenter.getHospital();
    }


    private int mPage = 1;

    @Override
    public void showHospital(List<HospitalItemBean> bean) {
        if (bean == null) {
            return;
        }
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
    protected HospitalItemPresenter createPresenter() {
        return new HospitalItemPresenter();
    }

    @Override
    public String getPage() {
        return mPage + "";
    }

    @Override
    public String getCityId() {
        return mCityId;
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.getHospital();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getHospital();
            refreshLayout.finishLoadMore();
        });
    }

}
