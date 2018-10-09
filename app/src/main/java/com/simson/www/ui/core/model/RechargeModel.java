package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class UserInfoModel extends BaseModel {

    public void getCustomerInfo(String json,RxObserver<CustomerInfoBean> rxObserver) {
        doRxRequest().
                getCustomerInfo(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
    public void updateCustomerInfo(String json,RxBaseObserver rxObserver) {
        doRxRequest().
                updateCustomerInfo(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
