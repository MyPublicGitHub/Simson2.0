package com.simson.www.ui.mine.alopecias;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.mine.AlopeciaBean;
import com.simson.www.ui.adapter.AlopeciaAdapter;
import com.simson.www.ui.adapter.FollowAdapter;
import com.simson.www.ui.base.BasePresenterActivity;

import java.util.List;

import butterknife.BindView;

public class AlopeciaActivity extends BasePresenterActivity<AlopeciaPresenter, AlopeciaContract.View>
        implements AlopeciaContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private int mPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_alopecias2;
    }

    AlopeciaAdapter adapter;

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AlopeciaAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        setRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.alopeciaTestingList();
    }

    @Override
    protected AlopeciaPresenter createPresenter() {
        return new AlopeciaPresenter();
    }

    @Override
    public int pageIndex() {
        return mPage;
    }

    @Override
    public void alopeciaTestingList(List<AlopeciaBean> bean) {
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
        mTitle.setText("脱发检测");
        return true;
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.alopeciaTestingList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.alopeciaTestingList();
            refreshLayout.finishLoadMore();
        });
    }
}
