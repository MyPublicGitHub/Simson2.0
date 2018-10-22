package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.PaymentOrderBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

public class PayModel extends BaseModel {

    public void paymentOrder(String json, RxObserver<PaymentOrderBean> rxObserver) {
        doRxRequest().
                paymentOrder(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
