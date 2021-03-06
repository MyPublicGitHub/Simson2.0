package com.simson.www.ui.community.knowledge.item;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.bean.community.PopularizationBean;
import com.simson.www.net.bean.home.IndexSynchysisBean;
import com.simson.www.ui.adapter.KnowledgeItemAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;
import com.simson.www.ui.main.login.LoginActivity;

import java.util.List;

import butterknife.BindView;


public class KnowledgeItemFragment extends BasePresenterFragment<KnowledgeItemPresenter, KnowledgeItemContract.View>
        implements KnowledgeItemContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private String type;
    private int mPage = 1;
    private KnowledgeItemAdapter adapter;

    @Override
    protected void initViews(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new KnowledgeItemAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<PopularizationBean> bean = (List<PopularizationBean>) adapter.getData();
            startActivity(new Intent(getContext(), WebViewActivity.class)
                    .putExtra(Const.WEB_VIEW_TITLE, "科普详情")
                    .putExtra(Const.WEB_VIEW_URL,bean.get(position).getLink_url()));
        });
        adapter.setOnItemChildClickListener((adapter, views, position) -> {
            mPosition = position;
            PopularizationBean bean = (PopularizationBean) adapter.getData().get(position);
            String mFollowMethod = bean.getIs_follow() == 0 ? "save" : "delete";
            switch (views.getId()) {
                case R.id.tv_follow:
                    mPresenter.follow(bean.getHospital_id(), mFollowMethod, Const.FOLLOW_TYPE.HOSPITAL);
                    break;
            }
        });
        setRefresh();
        //?json={"timestamp":"2018-09-26 01:38:17","popularizationId":"123123"}
        mPresenter.getPopularizationList();
    }
    int mPosition;
    @Override
    public void follow(BaseBean bean) {
        List<PopularizationBean> data = adapter.getData();
        PopularizationBean beans = data.get(mPosition);
        beans.setIs_follow(beans.getIs_follow() == 0 ? 1 : 0);
        adapter.notifyItemChanged(mPosition,beans);
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_diary_item;
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
    public int getPage() {
        return mPage;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String itemTypeId() {
        return null;
    }

    @Override
    public String search() {
        return null;
    }
    @Override
    protected KnowledgeItemPresenter createPresenter() {
        return new KnowledgeItemPresenter();
    }


    @Override
    protected void getBundle(Bundle bundle) {
        int position = bundle.getInt("position", 0);
        //type：1推荐，2关注，空不传是全部
        if (position == 1) {
            type = "2";
        } else if (position == 2) {
            type = "";
        } else {
            type = "1";
        }
    }

    public static KnowledgeItemFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        KnowledgeItemFragment fragment = new KnowledgeItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
