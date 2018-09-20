package com.simson.www.ui.mine.integral;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;

public class IntegralActivity extends BasePresenterActivity {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("积分");
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral;
    }
}
