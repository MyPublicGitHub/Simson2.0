package com.simson.www.ui.mine.test;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.mine.HospitalTestBean;
import com.simson.www.ui.adapter.HospitalTestAdapter;
import com.simson.www.ui.base.BasePresenterFragment;

import java.util.List;

import butterknife.BindView;

public class HospitalTestFragment extends BasePresenterFragment<HospitalTestPresenter, HospitalTestContract.View>
        implements HospitalTestContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    int mPage = 1;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hospital_test;
    }

    HospitalTestAdapter adapter;

    @Override
    protected void getBundle(Bundle bundle) {

    }

    @Override
    protected void initViews(View view) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HospitalTestAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            HospitalTestBean bean = (HospitalTestBean) adapter.getData().get(position);
            String id = bean.getTesting_id();
            //startActivity(new Intent(this, SubscribeDetailActivity.class).putExtra("id", id));
        });
        setRefresh();
        mPresenter.hospitalTestingList();

    }

    @Override
    public String pageIndex() {
        return mPage + "";
    }

    @Override
    public void hospitalTestingList(List<HospitalTestBean> bean) {
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
    protected HospitalTestPresenter createPresenter() {
        return new HospitalTestPresenter();
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.hospitalTestingList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.hospitalTestingList();
            refreshLayout.finishLoadMore();
        });
    }

}
