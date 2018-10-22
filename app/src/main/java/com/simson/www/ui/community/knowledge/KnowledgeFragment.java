package com.simson.www.ui.community.knowledge;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.simson.www.R;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.ui.adapter.ItemTypeGradeAdapter;
import com.simson.www.ui.adapter.TabViewPagerAdapter;
import com.simson.www.ui.adapter.TabViewPagerAdapterItem;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.community.knowledge.type.KnowledgeTypeActivity;
import com.simson.www.ui.mine.subscribe.select.SelectItemTypeContract;
import com.simson.www.ui.mine.subscribe.select.SelectItemTypePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class KnowledgeFragment extends BasePresenterFragment<SelectItemTypePresenter, SelectItemTypeContract.View>
        implements SelectItemTypeContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    ItemTypeGradeAdapter adapter;

    @Override
    protected void initViews(View view) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),5));
        adapter = new ItemTypeGradeAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        //adapter.bindToRecyclerView(recyclerView);
        //adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<ItemTypeBean> bean = (List<ItemTypeBean>) adapter.getData();
            String id = bean.get(position).getItemTypeId();
            startActivity(new Intent(getActivity(),KnowledgeTypeActivity.class).putExtra("itemTypeId",id));
        });
        ArrayList<String> titleData = new ArrayList<>();
        titleData.add("推荐");
        titleData.add("关注");
        titleData.add("全部");
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager(),
                TabViewPagerAdapterItem.createKnowledgeItemFragments(titleData));
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
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
    protected void getBundle(Bundle bundle) {

    }
}
