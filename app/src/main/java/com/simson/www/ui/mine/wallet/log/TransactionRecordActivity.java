package com.simson.www.ui.mine.wallet.log;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.mine.TransactionRecordBean;
import com.simson.www.ui.adapter.TransactionRecordAdapter;
import com.simson.www.ui.base.BasePresenterActivity;

import java.util.List;

import butterknife.BindView;

public class TransactionRecordActivity extends BasePresenterActivity<TransactionRecordPresenter, TransactionRecordContract.View>
        implements TransactionRecordContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private int mPage = 1;
    private TransactionRecordAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transaction_record;
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransactionRecordAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
//        adapter.setOnItemClickListener((adapter, view1, position) -> {
//                    List<ShopListBean> data = (List<ShopListBean>) adapter.getData();
//                    ShopListBean shopListBean = data.get(position);
//                    startActivity(new Intent(this, CommodityDetailActivity.class)
//                            .putExtra("id", shopListBean.getItem_id()));
//                }
//        );
        setRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.rechargeOrderList();
    }

    @Override
    public String pageIndex() {
        return mPage + "";
    }

    @Override
    protected TransactionRecordPresenter createPresenter() {
        return new TransactionRecordPresenter();
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.rechargeOrderList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.rechargeOrderList();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("交易记录");
        return true;
    }


    @Override
    public void rechargeOrderList(List<TransactionRecordBean> bean) {
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
}
