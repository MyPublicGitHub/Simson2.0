package com.simson.www.ui.mine.pay;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.PaymentOrderBean;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.LogUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

public class PayActivity extends BasePresenterActivity<PayPresenter, PayContract.View> implements PayContract.View {
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_point)
    TextView tvPoint;
    @BindView(R.id.rb_alipay)
    RadioButton rbAlipay;
    @BindView(R.id.rb_we_chat)
    RadioButton rbWeChat;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.tv_pay_now)
    TextView tvPayNow;
    private String points, money, orderId, payType;//支付类型：1支付宝；2微信；3银联；4 Apple Pay；5卡支付；6积分 必填

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initViews() {
        tvMoney.setText("订单金额：" + money + "元");
        tvOrderId.setText("订单编号：" + orderId);
        tvPoint.setText("订单积分：" + points);
        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.rb_alipay://1支付宝；2微信；3银联；4 Apple Pay；5卡支付；6积分 必填
                    payType = "1";
                    tvPayNow.setText("支付宝支付" + money + "元");
                    break;
                case R.id.rb_we_chat:
                    payType = "2";
                    tvPayNow.setText("微信支付" + money + "元");
                    break;
                case R.id.rb_union:
                    payType = "3";
                    tvPayNow.setText("微信支付" + money + "元");
                    break;
            }
        });
    }

    @OnClick({R.id.tv_pay_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pay_now:
                mPresenter.paymentOrder();
                break;
        }
    }

    @Override
    public void showPaymentOrder(PaymentOrderBean beans) {
        //生成支付信息成功，发起支付
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
    protected void getIntent(Intent intent) {
        money = getTwoDecimal(getIntent().getDoubleExtra("transactionMoney", 0));
        points = getIntent().getIntExtra("transactionPoint", 0) + "";
        orderId = getIntent().getStringExtra("orderId");
        LogUtils.e("----------------money:"+money);
    }


    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public String getTransactionMoney() {
        return money;
    }

    /**
     * 将数据保留两位小数
     */
    private String getTwoDecimal(double num) {
        DecimalFormat dFormat = new DecimalFormat("#.00");
        String yearString = dFormat.format(num);
        return yearString;
    }


    @Override
    public String getTtransactionPoint() {
        return points;
    }

    @Override
    public String getPaymentType() {
        return payType;
    }

    @Override
    public String getDeliveryId() {
        return "";
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("立即支付");
        return true;
    }

    @Override
    protected PayPresenter createPresenter() {
        return new PayPresenter();
    }

}
