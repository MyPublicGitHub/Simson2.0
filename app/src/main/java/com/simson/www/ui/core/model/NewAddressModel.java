package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class NewAddressModel extends BaseModel {

    public void newAddress(String json,RxObserver rxObserver) {
        doRxRequest().
                newAddress(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
