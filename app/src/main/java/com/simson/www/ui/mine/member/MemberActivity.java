package com.simson.www.ui.mine.member;

import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.VIPBean;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.SPUtils;

import butterknife.BindView;

public class MemberActivity extends BasePresenterActivity<MemberPresenter, MemberContract.View> implements MemberContract.View {


    @BindView(R.id.tv_card_number)
    TextView tvCardNumber;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_member;
    }


    @Override
    protected void initViews() {
        tvCardNumber.setText("会员卡号：" + SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));
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
