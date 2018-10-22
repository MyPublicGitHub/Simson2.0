package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.utils.AESUtils;

public class NewAddressModel extends BaseModel {

    public void newAddress(String json, RxBaseObserver rxObserver) {
        doRxRequest().
                newAddress(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
