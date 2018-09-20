package com.simson.www.ui.mine.set.address.add;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;

public class NewAddressActivity extends BasePresenterActivity {



    @Override
    protected void initViews() {


    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("新增收货地址");
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_address;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
