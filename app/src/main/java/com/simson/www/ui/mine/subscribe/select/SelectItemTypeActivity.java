package com.simson.www.ui.mine.subscribe.select;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.simson.www.R;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.ui.adapter.SelectItemTypeAdapter;
import com.simson.www.ui.base.BasePresenterActivity;

import java.util.List;

import butterknife.BindView;

public class SelectItemTypeActivity extends BasePresenterActivity<SelectItemTypePresenter, SelectItemTypeContract.View>
        implements SelectItemTypeContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_item_type;
    }

    SelectItemTypeAdapter adapter;

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelectItemTypeAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<ItemTypeBean> bean = (List<ItemTypeBean>) adapter.getData();
            String id = bean.get(position).getItemTypeId();
            String itemTypeName = bean.get(position).getTypeName();
            setResult(1002, new Intent().putExtra("itemTypeId", id).putExtra("itemTypeName",itemTypeName));
            finish();
        });
    }

    @Override
    protected void initData() {
        mPresenter.getItemType();
    }

    @Override
    public void setItemType(List<ItemTypeBean> bean) {
        if (bean == null) {
            return;
        }
        adapter.replaceData(bean);
    }

    @Override
    protected SelectItemTypePresenter createPresenter() {
        return new SelectItemTypePresenter();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("请选择预约的项目");
        return true;
    }
}