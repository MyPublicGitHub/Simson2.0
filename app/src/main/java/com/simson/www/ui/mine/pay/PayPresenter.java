package com.simson.www.ui.mine.pay;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.AliPayResult;
import com.simson.www.net.bean.mine.PaymentOrderBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.PayModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.SPUtils;
import com.simson.www.utils.ToastUtils;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;


public class PayPresenter extends BasePresenter<PayContract.View> implements PayContract.Presenter {
    private PayModel mModel;
    private PayContract.View mView;

    PayPresenter() {
        this.mModel = new PayModel();
    }

    public void wechatPay(Context context, PayReq request) {
        IWXAPI api = WXAPIFactory.createWXAPI(context, Const.WE_CHAT_APP_ID);
        if (api.getWXAppSupportAPI() < Build.PAY_SUPPORTED_SDK_INT) {
            ToastUtils.showToast("您未安装最新版本微信，不支持微信支付，请安装或升级微信版本");
            return;
        }
        boolean b = api.sendReq(request);
        LogUtils.d("b:" + b + ";wechat");
    }

    public void alipay(Activity activity, String orderInfo) {
        Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(activity);
            Map<String, String> result = alipay.payV2(orderInfo, true);

            Message msg = new Message();
            msg.obj = result;
            mHandler.sendMessage(msg);
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void paymentOrder() {
        mView = getView();
        if (TextUtils.isEmpty(mView.getPaymentType())) {
            ToastUtils.showToast("请选择支付方式");
            return;
        }
        RxObserver<PaymentOrderBean> observer = new RxObserver<PaymentOrderBean>(this) {

            @Override
            public void onSuccess(PaymentOrderBean mData) {
                mView.showPaymentOrder(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };
        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("orderId", mView.getOrderId());
        map.put("transactionMoney", mView.getTransactionMoney());
        map.put("transactionPoint", mView.getTtransactionPoint());//订单积分必填
        map.put("paymentType", mView.getPaymentType());//1支付宝；2微信；3银联；4 Apple Pay；5卡支付；6积分 必填
        map.put("deliveryId", mView.getDeliveryId());
        String json = new Gson().toJson(map);
        mModel.paymentOrder(json, observer);
        addDisposable(observer);
    }

    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            AliPayResult payResult = new AliPayResult((Map<String, String>) msg.obj);
            switch (payResult.getResultStatus()) {
                case "9000":
                    ToastUtils.showToast("支付成功");
                    break;
                case "8000":
                    ToastUtils.showToast("正在处理中");
                    break;
                case "4000":
                    ToastUtils.showToast("订单支付失败");
                    break;
                case "5000":
                    ToastUtils.showToast("重复请求");
                    break;
                case "6001":
                    ToastUtils.showToast("已取消支付");
                    break;
                case "6002":
                    ToastUtils.showToast("网络连接出错");
                    break;
                case "6004":
                    ToastUtils.showToast("正在处理中");
                    break;
                default:
                    ToastUtils.showToast("支付失败");
                    break;
            }
        }
    };
}
