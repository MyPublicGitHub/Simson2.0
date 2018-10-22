package com.simson.www.ui.core.model;


import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.net.callback.RxObserver;

public class WalletModel extends BaseModel {

    public void getCustomerInfo(String json, RxObserver<CustomerInfoBean> rxObserver) {
//        doRxRequest().
//                getCustomerInfo(json)
//                .compose(RxSchedulers.io_main())
//                .subscribe(rxObserver);
    }
}
