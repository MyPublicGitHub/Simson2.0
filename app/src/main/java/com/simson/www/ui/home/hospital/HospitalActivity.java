package com.simson.www.ui.home.hospital;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.ui.adapter.HospitalActivityAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.home.hospital.detail.HospitalDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HospitalActivity extends BasePresenterActivity<HospitalActivityPresenter, HospitalActivityContract.View> implements HospitalActivityContract.View {
    HospitalActivityAdapter adapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hospital;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("新生医院");
        return true;
    }

    @Override
    protected void initViews() {
        setRefresh();
        List<BaseBean> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new BaseBean());
        }
        adapter = new HospitalActivityAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(HospitalActivity.this, HospitalDetailActivity.class));
            }
        });
    }

    @Override
    protected HospitalActivityPresenter createPresenter() {
        return new HospitalActivityPresenter();
    }

    @Override
    public void setBannerData(HomeBannerBean bean) {

    }

    private int mPage = 1;

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            //mPresenter.getHomeItemData();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            //mPresenter.getHomeItemData();
            refreshLayout.finishLoadMore();
        });
    }
}
