package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxRedObserver;
import com.simson.www.utils.AESUtils;

public class MainModel extends BaseModel {

    public void newestRedEnvelope(String url,RxRedObserver rxObserver) {
        doRxRequest().
                newestRedEnvelope(url)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void receiveRedEnvelope(String url,String json, RxRedObserver rxObserver) {
        doRxRequest().
                receiveRedEnvelope(url,AESUtils.encryptRed(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
