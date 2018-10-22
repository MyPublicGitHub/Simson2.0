package com.simson.www.ui.mine.fans;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.mine.FansBean;
import com.simson.www.ui.adapter.DiaryAdapter;
import com.simson.www.ui.adapter.FansAdapter;
import com.simson.www.ui.base.BasePresenterActivity;

import java.util.List;

import butterknife.BindView;

public class FansActivity extends BasePresenterActivity<FansPresenter, FansContract.View>
        implements FansContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private int mPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fans;
    }

    FansAdapter adapter;

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FansAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        setRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.queryMyFansList();
    }

    @Override
    protected FansPresenter createPresenter() {
        return new FansPresenter();
    }

    @Override
    public int pageIndex() {
        return mPage;
    }

    @Override
    public void queryMyFansList(List<FansBean> bean) {
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
    protected boolean initToolbar() {
        mTitle.setText("我的粉丝");
        return true;
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.queryMyFansList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.queryMyFansList();
            refreshLayout.finishLoadMore();
        });
    }
}
