package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.CustomerBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

public class MineModel extends BaseModel {

    public void getCustomer(String json, RxObserver<CustomerBean> rxObserver) {
        doRxRequest().
                getCustomer(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
