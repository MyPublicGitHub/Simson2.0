package com.simson.www.ui.mine.integral;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.integral.detail.IntegralDetailActivity;
import com.simson.www.ui.mine.integral.mall.IntegralMallActivity;
import com.simson.www.ui.mine.invitation.InvitationActivity;
import com.simson.www.ui.mine.sign.SignActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntegralActivity extends BasePresenterActivity {

    @BindView(R.id.mine_integral)
    TextView mineIntegral;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral;
    }

    @OnClick({R.id.ll_integral_mall, R.id.integral_detail, R.id.btn_sign_in, R.id.btn_new_diary, R.id.btn_test, R.id.btn_friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_integral_mall:
                startActivity(new Intent(this,IntegralMallActivity.class));
                break;
            case R.id.integral_detail:
                startActivity(new Intent(this,IntegralDetailActivity.class));
                break;
            case R.id.btn_sign_in:
                startActivity(new Intent(this,SignActivity.class));
                break;
            case R.id.btn_new_diary:
                break;
            case R.id.btn_test:
                break;
            case R.id.btn_friend:
                startActivity(new Intent(this,InvitationActivity.class));
                break;
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("我的积分");
        return true;
    }
}
