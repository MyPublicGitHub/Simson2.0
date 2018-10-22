package com.simson.www.ui.home.cases.item;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.community.PopularizationBean;
import com.simson.www.net.bean.mine.CaseBean;
import com.simson.www.ui.adapter.CaseItemAdapter;
import com.simson.www.ui.adapter.HomeItemAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;
import com.simson.www.ui.home.cases.CaseContract;
import com.simson.www.ui.home.cases.CasePresenter;

import java.util.List;

import butterknife.BindView;

public class CaseItemFragment extends BasePresenterFragment<CaseItemPresenter, CaseItemContract.View>
        implements CaseItemContract.View {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    @Override
    protected void getBundle(Bundle bundle) {
        mItemType = getArguments().getString("itemType");
    }

    public static CaseItemFragment newInstance(String itemType) {
        Bundle args = new Bundle();
        args.putString("itemType", itemType);
        CaseItemFragment fragment = new CaseItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_case_item;
    }
    CaseItemAdapter adapter;

    @Override
    protected void initViews(android.view.View view) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter = new CaseItemAdapter(null);
        recyclerView.setAdapter(adapter);
//        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<CaseBean> bean = (List<CaseBean>) adapter.getData();
            String popularizationLink = bean.get(position).getLink_url();
            String popularizationId = bean.get(position).getCase_id();
            String url = popularizationLink + "?json={caseId:" + popularizationId + "}";
            startActivity(new Intent(getContext(), WebViewActivity.class)
                    .putExtra(Const.WEB_VIEW_TITLE, "案例详情")
                    .putExtra(Const.WEB_VIEW_URL,url));
        });
        setRefresh();
        mPage = 1;
        mPresenter.getCase();
    }

    @Override
    public void showCase(List<CaseBean> bean) {
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


    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.getCase();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getCase();
            refreshLayout.finishLoadMore();
        });
    }


    private int mPage = 1;
    private String mItemType = "", mHospitalId = "";

    @Override
    public String getPage() {
        return mPage + "";
    }

    @Override
    public String getHospitalId() {
        return mHospitalId;
    }

    @Override
    public String getItemTypeId() {
        return mItemType;
    }

    @Override
    protected CaseItemPresenter createPresenter() {
        return new CaseItemPresenter();
    }
}
