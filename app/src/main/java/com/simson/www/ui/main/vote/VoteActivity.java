package com.simson.www.ui.main.vote;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.main.ProgramBean;
import com.simson.www.net.bean.main.VoteBean;
import com.simson.www.ui.adapter.VoteAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.SPUtils;
import com.simson.www.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

public class VoteActivity extends BasePresenterActivity<VotePresenter, VoteContract.View> implements VoteContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    VoteAdapter adapter;

    @Override
    protected void initViews() {
        setRefresh();
        mRefreshLayout.setEnableLoadMore(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VoteAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            if (isSelect) {
                return;
            }
            for (int i = 0; i < adapter.getData().size(); i++) {
                ProgramBean beans = (ProgramBean) adapter.getData().get(i);
                /*if (beans.isSelection()) {
                    return;
                }*/
                if (i == position) {
                    beans.setSelection(true);
                    id = beans.getId();
                } else {
                    beans.setSelection(false);
                }
            }
            adapter.notifyDataSetChanged();
        });
    }

    public void finish(View view) {
        finish();
    }

    public void voteClick(View view) {
        mPresenter.vote();
    }

    @Override
    protected void initData() {
        mPresenter.program();
    }

    @Override
    public String phone() {
        return (String) SPUtils.get(Const.USER_INFO.CUSTOMER_MOBLE, "");
    }

    String id;

    @Override
    public String id() {
        return id;
    }

    boolean isSelect;

    @Override
    public void program(List<ProgramBean> bean) {
        adapter.replaceData(bean);
        isSelect = false;
        for (int i = 0; i < bean.size(); i++) {
            ProgramBean beans = (ProgramBean) bean.get(i);
            if (beans.isSelection()) {
                id = beans.getId();
                isSelect = true;
                return;
            }
        }
    }

    @Override
    public void vote(VoteBean bean) {
        ToastUtils.showToast(bean.getMessage());
        mPresenter.program();
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            isSelect = false;
            mPresenter.program();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vote;
    }

    @Override
    protected VotePresenter createPresenter() {
        return new VotePresenter();
    }
}
