package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class AddressModel extends BaseModel {

    public void getAddress(String json,RxObserver<List<AddressBean>> rxObserver) {
        doRxRequest().
                getAddress(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
