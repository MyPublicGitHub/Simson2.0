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

}
