package com.simson.www.ui.mine.wallet.recharge;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RechargeActivity extends BasePresenterActivity<RechargePresenter, RechargeContract.View>
        implements RechargeContract.View {


    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.rb_alipay)
    RadioButton rbAlipay;
    @BindView(R.id.rb_we_chat)
    RadioButton rbWeChat;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void initViews() {
        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.rb_alipay://1支付宝；2微信；3银联；4 Apple Pay；5卡支付；6积分 必填
                    payType = "1";
                    tvCommit.setText("支付宝支付" + etMoney.getText().toString() + "元");
                    break;
                case R.id.rb_we_chat:
                    payType = "2";
                    tvCommit.setText("微信支付" + etMoney.getText().toString() + "元");
                    break;
                case R.id.rb_union:
                    payType = "3";
                    tvCommit.setText("微信支付" + etMoney.getText().toString() + "元");
                    break;
            }
        });
    }

    @OnClick({R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                break;
        }
    }

    @Override
    public String transactionMoney() {
        return etMoney.getText().toString();
    }

    String payType;

    @Override
    public String paymentType() {
        return payType;
    }

    @Override
    public void paymentRechargeOrder(BaseBean bean) {
        ToastUtils.showToast(bean.message);
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("储值充卡");
        return true;
    }

    @Override
    protected RechargePresenter createPresenter() {
        return new RechargePresenter();
    }

}
