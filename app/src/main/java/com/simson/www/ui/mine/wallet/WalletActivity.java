package com.simson.www.ui.mine.wallet;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.wallet.log.TransactionRecordActivity;
import com.simson.www.ui.mine.wallet.recharge.RechargeActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class WalletActivity extends BasePresenterActivity {
    @BindView(R.id.tv_wallet)
    TextView tvWallet;
    @BindView(R.id.ll_recharge)
    LinearLayout llRecharge;
    @BindView(R.id.ll_log)
    LinearLayout llLog;
    @BindView(R.id.ll_help)
    LinearLayout llHelp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet;
    }

    @OnClick({R.id.ll_recharge, R.id.ll_log, R.id.ll_help})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_recharge:
                startActivity(new Intent(this,RechargeActivity.class));
                break;
            case R.id.ll_log:
                startActivity(new Intent(this,TransactionRecordActivity.class));
                break;
            case R.id.ll_help:
                break;
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("新生钱包");
        return true;
    }

}
