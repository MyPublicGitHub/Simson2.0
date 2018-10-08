package com.simson.www.ui.shop;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.ui.adapter.ShopCommodityAdapter;
import com.simson.www.ui.adapter.ShopIntegralAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.shop.detail.CommodityDetailActivity;
import com.simson.www.ui.shop.type.ShopTypeActivity;
import com.youth.banner.Banner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ShopFragment extends BasePresenterFragment<ShopPresenter, ShopContract.View> implements ShopContract.View {


    @BindView(R.id.rv_integral)
    RecyclerView rvIntegral;
    @BindView(R.id.rv_commodity)
    RecyclerView rvCommodity;

    ShopIntegralAdapter mShopIntegralAdapter;
    ShopCommodityAdapter mShopCommodityAdapter;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.srl_integral)
    SmartRefreshLayout srlIntegral;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private int mPageCommodity = 1, mPageIntegral = 1;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initViews(View view) {

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

        mPresenter.getShopList();
        mPresenter.getShopIntegralList();
    }



    @OnClick({R.id.ll_hair,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_hair:
                startActivity(new Intent(getActivity(), ShopTypeActivity.class));
                break;
        }
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

    @Override
    public String getIsPoint() {
        return "0";
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
            mPresenter.getShopList();
            refreshLayout.setNoMoreData(false);
            refreshLayouts.finishRefresh();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayouts -> {
            mPageCommodity++;
            mPresenter.getShopList();
            refreshLayouts.finishLoadMore();
        });
        srlIntegral.setOnRefreshListener(refreshLayouts -> {
            mPageIntegral = 1;
            mPresenter.getShopIntegralList();
            srlIntegral.setNoMoreData(false);
            refreshLayouts.finishRefresh();
        });
        srlIntegral.setOnLoadMoreListener(refreshLayouts -> {
            mPageIntegral++;
            mPresenter.getShopIntegralList();
            refreshLayouts.finishLoadMore();
        });
    }

    @Override
    protected ShopPresenter createPresenter() {
        return new ShopPresenter();
    }

}
