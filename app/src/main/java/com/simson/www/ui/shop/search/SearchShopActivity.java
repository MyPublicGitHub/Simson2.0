package com.simson.www.ui.shop.search;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.ui.adapter.ShopCommodityAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.shop.ShopContract;
import com.simson.www.ui.shop.ShopPresenter;
import com.simson.www.ui.shop.detail.CommodityDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchShopActivity extends BasePresenterActivity<ShopPresenter, ShopContract.View> implements ShopContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.et_search)
    EditText etSearch;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_shop;
    }

    ShopCommodityAdapter mShopCommodityAdapter;

    @Override
    protected void initViews() {

        mShopCommodityAdapter = new ShopCommodityAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mShopCommodityAdapter);
        mShopCommodityAdapter.setOnItemClickListener((adapter, view1, position) -> {
                    List<ShopListBean> data = (List<ShopListBean>) adapter.getData();
                    ShopListBean shopListBean = data.get(position);
                    startActivity(new Intent(this, CommodityDetailActivity.class).putExtra("id", shopListBean.getItem_id()));
                }
        );
        mShopCommodityAdapter.bindToRecyclerView(recyclerView);
        mShopCommodityAdapter.setEmptyView(R.layout.list_empty_view);
        recyclerView.setNestedScrollingEnabled(false);

        setRefresh();
    }

    @Override
    public void setItemType(List<ItemTypeBean> bean) {
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
            mRefreshLayout.setNoMoreData(true);
        }
    }

    @Override
    public void setShopIntegralListResponse(List<ShopListBean> bean) {

    }

    String itemTypeId;


    @Override
    public String itemTypeId() {
        return itemTypeId;
    }

    @Override
    public String search() {
        return etSearch.getText().toString();
    }

    int mPageCommodity = 1;

    @Override
    public int getPageCommodity() {
        return mPageCommodity;
    }

    @Override
    public int getPageIntegral() {
        return 1;
    }


    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayouts -> {
            mPageCommodity = 1;
            mPresenter.getShopList(0);
            mRefreshLayout.setNoMoreData(false);
            refreshLayouts.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayouts -> {
            mPageCommodity++;
            mPresenter.getShopList(0);
            refreshLayouts.finishLoadMore();
        });
    }

    @Override
    protected ShopPresenter createPresenter() {
        return new ShopPresenter();
    }

    @OnClick({R.id.iv_back, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                mPresenter.getShopList(0);
                break;
        }
    }
}
