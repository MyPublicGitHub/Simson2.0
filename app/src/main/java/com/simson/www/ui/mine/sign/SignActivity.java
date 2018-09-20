package com.simson.www.ui.mine.sign;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;

public class SignActivity extends BasePresenterActivity {

    @Override
    protected boolean initToolbar() {
        mTitle.setText("签到");
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
