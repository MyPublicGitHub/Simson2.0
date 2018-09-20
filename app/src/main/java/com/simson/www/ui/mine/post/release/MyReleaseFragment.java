package com.simson.www.ui.mine.post.release;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.adapter.MyDiaryAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.core.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MyReleaseFragment extends BasePresenterFragment {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private MyDiaryAdapter mAdapter;

    @Override
    protected void getBundle(Bundle bundle) {

    }

    @Override
    protected void initViews(View views) {
        setRefresh();
        mAdapter = new MyDiaryAdapter(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View view, int position) -> {

        });

        List<BaseBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new BaseBean());
        }
        mAdapter.replaceData(list);
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
        return R.layout.fragment_my_release;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
