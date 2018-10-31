package com.simson.www.ui.shop;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.ui.adapter.ItemTypeGradeAdapter;
import com.simson.www.ui.adapter.ShopCommodityAdapter;
import com.simson.www.ui.adapter.ShopIntegralAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.mine.integral.mall.IntegralMallActivity;
import com.simson.www.ui.shop.detail.CommodityDetailActivity;
import com.simson.www.ui.shop.search.SearchShopActivity;
import com.simson.www.ui.shop.type.ShopTypeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class ShopFragment extends BasePresenterFragment<ShopPresenter, ShopContract.View> implements ShopContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rv_integral)
    RecyclerView rvIntegral;
    @BindView(R.id.rv_commodity)
    RecyclerView rvCommodity;

    ShopIntegralAdapter mShopIntegralAdapter;
    ShopCommodityAdapter mShopCommodityAdapter;

    @BindView(R.id.srl_integral)
    SmartRefreshLayout srlIntegral;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private int mPageCommodity = 1, mPageIntegral = 1;
    ItemTypeGradeAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initViews(View view) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        adapter = new ItemTypeGradeAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        //adapter.bindToRecyclerView(recyclerView);
        //adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<ItemTypeBean> bean = (List<ItemTypeBean>) adapter.getData();
            String id = bean.get(position).getItemTypeId();
            startActivity(new Intent(getActivity(), ShopTypeActivity.class).putExtra("itemTypeId", id));
        });

        mShopIntegralAdapter = new ShopIntegralAdapter(null);
        rvIntegral.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvIntegral.setAdapter(mShopIntegralAdapter);
        mShopIntegralAdapter.bindToRecyclerView(rvIntegral);
        mShopIntegralAdapter.setEmptyView(R.layout.list_empty_view);
        mShopIntegralAdapter.setOnItemClickListener((adapter, view1, position) -> {
                    List<ShopListBean> data = (List<ShopListBean>) adapter.getData();
                    ShopListBean shopListBean = data.get(position);
                    startActivity(new Intent(getActivity(), CommodityDetailActivity.class).putExtra("id", shopListBean.getItem_id()));
                }
        );

        mShopCommodityAdapter = new ShopCommodityAdapter(null);
        rvCommodity.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCommodity.setAdapter(mShopCommodityAdapter);
        mShopCommodityAdapter.setOnItemClickListener((adapter, view1, position) -> {
                    List<ShopListBean> data = (List<ShopListBean>) adapter.getData();
                    ShopListBean shopListBean = data.get(position);
                    startActivity(new Intent(getActivity(), CommodityDetailActivity.class).putExtra("id", shopListBean.getItem_id()));
                }
        );
        mShopCommodityAdapter.bindToRecyclerView(rvCommodity);
        mShopCommodityAdapter.setEmptyView(R.layout.list_empty_view);
        rvCommodity.setNestedScrollingEnabled(false);

        setRefresh();

        mPresenter.getItemType();
        mPresenter.getShopList(0);
        mPresenter.getShopList(1);
    }

    @Override
    public void setItemType(List<ItemTypeBean> bean) {
        if (bean == null) {
            return;
        }
        adapter.replaceData(bean);
    }

    @Override
    public void setShopListResponse(List<ShopListBean> bean) {
        if (bean == null) {
            return;
        }
        if (mPageCommodity == 1) {
            mShopCommodityAdapter.replaceData(bean);

        } else {
            mShopCommodityAdapter.addData(bean);
        }
        if (bean.size() == 0) {
            refreshLayout.setNoMoreData(true);
        }
    }

    @Override
    public void setShopIntegralListResponse(List<ShopListBean> bean) {
        if (bean == null) {
            return;
        }
        if (mPageIntegral == 1) {
            mShopIntegralAdapter.replaceData(bean);

        } else {
            mShopIntegralAdapter.addData(bean);
        }
        if (bean.size() == 0) {
            srlIntegral.setNoMoreData(true);
        }
    }

    String itemTypeId, search;


    @Override
    public String itemTypeId() {
        return itemTypeId;
    }

    @Override
    public String search() {
        return search;
    }

    @Override
    public int getPageCommodity() {
        return mPageCommodity;
    }

    @Override
    public int getPageIntegral() {
        return mPageIntegral;
    }

    @Override
    protected void getBundle(Bundle bundle) {

    }

    private void setRefresh() {
        refreshLayout.setOnRefreshListener(refreshLayouts -> {
            mPageCommodity = 1;
            mPresenter.getShopList(0);
            refreshLayout.setNoMoreData(false);
            refreshLayouts.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayouts -> {
            mPageCommodity++;
            mPresenter.getShopList(0);
            refreshLayouts.finishLoadMore();
        });
        srlIntegral.setOnRefreshListener(refreshLayouts -> {
            mPageIntegral = 1;
            mPresenter.getShopList(1);
            srlIntegral.setNoMoreData(false);
            refreshLayouts.finishRefresh();
        });
        srlIntegral.setOnLoadMoreListener(refreshLayouts -> {
            mPageIntegral++;
            mPresenter.getShopList(1);
            refreshLayouts.finishLoadMore();
        });
    }

    @Override
    protected ShopPresenter createPresenter() {
        return new ShopPresenter();
    }


    @OnClick({R.id.iv_mall, R.id.ll_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_search:
                startActivity(new Intent(getActivity(), SearchShopActivity.class));
                break;
            case R.id.iv_mall:
                startActivity(new Intent(getActivity(), IntegralMallActivity.class));
                break;
        }
    }
}
