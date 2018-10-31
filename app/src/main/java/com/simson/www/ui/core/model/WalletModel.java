package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.CustomerBasicBean;
import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

public class WalletModel extends BaseModel {

    public void getCustomerBasicInfo(String json, RxObserver<CustomerBasicBean> rxObserver) {
        doRxRequest().
                getCustomerBasicInfo(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
