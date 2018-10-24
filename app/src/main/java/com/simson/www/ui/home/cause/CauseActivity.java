package com.simson.www.ui.home.cause;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.home.CauseListBean;
import com.simson.www.ui.adapter.CauseAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;

import java.util.List;

import butterknife.BindView;

public class CauseActivity extends BasePresenterActivity<CausePresenter, CauseContract.View>
        implements CauseContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cause;
    }

    CauseAdapter adapter;

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CauseAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<CauseListBean> bean = (List<CauseListBean>) adapter.getData();
            startActivity(new Intent(this, WebViewActivity.class)
                    .putExtra(Const.WEB_VIEW_TITLE, "脱发原因")
                    .putExtra(Const.WEB_VIEW_URL, bean.get(position).getAlopecia_cause_link()));
        });
        setRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.getCauseList();
    }

    @Override
    public void showCauseList(List<CauseListBean> bean) {
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
            mPresenter.getCauseList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getCauseList();
            refreshLayout.finishLoadMore();
        });
    }


    private int mPage = 1;

    @Override
    public String getPage() {
        return mPage + "";
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("脱发原因");
        return true;
    }

    @Override
    protected CausePresenter createPresenter() {
        return new CausePresenter();
    }

}
