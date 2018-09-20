package com.simson.www.ui.mine.integral.mall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.adapter.IntegralMallAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.integral.IntegralActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntegralMallActivity extends BasePresenterActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    IntegralMallAdapter mIntegralMallAdapter;
    @BindView(R.id.ll_my_integral)
    LinearLayout llMyIntegral;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral_mall;
    }

    @Override
    protected void initViews() {
        List<BaseBean> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add(new BaseBean());
        }
        mIntegralMallAdapter = new IntegralMallAdapter(list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(mIntegralMallAdapter);
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("积分商城");
        return true;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.ll_my_integral})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_integral:
                startActivity(new Intent(this, IntegralActivity.class));
                break;
        }
    }
}
