package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.AddressDetailBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

public class AddressDetailModel extends BaseModel {

    public void getAddressDetail(String json, RxObserver<AddressDetailBean> rxObserver) {
        doRxRequest().
                getAddressDetail(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
