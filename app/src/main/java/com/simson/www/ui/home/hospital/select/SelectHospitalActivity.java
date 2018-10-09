package com.simson.www.ui.home.hospital.select;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.ui.adapter.HospitalItemAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.home.hospital.detail.HospitalDetailActivity;
import com.simson.www.ui.home.hospital.item.HospitalItemContract;
import com.simson.www.ui.home.hospital.item.HospitalItemPresenter;

import java.util.List;

import butterknife.BindView;

public class SelectHospitalActivity extends BasePresenterActivity<HospitalItemPresenter, HospitalItemContract.View>
        implements HospitalItemContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_hospital;
    }

    HospitalItemAdapter adapter;

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HospitalItemAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<HospitalItemBean> bean = (List<HospitalItemBean>) adapter.getData();
            String hospitalId = bean.get(position).getHospital_id();
            String hospitalName = bean.get(position).getHospital_name();
            setResult(1001,new Intent()
                    .putExtra("hospitalId", hospitalId)
                    .putExtra("hospitalName",hospitalName));
            finish();
        });
        setRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.getHospital();

    }

    @Override
    protected HospitalItemPresenter createPresenter() {
        return new HospitalItemPresenter();
    }

    private int mPage = 1;

    @Override
    public String getPage() {
        return mPage + "";
    }

    @Override
    public String getCityId() {
        return "";
    }

    @Override
    public void showHospital(List<HospitalItemBean> bean) {
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
            mPresenter.getHospital();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getHospital();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("选择医院");
        return true;
    }
}
