package com.simson.www.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.net.bean.home.IndexSynchysisBean;
import com.simson.www.ui.adapter.HomeAdapter;
import com.simson.www.ui.adapter.HomeHeaderAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.community.circle.detail.FriendCircleDetailActivity;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;
import com.simson.www.ui.home.cases.CaseActivity;
import com.simson.www.ui.home.cause.CauseActivity;
import com.simson.www.ui.home.expert.ExpertActivity;
import com.simson.www.ui.home.hair.BeautifulHairActivity;
import com.simson.www.ui.home.hospital.detail.HospitalDetailActivity;
import com.simson.www.ui.home.test.TestSplashActivity;
import com.simson.www.ui.mine.subscribe.save.NewSubscribeActivity;
import com.simson.www.ui.mine.test.save.NewHospitalTestActivity;
import com.simson.www.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class HomeFragment extends BasePresenterFragment<HomePresenter, HomeContract.View> implements HomeContract.View {

    @BindView(R.id.recycler_view_header)
    RecyclerView recyclerViewHeader;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    HomeHeaderAdapter homeHeaderAdapter;
    HomeAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View view) {
        setRefresh();
        recyclerViewHeader.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        homeHeaderAdapter = new HomeHeaderAdapter(null);
        recyclerViewHeader.setAdapter(homeHeaderAdapter);
        recyclerViewHeader.setNestedScrollingEnabled(false);
        recyclerViewHeader.setFocusable(false);
//        homeHeaderAdapter.bindToRecyclerView(recyclerView);
//        homeHeaderAdapter.setEmptyView(R.layout.list_empty_view);
        homeHeaderAdapter.setOnItemClickListener((adapter, view1, position) -> {
            List<HospitalItemBean> bean = (List<HospitalItemBean>) adapter.getData();
            String hospitalId = bean.get(position).getHospital_id();
            startActivity(new Intent(getContext(), HospitalDetailActivity.class)
                    .putExtra("hospitalId", hospitalId));
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            IndexSynchysisBean bean = (IndexSynchysisBean) adapter.getData().get(position);
            if ("friendsCircle".equals(bean.getData_status())) {
                String id = bean.getFriends_circle_id();
                startActivity(new Intent(getContext(), FriendCircleDetailActivity.class).putExtra("id", id));
            } else if ("popularization".equals(bean.getData_status())) {
                startActivity(new Intent(getContext(), WebViewActivity.class)
                        .putExtra(Const.WEB_VIEW_TITLE, "科普详情")
                        .putExtra(Const.WEB_VIEW_URL, bean.getLink_url()));
            }
        });
        adapter.setOnItemChildClickListener((adapter, views, position) -> {
            mPosition = position;
            IndexSynchysisBean bean = (IndexSynchysisBean) adapter.getData().get(position);
            String mFollowMethod = bean.getIs_follow() == 0 ? "save" : "delete";
            switch (views.getId()) {
                case R.id.tv_follow:
                    mPresenter.follow(bean.getHospital_id(), mFollowMethod, Const.FOLLOW_TYPE.HOSPITAL);
                    break;
            }
        });
        mPresenter.indexSynchysis();
        mPresenter.getHospital();
    }

    @Override
    public void follow(BaseBean bean) {
        IndexSynchysisBean beans = adapter.getData().get(mPosition);
        beans.setIs_follow(beans.getIs_follow() == 0 ? 1 : 0);
        adapter.notifyItemChanged(mPosition, beans);
    }

    int mPosition;

    @OnClick({R.id.tv_hospital, R.id.tv_expert, R.id.tv_case, R.id.rl_cause, R.id.rv_subscribe_test, R.id.rv_subscribe, R.id.iv_test, R.id.rl_consultation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_hospital:
                //startActivity(new Intent(getActivity(), HospitalActivity.class));
                startActivity(new Intent(getActivity(), BeautifulHairActivity.class));
                break;
            case R.id.tv_expert:
                startActivity(new Intent(getActivity(), ExpertActivity.class));
                break;
            case R.id.tv_case:
                startActivity(new Intent(getActivity(), CaseActivity.class));
                break;
            case R.id.rl_cause:
                startActivity(new Intent(getActivity(), CauseActivity.class));
                break;
            case R.id.rv_subscribe://线上预约
                startActivity(new Intent(getActivity(), NewSubscribeActivity.class));
                break;
            case R.id.rv_subscribe_test://到院检测
                startActivity(new Intent(getActivity(), NewHospitalTestActivity.class));
                break;
            case R.id.iv_test://脱发检测
                startActivity(new Intent(getActivity(), TestSplashActivity.class));
                break;
            case R.id.rl_consultation:
                CommonUtils.consultation(getActivity());
                break;
        }
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void getBundle(Bundle bundle) {

    }

    @Override
    public void indexSynchysis(List<IndexSynchysisBean> bean) {
        if (bean == null) {
            return;
        }
        if (isAdd) adapter.addData(bean);
        else adapter.replaceData(bean);
    }

    @Override
    public void getHospital(List<HospitalItemBean> bean) {
        if (bean == null) {
            return;
        }
        homeHeaderAdapter.replaceData(bean);
    }

    boolean isAdd;

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            isAdd = false;
            mPresenter.indexSynchysis();
            mPresenter.getHospital();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            isAdd = true;
            mPresenter.indexSynchysis();
            refreshLayout.finishLoadMore();
        });
    }
}
