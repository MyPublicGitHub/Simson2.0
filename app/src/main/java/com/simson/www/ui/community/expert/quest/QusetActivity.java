package com.simson.www.ui.community.expert.quest;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.QuestionsBean;
import com.simson.www.ui.adapter.QuestionAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.expert.detail.QuestionDetailActivity;
import com.simson.www.ui.community.expert.item.ExpertItemContract;
import com.simson.www.ui.community.expert.item.ExpertItemPresenter;

import java.util.List;

import butterknife.BindView;

public class QusetActivity extends BasePresenterActivity<ExpertItemPresenter, ExpertItemContract.View>
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
    protected int getLayoutId() {
        return R.layout.activity_quset;
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuestionAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<QuestionsBean> bean = (List<QuestionsBean>) adapter.getData();
            String questionsId = bean.get(position).getQuestions_id();
            startActivity(new Intent(this, QuestionDetailActivity.class).putExtra("questionsId", questionsId));
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
    }

    @Override
    protected void initData() {
        super.initData();
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
    public int getPage() {
        return mPage;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void goToLogin() {

    }

    @Override
    protected ExpertItemPresenter createPresenter() {
        return new ExpertItemPresenter();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("问答");
        return true;
    }
}
