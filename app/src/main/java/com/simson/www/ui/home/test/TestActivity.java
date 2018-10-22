package com.simson.www.ui.home.test;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;

public class TestActivity extends BasePresenterActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("毛囊检测");
        return true;
    }
}
