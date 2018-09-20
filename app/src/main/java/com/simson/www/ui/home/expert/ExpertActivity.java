package com.simson.www.ui.home.expert;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.adapter.ExpertAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.home.expert.detail.ExpertDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ExpertActivity extends BasePresenterActivity {
    ExpertAdapter adapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_expert;
    }

    @Override
    protected void initViews() {
        List<BaseBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new BaseBean());
        }
        adapter = new ExpertAdapter(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setSmoothScrollbarEnabled(true);
//        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(ExpertActivity.this, ExpertDetailActivity.class));
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("专家");
        return true;
    }
}
