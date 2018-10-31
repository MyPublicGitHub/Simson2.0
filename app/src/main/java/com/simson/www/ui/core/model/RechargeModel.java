package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.PaymentOrderBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.utils.AESUtils;

public class RechargeModel extends BaseModel {

    public void paymentRechargeOrder(String json, RxBaseObserver<PaymentOrderBean> rxObserver) {
        doRxRequest().
                paymentRechargeOrder(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
