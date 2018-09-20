package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.callback.RxObserver;

public class HomeModel extends BaseModel {

    public void getHomeBannerData(String json,RxObserver<HomeBannerBean> rxObserver) {
        doRxRequest().
                getHomeBannerData(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
