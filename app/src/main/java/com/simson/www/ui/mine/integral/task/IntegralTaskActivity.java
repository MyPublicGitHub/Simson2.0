package com.simson.www.ui.mine.integral.task;

import android.content.Intent;
import android.view.View;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.home.test.TestActivity;
import com.simson.www.ui.mine.invitation.InvitationActivity;
import com.simson.www.ui.mine.sign.SignActivity;

import butterknife.OnClick;

public class IntegralTaskActivity extends BasePresenterActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral_task;
    }

    @OnClick({R.id.btn_sign_in, R.id.btn_test, R.id.btn_friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                startActivity(new Intent(this, SignActivity.class));
                break;
            case R.id.btn_test:
                startActivity(new Intent(this, TestActivity.class));
                break;
            case R.id.btn_friend:
                startActivity(new Intent(this, InvitationActivity.class));
                break;
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("积分任务中心");
        return true;
    }
}
