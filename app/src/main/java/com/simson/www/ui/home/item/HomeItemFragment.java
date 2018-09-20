package com.simson.www.ui.home.item;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.home.HomeItemBean;
import com.simson.www.ui.adapter.HomeItemAdapter;
import com.simson.www.ui.base.BasePresenterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeItemFragment extends BasePresenterFragment<HomeItemPresenter, HomeItemContract.IHomeItemView> implements HomeItemContract.IHomeItemView {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    @Override
    protected void getBundle(Bundle bundle) {
        mItemType = getArguments().getString("itemType");
    }

    public static HomeItemFragment newInstance(String itemType) {
        Bundle args = new Bundle();
        args.putString("itemType", itemType);
        HomeItemFragment fragment = new HomeItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    HomeItemAdapter adapter;

    @Override
    protected void initViews(View view) {
        //        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),
//                DividerItemDecoration.VERTICAL);
//        itemDecoration.setDrawable(ContextCompat.getDrawable(getContext(),
//                R.drawable.recycler_divider));
//        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeItemAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        setRefresh();
        mPage = 1;

        mPresenter.getHomeItemData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_item;
    }

    @Override
    protected HomeItemPresenter createPresenter() {
        return new HomeItemPresenter();
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.getHomeItemData();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getHomeItemData();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    public void setHomeItemData(List<HomeItemBean> bean) {
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

    private int mPage = 1;
    private String mItemType,mType = "";

    @Override
    public int getPage() {
        return mPage;
    }

    @Override
    public String getType() {
        return mType;
    }

    @Override
    public String getItemType() {
        return mItemType;
    }
}
