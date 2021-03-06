package com.simson.www.ui.home.item;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.ui.adapter.HomeItemAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.community.diary.detail.DiaryDetailActivity;

import java.util.List;

import butterknife.BindView;


public class HomeItemFragment extends BasePresenterFragment<HomeItemPresenter, HomeItemContract.View> implements HomeItemContract.View {


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
    int mPosition;

    @Override
    protected void initViews(View view) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeItemAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemChildClickListener((adapter, views, position) -> {
            mPosition = position;
            List<DiaryBean> data = (List<DiaryBean>) adapter.getData();
            DiaryBean bean = data.get(position);
            mFollowMethod = bean.getIs_follow() == 0 ? "save" : "delete";
            mFollowCustomerId = bean.getCustomer_id();
            switch (views.getId()) {
                case R.id.tv_follow:
                    mPresenter.follow();
                    break;
            }
        });
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<DiaryBean> bean = (List<DiaryBean>) adapter.getData();
            String diary_id = bean.get(position).getDiary_id();
            startActivity(new Intent(getContext(), DiaryDetailActivity.class).putExtra("id", diary_id));
        });
        setRefresh();
        mPage = 1;

        mPresenter.getHomeItemData();
    }

    @Override
    public void follow(BaseBean bean) {
        List<DiaryBean> data = adapter.getData();
        DiaryBean beans = data.get(mPosition);
        beans.setIs_follow(beans.getIs_follow() == 0 ? 1 : 0);
        adapter.notifyItemChanged(mPosition,beans);
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
    public void setHomeItemData(List<DiaryBean> bean) {
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
    private String mItemType, mType = "", mFollowCustomerId, mFollowMethod;

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

    @Override
    public String getFollowCustomerId() {
        return mFollowCustomerId;
    }

    @Override
    public String getFollowMethod() {
        return mFollowMethod;
    }
}
