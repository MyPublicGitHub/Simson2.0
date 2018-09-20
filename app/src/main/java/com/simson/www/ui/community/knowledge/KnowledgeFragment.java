package com.simson.www.ui.community.knowledge;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.adapter.HDACaseDiaryAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.core.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class KnowledgeFragment extends BasePresenterFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    HDACaseDiaryAdapter adapter;

    @Override
    protected void getBundle(Bundle bundle) {

    }


    @Override
    protected void initViews(View view) {
        List<BaseBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new BaseBean());
        }


        adapter = new HDACaseDiaryAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
