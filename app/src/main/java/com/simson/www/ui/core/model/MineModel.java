package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.bean.mine.CustomerBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class MineModel extends BaseModel {

    public void getCustomer(String json,RxObserver<CustomerBean> rxObserver) {
        doRxRequest().
                getCustomer(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
