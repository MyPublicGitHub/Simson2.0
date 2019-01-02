package com.simson.www.ui.mine.wallet;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.net.bean.mine.CustomerBasicBean;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.mine.wallet.log.TransactionRecordActivity;
import com.simson.www.ui.mine.wallet.recharge.RechargeActivity;
import com.simson.www.ui.mine.wallet.red.RedEnvelopesActivity;
import com.simson.www.utils.CommonUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class WalletActivity extends BasePresenterActivity<WalletPresenter, WalletContract.View>
        implements WalletContract.View {
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

    @OnClick({R.id.ll_recharge, R.id.ll_log, R.id.ll_help, R.id.ll_red})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_recharge:
                startActivity(new Intent(this, RechargeActivity.class));
                break;
            case R.id.ll_red:
                startActivity(new Intent(this, RedEnvelopesActivity.class));
                break;
            case R.id.ll_log:
                startActivity(new Intent(this, TransactionRecordActivity.class));
                break;
            case R.id.ll_help:
                CommonUtils.consultation(this);
                break;
        }
    }

    @Override
    protected void initData() {
        mPresenter.getCustomerBasicInfo();
    }

    @Override
    public void getCustomerBasicInfo(CustomerBasicBean bean) {
        if (bean == null) return;
        tvWallet.setText("" + bean.getCard_amount());
    }

    @Override
    protected WalletPresenter createPresenter() {
        return new WalletPresenter();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("新生钱包");
        return true;
    }

}
