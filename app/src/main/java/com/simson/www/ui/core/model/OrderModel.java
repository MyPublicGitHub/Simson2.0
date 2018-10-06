package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.net.bean.mine.ShopCartBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class OrderModel extends BaseModel {

    public void getOrder(String json,RxObserver<List<OrderBean>> rxObserver) {
        doRxRequest().
                getOrder(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
