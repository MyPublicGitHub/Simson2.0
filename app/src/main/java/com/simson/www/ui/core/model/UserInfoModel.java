package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

public class UserInfoModel extends BaseModel {

    public void getCustomerInfo(String json, RxObserver<CustomerInfoBean> rxObserver) {
        doRxRequest().
                getCustomerInfo(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void updateCustomerInfo(String json, RxBaseObserver rxObserver) {
        doRxRequest().
                updateCustomerInfo(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
