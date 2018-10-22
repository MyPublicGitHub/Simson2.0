package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.utils.AESUtils;

public class FeedBackDetailModel extends BaseModel {

    public void feedback(String json, RxBaseObserver rxObserver) {
        doRxRequest().
                feedback(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
