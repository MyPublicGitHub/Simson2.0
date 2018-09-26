package com.simson.www.ui.mine.diary;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.ui.adapter.DiaryAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.diary.detail.DiaryDetailActivity;
import com.simson.www.ui.community.diary.item.DiaryItemContract;
import com.simson.www.ui.community.diary.item.DiaryItemPresenter;
import com.simson.www.ui.main.login.LoginActivity;
import com.simson.www.ui.main.register.RegisterActivity;
import com.simson.www.ui.mine.diary.add.NewDiaryActivity;

import java.util.List;

import butterknife.BindView;

public class MyDiaryActivity extends BasePresenterActivity<DiaryItemPresenter, DiaryItemContract.View> implements DiaryItemContract.View {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private String type = "";
    private int mPage = 1;
    private DiaryAdapter adapter;

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DiaryAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<DiaryBean> bean = (List<DiaryBean>) adapter.getData();
            String diary_id = bean.get(position).getDiary_id();
            startActivity(new Intent(this, DiaryDetailActivity.class).putExtra("id", diary_id));
        });
        setRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.getMyDiaryList();
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
        return R.layout.activity_my_diary;
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.getMyDiaryList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getMyDiaryList();
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
    protected boolean initToolbar() {
        mTitle.setText("我的日记");
        mToolbar.inflateMenu(R.menu.toolbar_menu_diary);
        mToolbar.setOnMenuItemClickListener(item -> {
            startActivity(new Intent(this, NewDiaryActivity.class));
            return false;
        });
        return true;
    }
    public String getType() {
        return type;
    }

    @Override
    public void goToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

}
