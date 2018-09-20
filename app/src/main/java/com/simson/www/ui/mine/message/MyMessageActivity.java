package com.simson.www.ui.mine.message;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;

public class MyMessageActivity extends BasePresenterActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_message;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("我的消息");
        return true;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
