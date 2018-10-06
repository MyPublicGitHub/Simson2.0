package com.simson.www.ui.mine.order.item;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.ui.adapter.OrderAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.community.diary.item.DiaryItemFragment;

import java.util.List;

import butterknife.BindView;

public class OrderFragment extends BasePresenterFragment <OrderPresenter,OrderContract.View>
        implements OrderContract.View{
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
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<OrderBean> bean = (List<OrderBean>) adapter.getData();
            //String diary_id = bean.get(position).getDiary_id();
            //startActivity(new Intent(this, AddressDetailActivity.class).putExtra("id", diary_id));
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
        return mPage+"";
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
