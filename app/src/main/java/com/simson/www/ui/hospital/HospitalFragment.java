package com.simson.www.ui.hospital;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.ui.adapter.HospitalFragmentAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.shop.detail.CommodityDetailActivity;

import java.util.List;

import butterknife.BindView;

public class HospitalFragment extends BasePresenterFragment<HospitalPresenter, HospitalContract.View>
        implements HospitalContract.View {

    HospitalFragmentAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private int mPage = 1;

    @Override
    protected HospitalPresenter createPresenter() {
        return new HospitalPresenter();
    }

    @Override
    protected void getBundle(Bundle bundle) {

    }

    @Override
    protected void initViews(View view) {
        adapter = new HospitalFragmentAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
                    List<ShopListBean> data = (List<ShopListBean>) adapter.getData();
                    ShopListBean shopListBean = data.get(position);
                    startActivity(new Intent(getActivity(), CommodityDetailActivity.class)
                            .putExtra("id", shopListBean.getItem_id()));
                }
        );
        setRefresh();
        mPresenter.getShopList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hospital;
    }

    @Override
    public void getShopList(List<ShopListBean> bean) {
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
    public String pageIndex() {
        return mPage + "";
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.getShopList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getShopList();
            refreshLayout.finishLoadMore();
        });
    }
}
