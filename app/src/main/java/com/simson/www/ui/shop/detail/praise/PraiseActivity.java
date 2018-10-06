package com.simson.www.ui.shop.detail.praise;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.shop.CommentBean;
import com.simson.www.ui.adapter.HomeItemAdapter;
import com.simson.www.ui.adapter.PraiseAdapter;
import com.simson.www.ui.base.BasePresenterActivity;

import java.util.List;

import butterknife.BindView;

public class PraiseActivity extends BasePresenterActivity<PraisePresenter, PraiseContract.View>
        implements PraiseContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_praise;
    }
    PraiseAdapter adapter;
    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PraiseAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setFocusable(false);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        setRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.getCommodityQueryItemComment();
    }

    @Override
    public void showCommodityQueryItemComment(List<CommentBean> bean) {
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
    String itemId;
    int mPage = 1;

    @Override
    protected void getIntent(Intent intent) {
        itemId = intent.getStringExtra("itemId");
    }

    @Override
    public String getItemId() {
        return itemId;
    }

    @Override
    public String getPageIndex() {
        return mPage + "";
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.getCommodityQueryItemComment();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getCommodityQueryItemComment();
            refreshLayout.finishLoadMore();
        });
    }
    @Override
    protected PraisePresenter createPresenter() {
        return new PraisePresenter();
    }
}
