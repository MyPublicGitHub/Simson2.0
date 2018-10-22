package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.utils.AESUtils;

public class EditAddressModel extends BaseModel {

    public void editAddress(String json, RxBaseObserver rxObserver) {
        doRxRequest().
                editAddress(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
