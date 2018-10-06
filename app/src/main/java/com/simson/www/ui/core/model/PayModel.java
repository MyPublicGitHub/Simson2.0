package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.net.bean.mine.PaymentOrderBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class PayModel extends BaseModel {

    public void paymentOrder(String json,RxObserver<PaymentOrderBean> rxObserver) {
        doRxRequest().
                paymentOrder(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
