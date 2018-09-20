package com.simson.www.ui.home.expert.detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.adapter.ExpertAdapter;
import com.simson.www.ui.adapter.ExpertDetailAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ExpertDetailActivity extends BasePresenterActivity {

    @BindView(R.id.rv_experts)
    RecyclerView recyclerView;

    ExpertDetailAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_expert_detail;
    }

    @Override
    protected void initViews() {
        List<BaseBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new BaseBean());
        }
        adapter = new ExpertDetailAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}
