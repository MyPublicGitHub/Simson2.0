package com.simson.www.ui.community.diary.item;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.ui.adapter.DiaryAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.community.diary.detail.DiaryDetailActivity;
import com.simson.www.ui.main.login.LoginActivity;

import java.util.List;

import butterknife.BindView;


public class DiaryItemFragment extends BasePresenterFragment<DiaryItemPresenter, DiaryItemContract.View> implements DiaryItemContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private String type;
    private int mPage = 1;
    private DiaryAdapter adapter;

    @Override
    protected void initViews(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DiaryAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<DiaryBean> bean = (List<DiaryBean>) adapter.getData();
            String diary_id = bean.get(position).getDiary_id();
            startActivity(new Intent(getContext(), DiaryDetailActivity.class).putExtra("id", diary_id));
        });
        setRefresh();
        mPresenter.getDiaryList();
    }

    @Override
    public void showDiaryList(List<DiaryBean> bean) {
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
    protected int getLayoutId() {
        return R.layout.fragment_diary_item;
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.getDiaryList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getDiaryList();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    protected DiaryItemPresenter createPresenter() {
        return new DiaryItemPresenter();
    }

    @Override
    public int getPage() {
        return mPage;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void goToLogin() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    @Override
    protected void getBundle(Bundle bundle) {
        int position = bundle.getInt("position", 0);
        //type：1推荐，2关注，空不传是全部
        if (position == 1) {
            type = "2";
        } else if (position == 2) {
            type = "";
        } else {
            type = "1";
        }
    }

    public static DiaryItemFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        DiaryItemFragment fragment = new DiaryItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
