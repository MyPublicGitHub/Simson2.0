package com.simson.www.ui.mine.user;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;

public class UserInfoActivity extends BasePresenterActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("个人信息");
        return true;
    }
}
