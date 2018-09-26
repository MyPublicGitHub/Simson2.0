package com.simson.www.ui.mine.cart;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.net.bean.mine.ShopCartBean;
import com.simson.www.ui.adapter.ShopCartAdapter;
import com.simson.www.ui.base.BasePresenterActivity;

import java.util.List;

import butterknife.BindView;

public class ShopCartActivity extends BasePresenterActivity<ShopCartPresenter, ShopCartContract.View>
        implements ShopCartContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ShopCartAdapter adapter;
    int mPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_cart;
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShopCartAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<ShopCartBean> bean = (List<ShopCartBean>) adapter.getData();
            //String diary_id = bean.get(position).getDiary_id();
            //startActivity(new Intent(this, AddressDetailActivity.class).putExtra("id", diary_id));
        });
        setRefresh();
        mPresenter.getShopCart();
    }

    @Override
    public void showShopCart(List<ShopCartBean> bean) {
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
    public int getPage() {
        return mPage;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("购物车");
        return true;
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.getShopCart();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getShopCart();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    protected ShopCartPresenter createPresenter() {
        return new ShopCartPresenter();
    }

}
