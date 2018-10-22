package com.simson.www.ui.community.circle;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.bean.community.FriendsCircleBean;
import com.simson.www.ui.adapter.FriendCircleAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.community.circle.detail.FriendCircleDetailActivity;
import com.simson.www.ui.community.diary.detail.DiaryDetailActivity;

import java.util.List;

import butterknife.BindView;

public class FriendCircleFragment extends BasePresenterFragment<FriendCirclePresenter,FriendCircleContract.View>
implements FriendCircleContract.View{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private int mPage = 1;
    private FriendCircleAdapter adapter;

    @Override
    protected void initViews(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FriendCircleAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<FriendsCircleBean> bean = (List<FriendsCircleBean>) adapter.getData();
            String id = bean.get(position).getFriends_circle_id();
            startActivity(new Intent(getContext(), FriendCircleDetailActivity.class).putExtra("id", id));
        });
        setRefresh();
        mPresenter.friendsCircleList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friend_circle;
    }

    @Override
    protected FriendCirclePresenter createPresenter() {
        return new FriendCirclePresenter();
    }

    @Override
    protected void getBundle(Bundle bundle) {

    }

    @Override
    public int getPage() {
        return mPage;
    }

    @Override
    public void friendsCircleList(List<FriendsCircleBean> bean) {
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
            mPresenter.friendsCircleList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.friendsCircleList();
            refreshLayout.finishLoadMore();
        });
    }
}
