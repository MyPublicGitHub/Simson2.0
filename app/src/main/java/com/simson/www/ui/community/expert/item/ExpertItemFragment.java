package com.simson.www.ui.community.expert.item;


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
import com.simson.www.net.bean.community.QuestionsBean;
import com.simson.www.net.bean.home.IndexSynchysisBean;
import com.simson.www.ui.adapter.QuestionAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.community.expert.detail.QuestionDetailActivity;
import com.simson.www.ui.main.login.LoginActivity;

import java.util.List;

import butterknife.BindView;


public class ExpertItemFragment extends BasePresenterFragment<ExpertItemPresenter, ExpertItemContract.View>
        implements ExpertItemContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private String type;
    private int mPage = 1;
    QuestionAdapter adapter;
    int mPosition;
    @Override
    protected void initViews(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new QuestionAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<QuestionsBean> bean = (List<QuestionsBean>) adapter.getData();
            String questionsId = bean.get(position).getQuestions_id();
            startActivity(new Intent(getContext(), QuestionDetailActivity.class).putExtra("questionsId", questionsId));
        });
        adapter.setOnItemChildClickListener((adapter, views, position) -> {
            mPosition = position;
            QuestionsBean bean = (QuestionsBean) adapter.getData().get(position);
            String mFollowMethod = bean.getIs_follow() == 0 ? "save" : "delete";
            switch (views.getId()) {
                case R.id.tv_follow:
                    mPresenter.follow(bean.getCustomer_id(), mFollowMethod, Const.FOLLOW_TYPE.USER);
                    break;
            }
        });
        setRefresh();
        mPresenter.getQuestionsList();
    }
    @Override
    public void follow(BaseBean bean) {
        List<QuestionsBean> data = adapter.getData();
        QuestionsBean beans = data.get(mPosition);
        beans.setIs_follow(beans.getIs_follow() == 0 ? 1 : 0);
        adapter.notifyItemChanged(mPosition,beans);
    }
    @Override
    public void showQuestionsList(List<QuestionsBean> bean) {
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
            mPresenter.getQuestionsList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getQuestionsList();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    protected ExpertItemPresenter createPresenter() {
        return new ExpertItemPresenter();
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
    public void goToLogin() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
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

    public static ExpertItemFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        ExpertItemFragment fragment = new ExpertItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
