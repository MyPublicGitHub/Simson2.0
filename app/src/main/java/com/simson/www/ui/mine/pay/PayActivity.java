package com.simson.www.ui.mine.pay;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.PaymentOrderBean;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.ui.adapter.PayAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.ToastUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.text.DecimalFormat;
import java.util.ArrayList;

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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_alipay)
    LinearLayout llAlipay;
    @BindView(R.id.ll_wechat_pay)
    LinearLayout llWechatPay;
    @BindView(R.id.ll_balance)
    LinearLayout llBalance;
    @BindView(R.id.ll_point)
    LinearLayout llPoint;
    @BindView(R.id.rb_balance)
    RadioButton rbBalance;
    @BindView(R.id.rb_point)
    RadioButton rbPoint;
    private String points, money, orderId, payType;//支付类型：1支付宝；2微信；3银联；4 Apple Pay；5卡支付；6积分 必填
    boolean isPoint;
    ArrayList<CommodityDetailBean> datas;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay;
    }

    PayAdapter adapter;

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PayAdapter(datas);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        if (isPoint) {
            tvMoney.setText(points + "积分");
            llAlipay.setVisibility(View.GONE);
            llWechatPay.setVisibility(View.GONE);
            llBalance.setVisibility(View.GONE);
            rbAlipay.setVisibility(View.GONE);
            rbWeChat.setVisibility(View.GONE);
            rbBalance.setVisibility(View.GONE);
        } else {
            tvMoney.setText("合计：" + money + "");
            llPoint.setVisibility(View.GONE);
            rbPoint.setVisibility(View.GONE);
        }
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
                case R.id.rb_balance:
                    payType = "5";
                    tvPayNow.setText("余额支付" + money + "元");
                    break;
                case R.id.rb_point:
                    payType = "6";
                    tvPayNow.setText("支付" + points + "积分");
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
        } else {
            ToastUtils.showToast("支付成功");
            startActivity(new Intent(this, PayCompleteActivity.class));
            finish();
        }
    }

    @Override
    protected void getIntent(Intent intent) {
        money = getTwoDecimal(getIntent().getDoubleExtra("transactionMoney", 0.00));
        points = getIntent().getIntExtra("transactionPoint", 0) + "";
        orderId = getIntent().getStringExtra("orderId");
        isPoint = getIntent().getBooleanExtra("isPoint", false);
        datas = getIntent().getParcelableArrayListExtra("CommodityDetailBean");
        LogUtils.e("----------------money:" + money);
    }


    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public String getTransactionMoney() {
        if (".00".equals(money)) {
            return "0.00";
        }
        return money;
    }

    /**
     * 将数据保留两位小数
     */
    private String getTwoDecimal(double num) {
        DecimalFormat dFormat = new DecimalFormat("###.00");
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
