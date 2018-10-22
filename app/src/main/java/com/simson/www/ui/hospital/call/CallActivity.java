package com.simson.www.ui.hospital.call;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.ui.adapter.CallAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.home.hospital.item.HospitalItemContract;
import com.simson.www.ui.home.hospital.item.HospitalItemPresenter;
import com.simson.www.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;

import static com.simson.www.application.AppContext.getContext;

public class CallActivity extends BasePresenterActivity<HospitalItemPresenter, HospitalItemContract.View>
        implements HospitalItemContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    String mCityId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_call;
    }

    CallAdapter adapter;

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CallAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        /*adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<HospitalItemBean> bean = (List<HospitalItemBean>) adapter.getData();
            String hospitalId = bean.get(position).getHospital_id();
            startActivity(new Intent(getContext(), HospitalDetailActivity.class)
                    .putExtra("hospitalId", hospitalId));
        });*/
        adapter.setOnItemChildClickListener((adapter, views, position) -> {
            HospitalItemBean bean = (HospitalItemBean) adapter.getData().get(position);
            switch (views.getId()) {
                case R.id.tv_reserve:
                    CommonUtils.callPhone(this, bean.getConsulting_phone());
                    break;
            }
        });
        setRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.getHospital();
    }

    private int mPage = 1;

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

    @Override
    protected HospitalItemPresenter createPresenter() {
        return new HospitalItemPresenter();
    }

    @Override
    public String getPage() {
        return mPage + "";
    }

    @Override
    public String getCityId() {
        return mCityId;
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
        mTitle.setText("电话咨询");
        return true;
    }
}
