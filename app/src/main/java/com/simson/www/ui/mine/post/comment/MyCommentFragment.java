package com.simson.www.ui.mine.post.comment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.ui.adapter.DiaryAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.core.presenter.BasePresenter;

import butterknife.BindView;


public class MyCommentFragment extends BasePresenterFragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private DiaryAdapter mAdapter;

    @Override
    protected void getBundle(Bundle bundle) {

    }

    @Override
    protected void initViews(View view) {
        setRefresh();
        mAdapter = new DiaryAdapter(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View views, int position) -> {

        });


    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            //mCurrentPage = 1;
            //mPresenter.getDiaryData(mCustomerId, mCurrentPage);
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            //mCurrentPage++;
            //mPresenter.getDiaryData(mCustomerId, mCurrentPage);
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_comment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
