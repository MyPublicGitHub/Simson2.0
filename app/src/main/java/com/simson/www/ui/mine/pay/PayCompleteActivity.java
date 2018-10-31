package com.simson.www.ui.mine.pay;

import android.content.Intent;
import android.view.View;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.order.OrderActivity;

import butterknife.OnClick;

public class PayCompleteActivity extends BasePresenterActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_complete;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("支付订单");
        return true;
    }


    @OnClick(R.id.tv_order)
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tv_order:
                startActivity(new Intent(this,OrderActivity.class).putExtra("status", "2"));
                finish();
                break;
        }
    }
}
