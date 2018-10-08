package com.simson.www.ui.mine.set.address;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.ui.adapter.AddressAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.mine.set.address.add.NewAddressActivity;
import com.simson.www.ui.mine.set.address.detail.AddressDetailActivity;
import com.simson.www.ui.mine.set.address.edit.EditAddressActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressActivity extends BasePresenterActivity<AddressPresenter, AddressContract.View> implements AddressContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private AddressAdapter adapter;
    int mPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddressAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
//        adapter.setOnItemClickListener((adapter, view, position) -> {
//            List<AddressBean> data = (List<AddressBean>) adapter.getData();
//            AddressBean dataBean = data.get(position);
//            startActivity(new Intent(AddressActivity.this, AddressDetailActivity.class)
//                    .putExtra("deliveryId", dataBean.getDelivery_id()));
//        });
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            List<AddressBean> data = (List<AddressBean>) adapter.getData();
            AddressBean dataBean = data.get(position);
            switch (view.getId()) {
                case R.id.tv_edit:
                    Intent intent = new Intent(AddressActivity.this, EditAddressActivity.class);
                    intent.putExtra("data", dataBean);
                    startActivityForResult(intent, 1);
                    break;
                case R.id.tv_delete:
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddressActivity.this);
                    builder.setMessage("删除地址");
                    builder.setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                            .setNeutralButton("确定", (dialog, which) -> {
                                //mPresenter.deleteAddressData(dataBean.getId());
                                dialog.dismiss();
                            }).show();
                    break;
            }
        });
        setRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.getAddress();
    }

    @Override
    public int getPage() {
        return mPage;
    }

    @Override
    public void showAddress(List<AddressBean> bean) {
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

    @OnClick({R.id.tv_new_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_new_address:
                startActivity(new Intent(this, NewAddressActivity.class));
                break;
        }
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("收货地址");
        return true;
    }

    @Override
    protected AddressPresenter createPresenter() {
        return new AddressPresenter();
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.getAddress();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getAddress();
            refreshLayout.finishLoadMore();
        });
    }
}
