package com.simson.www.ui.mine.subscribe;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.bean.mine.SubscribeListBean;
import com.simson.www.ui.adapter.HomeItemAdapter;
import com.simson.www.ui.adapter.SubscribeAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.diary.detail.DiaryDetailActivity;

import java.util.List;

import butterknife.BindView;

public class SubscribeActivity extends BasePresenterActivity<SubscribePresenter, SubscribeContract.View>
        implements SubscribeContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    int mPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_subscribe;
    }

    SubscribeAdapter adapter;

    @Override
    protected void initViews() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SubscribeAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<SubscribeListBean> bean = (List<SubscribeListBean>) adapter.getData();
            String id = bean.get(position).getSubscribe_id();
            //startActivity(new Intent(this, SubscribeDetailActivity.class).putExtra("id", id));
        });
        setRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.subscribeList();
    }

    @Override
    public String subscribeType() {
        return null;
    }

    @Override
    public String pageIndex() {
        return mPage + "";
    }

    @Override
    public void subscribeList(List<SubscribeListBean> bean) {
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
        mTitle.setText("我的预约");
        return true;
    }

    @Override
    protected SubscribePresenter createPresenter() {
        return new SubscribePresenter();
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.subscribeList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.subscribeList();
            refreshLayout.finishLoadMore();
        });
    }
}
