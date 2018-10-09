package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.callback.RxBaseObserver;

public class RechargeModel extends BaseModel {

    public void paymentRechargeOrder(String json, RxBaseObserver<BaseBean> rxObserver) {
        doRxRequest().
                paymentRechargeOrder(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
