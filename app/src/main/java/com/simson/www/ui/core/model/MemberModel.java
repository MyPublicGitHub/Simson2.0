package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.net.bean.mine.VIPBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class MemberModel extends BaseModel {

    public void getCustomerVIP(String json, RxObserver<VIPBean> rxObserver) {
        doRxRequest().
                getCustomerVIP(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
