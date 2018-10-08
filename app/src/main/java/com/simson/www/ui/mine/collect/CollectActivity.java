package com.simson.www.ui.mine.collect;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.home.CauseListBean;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.ui.adapter.IntegralDetailAdapter;
import com.simson.www.ui.adapter.ShopCommodityAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.home.cause.CauseContract;

import java.util.List;

import butterknife.BindView;

public class CollectActivity extends BasePresenterActivity<CollectPresenter, CollectContract.View> implements CollectContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private int mPage = 1;
    private ShopCommodityAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShopCommodityAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        setRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.itemCollectList();
    }

    @Override
    protected CollectPresenter createPresenter() {
        return new CollectPresenter();
    }

    @Override
    public String pageIndex() {
        return mPage + "";
    }

    @Override
    public void itemCollectList(List<ShopListBean> bean) {
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
    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.itemCollectList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.itemCollectList();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("收藏");
        return true;
    }
}
