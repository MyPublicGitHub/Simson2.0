package com.simson.www.ui.home.expert;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.community.DoctorBean;
import com.simson.www.ui.adapter.ExpertAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.expert.ExpertContract;
import com.simson.www.ui.community.expert.ExpertPresenter;
import com.simson.www.ui.home.expert.detail.ExpertDetailActivity;

import java.util.List;

import butterknife.BindView;

public class ExpertActivity extends BasePresenterActivity<ExpertPresenter, ExpertContract.View>
        implements ExpertContract.View {
    ExpertAdapter adapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private int mPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_expert;
    }

    @Override
    protected void initViews() {
        adapter = new ExpertAdapter(null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        setRefresh();
        adapter.setOnItemClickListener((adapter, view, position) -> {
            DoctorBean.DoctorItemBean bean = (DoctorBean.DoctorItemBean)adapter.getData().get(position);
            startActivity(new Intent(ExpertActivity.this, ExpertDetailActivity.class).putExtra("doctorId",bean.getDoctor_id()));
        });
    }

    @Override
    protected void initData() {
        mPresenter.getDoctorList();
    }

    @Override
    public void showDoctorList(DoctorBean beans) {
        if (beans == null && beans.getList() != null) {
            return;
        }
        List<DoctorBean.DoctorItemBean> bean = beans.getList();
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
            mPresenter.getDoctorList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getDoctorList();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    protected ExpertPresenter createPresenter() {
        return new ExpertPresenter();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("专家");
        return true;
    }

    @Override
    public int getPage() {
        return mPage;
    }

}
