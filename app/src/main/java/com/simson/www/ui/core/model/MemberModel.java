package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class OrderModel extends BaseModel {

    public void getOrder(String json, RxObserver<List<OrderBean>> rxObserver) {
        doRxRequest().
                getOrder(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
