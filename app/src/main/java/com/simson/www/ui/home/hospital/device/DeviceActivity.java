package com.simson.www.ui.home.hospital.device;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.home.HospitalDeviceBean;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.ui.adapter.DeviceAdapter;
import com.simson.www.ui.adapter.ShopCommodityAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;
import com.simson.www.ui.shop.detail.CommodityDetailActivity;

import java.util.List;

import butterknife.BindView;

public class DeviceActivity extends BasePresenterActivity<DevicePresenter, DeviceContract.View>
        implements DeviceContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private int mPage = 1;
    private DeviceAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device;
    }
    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DeviceAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<HospitalDeviceBean> bean = (List<HospitalDeviceBean>) adapter.getData();
            String link = bean.get(position).getDevice_link();
            String id = bean.get(position).getDevice_id();
            String url = link + "?json={deviceId:" + id + "}";
            startActivity(new Intent(this, WebViewActivity.class)
                    .putExtra(Const.WEB_VIEW_TITLE, bean.get(position).getDevice_name()+"")
                    .putExtra(Const.WEB_VIEW_URL, url));
        });
        setRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.getHospitalDeviceList();
    }

    @Override
    protected DevicePresenter createPresenter() {
        return new DevicePresenter();
    }


    @Override
    public String pageIndex() {
        return mPage + "";
    }

    @Override
    public void getHospitalDeviceList(List<HospitalDeviceBean> bean) {
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
            mPresenter.getHospitalDeviceList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getHospitalDeviceList();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("植发仪器");
        return true;
    }
}
