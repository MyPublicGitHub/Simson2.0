package com.simson.www.ui.core.model.impl;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.callback.RxObserver;

public class HospitalActivityModel extends BaseModel {

    public void getHomeBannerData(RxObserver<HomeBannerBean> rxObserver) {
        doRxRequest().
                getHomeBannerData()
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
