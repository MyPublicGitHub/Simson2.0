package com.simson.www.ui.mine.wallet.recharge;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.ui.core.model.RechargeModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.SPUtils;
import com.simson.www.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;


public class RechargePresenter extends BasePresenter<RechargeContract.View> implements RechargeContract.Presenter {
    private RechargeModel mModel;
    private RechargeContract.View mView;

    RechargePresenter() {
        this.mModel = new RechargeModel();
    }

    @Override
    public void paymentRechargeOrder() {
        mView = getView();
        if (TextUtils.isEmpty(mView.transactionMoney())){
            ToastUtils.showToast("请输入充值金额");
            return;
        }
        if (TextUtils.isEmpty(mView.paymentType())){
            ToastUtils.showToast("请选择支付方式");
            return;
        }
        RxBaseObserver<BaseBean> observer = new RxBaseObserver<BaseBean>(this) {
            @Override
            public void onNext(BaseBean bean) {
                //请求成功
                if (bean.result == NetConfig.REQUEST_SUCCESS) {
                    mView.paymentRechargeOrder(bean);
                } else {
                    //失败
                    mView.showFail(bean.message);
                }
            }
        };
        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("transactionMoney", mView.transactionMoney());//订单金额必填
        map.put("paymentType", mView.paymentType());//支付类型：1支付宝；2微信；3银联；4 Apple Pay；
        String json = new Gson().toJson(map);
        LogUtils.e(json);
        mModel.paymentRechargeOrder(json, observer);
        addDisposable(observer);
    }

}
