package com.simson.www.ui.mine.follow;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.mine.FansBean;
import com.simson.www.net.bean.mine.FollowBean;
import com.simson.www.ui.adapter.FansAdapter;
import com.simson.www.ui.adapter.FollowAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.mine.fans.FansContract;
import com.simson.www.ui.mine.fans.FansPresenter;

import java.util.List;

import butterknife.BindView;

public class FollowActivity extends BasePresenterActivity<FollowPresenter, FollowContract.View>
        implements FollowContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private int mPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_follow;
    }

    FollowAdapter adapter;

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FollowAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        setRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.queryMyFollowList();
    }

    @Override
    protected FollowPresenter createPresenter() {
        return new FollowPresenter();
    }

    @Override
    public int pageIndex() {
        return mPage;
    }

    @Override
    public void queryMyFollowList(List<FollowBean> bean) {
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
        mTitle.setText("我的关注");
        return true;
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.queryMyFollowList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.queryMyFollowList();
            refreshLayout.finishLoadMore();
        });
    }
}
