package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.net.bean.mine.SignBean;
import com.simson.www.net.bean.mine.SignPageBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;

public class SignModel extends BaseModel {

    public void signInPage(String json,RxObserver<SignPageBean> rxObserver) {
        doRxRequest().
                signInPage(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
    public void signIn(String json,RxObserver<SignBean> rxObserver) {
        doRxRequest().
                signIn(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
