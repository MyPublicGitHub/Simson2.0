package com.simson.www.ui.mine.integral.detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.mine.IntegralDetailBean;
import com.simson.www.ui.adapter.IntegralDetailAdapter;
import com.simson.www.ui.base.BasePresenterActivity;

import java.util.List;

import butterknife.BindView;

public class IntegralDetailActivity extends BasePresenterActivity<IntegralDetailPresenter, IntegralDetailContract.View>
        implements IntegralDetailContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private int mPage = 1;
    private IntegralDetailAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral_detail;
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new IntegralDetailAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        setRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.pointList();
    }

    @Override
    public String pageIndex() {
        return mPage + "";
    }

    @Override
    public void pointList(List<IntegralDetailBean> bean) {
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
    protected IntegralDetailPresenter createPresenter() {
        return new IntegralDetailPresenter();
    }
    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.pointList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.pointList();
            refreshLayout.finishLoadMore();
        });
    }
}
