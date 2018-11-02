package com.simson.www.ui.mine.order.item;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.ui.adapter.OrderAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.mine.pay.PayActivity;
import com.simson.www.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderFragment extends BasePresenterFragment<OrderPresenter, OrderContract.View>
        implements OrderContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private OrderAdapter adapter;
    int mPage = 1;
    String mStatus;//status：1待支付；2已支付；空全部

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }


    @Override
    protected void getBundle(Bundle bundle) {
        mStatus = bundle.getString("status");
    }

    @Override
    protected void initViews(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new OrderAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemChildClickListener((adapter, view1, position) -> {
            OrderBean bean = (OrderBean) adapter.getData().get(position);
            switch (view1.getId()) {
                case R.id.tv_payment://支付
                    if (bean.getOrder_id() != null) {
                        ArrayList<CommodityDetailBean> list = new ArrayList<>();
                        for (int i = 0; i < bean.getItems().size(); i++) {
                            OrderBean.ItemsBean itemsBean = bean.getItems().get(i);
                            CommodityDetailBean beans = new CommodityDetailBean();
                            ArrayList icon = new ArrayList<>();
                            icon.add(itemsBean.getItem_icon());
                            beans.setPicture(icon);
                            beans.setItem_name(itemsBean.getItem_name());
                            beans.setIs_point(itemsBean.getIs_point());
                            beans.buyNumber = itemsBean.getBuy_num() + "";
                            //beans.unityPrice = itemsBean.;
                            list.add(beans);
                        }
                        Intent intent = new Intent(getContext(), PayActivity.class);
                        intent.putExtra("transactionMoney", bean.getTransaction_money());
                        intent.putExtra("transactionPoint", bean.getTransaction_point());
                        intent.putExtra("isPoint", bean.getItems().get(0).getIs_point() == 1 ? true : false);
                        intent.putParcelableArrayListExtra("CommodityDetailBean", list);
                        intent.putExtra("orderId", bean.getOrder_id());
                        startActivity(intent);
                    }
                    break;
                case R.id.tv_logistics://使用
                    LogUtils.e("-----------------使用-------------------");
                    break;
                case R.id.tv_evaluate://评价
                    LogUtils.e("-----------------评价-------------------");
                    break;
            }

        });
        setRefresh();
        mPresenter.getOrder();
    }

    @Override
    public void showOrder(List<OrderBean> bean) {
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
    protected OrderPresenter createPresenter() {
        return new OrderPresenter();
    }

    @Override
    public String getPage() {
        return mPage + "";
    }

    @Override
    public String getStatus() {
        return mStatus;
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.getOrder();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getOrder();
            refreshLayout.finishLoadMore();
        });
    }

    public static OrderFragment newInstance(String status) {
        Bundle args = new Bundle();
        args.putString("status", status);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
