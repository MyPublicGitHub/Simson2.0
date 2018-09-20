package com.simson.www.ui.hospital;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.adapter.HospitalFragmentAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.shop.detail.CommodityDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HospitalFragment extends BasePresenterFragment {

    HospitalFragmentAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void getBundle(Bundle bundle) {

    }

    @Override
    protected void initViews(View view) {
        List<BaseBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new BaseBean());
        }
        adapter = new HospitalFragmentAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        adapter.setOnItemClickListener((adapter, view1, position) ->
                startActivity(new Intent(getActivity(),CommodityDetailActivity.class))
        );
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hospital;
    }

}
