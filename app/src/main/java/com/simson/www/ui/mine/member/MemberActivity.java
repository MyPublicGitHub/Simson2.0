package com.simson.www.ui.mine.member;

import com.simson.www.R;
import com.simson.www.net.bean.mine.VIPBean;
import com.simson.www.ui.base.BasePresenterActivity;

public class MemberActivity extends BasePresenterActivity<MemberPresenter, MemberContract.View> implements MemberContract.View {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_member;
    }


    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void initData() {
        mPresenter.getCustomerVIP();
    }

    @Override
    protected MemberPresenter createPresenter() {
        return new MemberPresenter();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("会员卡");
        return true;
    }

    @Override
    public void getCustomerVIP(VIPBean bean) {

    }
}
