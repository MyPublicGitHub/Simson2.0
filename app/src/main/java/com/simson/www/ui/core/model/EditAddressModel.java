package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class EditAddressModel extends BaseModel {

    public void editAddress(String json,RxBaseObserver rxObserver) {
        doRxRequest().
                editAddress(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
