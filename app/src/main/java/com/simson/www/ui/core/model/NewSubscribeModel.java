package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.callback.RxObserver;

public class NewSubscribeModel extends BaseModel {

    public void saveSubscribe(String json,RxObserver<BaseBean> rxObserver) {
        doRxRequest().
                saveSubscribe(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
