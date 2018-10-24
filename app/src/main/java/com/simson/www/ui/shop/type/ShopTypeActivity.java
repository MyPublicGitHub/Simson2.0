package com.simson.www.ui.shop.type;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.ui.adapter.ShopCommodityAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.shop.ShopContract;
import com.simson.www.ui.shop.ShopPresenter;
import com.simson.www.ui.shop.detail.CommodityDetailActivity;

import java.util.List;

import butterknife.BindView;

public class ShopTypeActivity extends BasePresenterActivity<ShopPresenter, ShopContract.View> implements ShopContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_type;
    }

    ShopCommodityAdapter adapter;

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShopCommodityAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<ShopListBean> data = (List<ShopListBean>) adapter.getData();
            ShopListBean shopListBean = data.get(position);
            startActivity(new Intent(this, CommodityDetailActivity.class).putExtra("id", shopListBean.getItem_id()));
        });
        setRefresh();

    }

    @Override
    protected void initData() {
        mPresenter.getShopList(0);
    }

    @Override
    protected ShopPresenter createPresenter() {
        return new ShopPresenter();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("分类商品");
        return true;
    }

    @Override
    protected void getIntent(Intent intent) {
        itemTypeId = intent.getStringExtra("itemTypeId");
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
        return 0;
    }

    int mPageCommodity = 1;

    @Override
    public void setShopListResponse(List<ShopListBean> bean) {
        if (bean == null) {
            return;
        }
        if (mPageCommodity == 1) {
            adapter.replaceData(bean);

        } else {
            adapter.addData(bean);
        }
        if (bean.size() == 0) {
            mRefreshLayout.setNoMoreData(true);
        }
    }

    @Override
    public void setShopIntegralListResponse(List<ShopListBean> bean) {

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
}
