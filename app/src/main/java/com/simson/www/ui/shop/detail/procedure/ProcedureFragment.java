package com.simson.www.ui.shop.detail.procedure;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.simson.www.R;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.ui.adapter.DetailAdapter;
import com.simson.www.ui.base.BasePresenterFragment;

import butterknife.BindView;

public class ProcedureFragment extends BasePresenterFragment<ProcedurePresenter, ProcedureContract.View>
        implements ProcedureContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

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
        mPresenter.getCommoditySubscribeProcess();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_procedure;
    }

    @Override
    protected ProcedurePresenter createPresenter() {
        return new ProcedurePresenter();
    }

    @Override
    public String getItemId() {
        return itemId;
    }


    String itemId;

    public static ProcedureFragment newInstance(String itemId) {
        Bundle args = new Bundle();
        args.putString("itemId", itemId);
        ProcedureFragment fragment = new ProcedureFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showCommoditySubscribeProcess(CommodityDetailBean bean) {
        if (bean == null) {
            return;
        }
        //adapter.replaceData(bean.pictures);
    }
}
