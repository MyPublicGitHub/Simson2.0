package com.simson.www.ui.mine.integral.mall;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.simson.www.R;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.ui.adapter.IntegralMallAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.mine.integral.IntegralActivity;
import com.simson.www.ui.shop.ShopContract;
import com.simson.www.ui.shop.ShopPresenter;
import com.simson.www.ui.shop.detail.CommodityDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class IntegralMallActivity extends BasePresenterActivity<ShopPresenter, ShopContract.View> implements ShopContract.View {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    IntegralMallAdapter adapter;
    @BindView(R.id.ll_my_integral)
    LinearLayout llMyIntegral;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral_mall;
    }

    @Override
    protected void initViews() {
        adapter = new IntegralMallAdapter(null);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<ShopListBean> data = (List<ShopListBean>) adapter.getData();
            ShopListBean shopListBean = data.get(position);
            startActivity(new Intent(this, CommodityDetailActivity.class).putExtra("id", shopListBean.getItem_id()));
        });
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("积分商城");
        return true;
    }


    @OnClick({R.id.ll_my_integral})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_integral:
                startActivity(new Intent(this, IntegralActivity.class));
                break;
        }
    }

    @Override
    protected void initData() {
        mPresenter.getShopList(1);
    }

    @Override
    protected ShopPresenter createPresenter() {
        return new ShopPresenter();
    }

    @Override
    protected void getIntent(Intent intent) {
        itemTypeId = intent.getStringExtra("itemTypeId");
    }

    String itemTypeId, search;

    @Override
    public String itemTypeId() {
        return itemTypeId;
    }

    @Override
    public String search() {
        return search;
    }

    @Override
    public int getPageCommodity() {
        return mPageCommodity;
    }

    @Override
    public int getPageIntegral() {
        return 0;
    }

    int mPageCommodity = 1;

    @Override
    public void setShopListResponse(List<ShopListBean> bean) {

    }

    @Override
    public void setShopIntegralListResponse(List<ShopListBean> bean) {
        if (bean == null) {
            return;
        }
        adapter.replaceData(bean);
    }

}
