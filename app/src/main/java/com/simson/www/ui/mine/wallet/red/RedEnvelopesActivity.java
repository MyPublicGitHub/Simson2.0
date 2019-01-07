package com.simson.www.ui.mine.wallet.red;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.mine.RedEnvelopesBean;
import com.simson.www.ui.adapter.RedEnvelopesAdapter;
import com.simson.www.ui.base.BasePresenterActivity;

import java.util.List;

import butterknife.BindView;

public class RedEnvelopesActivity extends BasePresenterActivity<RedEnvelopesPresenter, RedEnvelopesContract.View>
        implements RedEnvelopesContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private int mPage = 1;
    private RedEnvelopesAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_red_envelopes;
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RedEnvelopesAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        mRefreshLayout.setEnableLoadMore(false);
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
    public void showFail(String msg) {

    }

    @Override
    protected void initData() {
        mPresenter.redEnvelopesRecord();
    }

    @Override
    protected RedEnvelopesPresenter createPresenter() {
        return new RedEnvelopesPresenter();
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.redEnvelopesRecord();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("红包记录");
        return true;
    }


    @Override
    public void redEnvelopesRecord(List<RedEnvelopesBean> bean) {
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
