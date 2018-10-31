package com.simson.www.ui.mine.pay;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.PaymentOrderBean;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.GlideUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.ToastUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

public class PayActivity extends BasePresenterActivity<PayPresenter, PayContract.View> implements PayContract.View {

    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.rb_alipay)
    RadioButton rbAlipay;
    @BindView(R.id.rb_we_chat)
    RadioButton rbWeChat;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.tv_pay_now)
    TextView tvPayNow;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_unity_price)
    TextView tvUnityPrice;
    @BindView(R.id.rb_union)
    RadioButton rbUnion;
    private String points, money, orderId, buyNum, payType;//支付类型：1支付宝；2微信；3银联；4 Apple Pay；5卡支付；6积分 必填
    boolean isPoint;
    double unityPrice;
    CommodityDetailBean beans;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initViews() {
        GlideUtils.with(beans.getPicture().get(0), ivImage);
        tvTitle.setText(beans.getItem_name() + "");
        tvMoney.setText(money + "");
        tvNumber.setText("X" + buyNum);
        tvUnityPrice.setText("￥" + unityPrice);
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
                    payType = "5";
                    tvPayNow.setText("余额支付" + money + "元");
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
        } else if ("5".equals(payType)) {
            ToastUtils.showToast("支付成功");
            startActivity(new Intent(this,PayCompleteActivity.class));
            finish();
        }
    }

    @Override
    protected void getIntent(Intent intent) {
        money = getTwoDecimal(getIntent().getDoubleExtra("transactionMoney", 0.00));
        points = getIntent().getIntExtra("transactionPoint", 0) + "";
        orderId = getIntent().getStringExtra("orderId");
        buyNum = getIntent().getStringExtra("buyNum");
        unityPrice = getIntent().getDoubleExtra("unityPrice", 0.00);
        isPoint = getIntent().getBooleanExtra("isPoint", false);
        beans = (CommodityDetailBean) getIntent().getSerializableExtra("CommodityDetailBean");
        LogUtils.e("----------------money:" + money);
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
