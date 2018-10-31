package com.simson.www.ui.mine.wallet.recharge;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.PaymentOrderBean;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.LogUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;

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
//                case R.id.rb_union:
//                    payType = "3";
//                    tvCommit.setText("微信支付" + etMoney.getText().toString() + "元");
//                    break;
            }
        });
    }

    @OnClick({R.id.tv_commit, R.id.tv_money1, R.id.tv_money2, R.id.tv_money3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                mPresenter.paymentRechargeOrder();
                break;
            case R.id.tv_money1:
                etMoney.setText("10000");
                break;
            case R.id.tv_money2:
                etMoney.setText("20000");
                break;
            case R.id.tv_money3:
                etMoney.setText("30000");
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
    public void paymentRechargeOrder(PaymentOrderBean beans) {
        //生成支付信息成功，发起支付
        LogUtils.e("beans:"+beans);
        if ("1".equals(payType)) {
            if (beans == null || TextUtils.isEmpty(beans.getSingn())) {
                LogUtils.d("生成支付信息失败");
                return;
            }
            mPresenter.alipay(this, beans.getSingn());
        } else if ("2".equals(payType)) {
            PayReq request = new PayReq();
            request.appId = Const.WE_CHAT_APP_ID;
            request.partnerId = beans.getPartnerid();
            request.prepayId = beans.getPrepayid();
            request.packageValue = beans.getPackageX();
            request.nonceStr = beans.getNoncestr();
            request.timeStamp = beans.getTimestamp();
            request.sign = beans.getSign();
            mPresenter.wechatPay(this, request);
        }
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
