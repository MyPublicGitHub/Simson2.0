package com.simson.www.ui.community.knowledge.type;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.community.PopularizationBean;
import com.simson.www.ui.adapter.KnowledgeItemAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;
import com.simson.www.ui.community.knowledge.item.KnowledgeItemContract;
import com.simson.www.ui.community.knowledge.item.KnowledgeItemPresenter;

import java.util.List;

import butterknife.BindView;

public class KnowledgeTypeActivity extends BasePresenterActivity<KnowledgeItemPresenter, KnowledgeItemContract.View>
        implements KnowledgeItemContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private int mPage = 1;
    private KnowledgeItemAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_konwledge_type;
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new KnowledgeItemAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<PopularizationBean> bean = (List<PopularizationBean>) adapter.getData();
            String popularizationLink = bean.get(position).getLink_url();
            String popularizationId = bean.get(position).getPopularization_id();
            String url = popularizationLink + "?json={popularizationId:" + popularizationId + "}";
            startActivity(new Intent(this, WebViewActivity.class)
                    .putExtra(Const.WEB_VIEW_TITLE, "科普详情")
                    .putExtra(Const.WEB_VIEW_URL, url));
        });
        setRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.getPopularizationList();
    }

    @Override
    protected KnowledgeItemPresenter createPresenter() {
        return new KnowledgeItemPresenter();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("科普");
        return true;
    }

    @Override
    public int getPage() {
        return mPage;
    }

    @Override
    public String getType() {
        return "";
    }

    String itemTypeId;

    @Override
    public String itemTypeId() {
        return itemTypeId;
    }

    @Override
    public String search() {
        return "";
    }

    @Override
    public void showPopularizationList(List<PopularizationBean> bean) {
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
            mPresenter.getPopularizationList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getPopularizationList();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    protected void getIntent(Intent intent) {
        itemTypeId = intent.getStringExtra("itemTypeId");
    }

}
