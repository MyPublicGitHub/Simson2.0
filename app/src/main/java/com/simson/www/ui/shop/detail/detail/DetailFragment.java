package com.simson.www.ui.shop.detail.detail;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.simson.www.R;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.ui.adapter.DetailAdapter;
import com.simson.www.ui.base.BasePresenterFragment;

import butterknife.BindView;

public class DetailFragment extends BasePresenterFragment<DetailPresenter, DetailContract.View>
        implements DetailContract.View {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail;
    }

    @Override
    protected void getBundle(Bundle bundle) {
        itemId = bundle.getString("itemId");
    }

    DetailAdapter adapter;

    @Override
    protected void initViews(View view) {
        adapter = new DetailAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        mPresenter.getCommodityDetailPicture();
    }

    @Override
    public void showCommodityDetailPicture(CommodityDetailBean bean) {
        if (bean == null) {
            return;
        }
        adapter.replaceData(bean.pictures);
    }

    @Override
    protected DetailPresenter createPresenter() {
        return new DetailPresenter();
    }

    @Override
    public String getItemId() {
        return itemId;
    }


    String itemId;

    public static DetailFragment newInstance(String itemId) {
        Bundle args = new Bundle();
        args.putString("itemId", itemId);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
