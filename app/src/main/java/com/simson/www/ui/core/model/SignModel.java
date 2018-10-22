package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.SignBean;
import com.simson.www.net.bean.mine.SignPageBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

public class SignModel extends BaseModel {

    public void signInPage(String json, RxObserver<SignPageBean> rxObserver) {
        doRxRequest().
                signInPage(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void signIn(String json, RxObserver<SignBean> rxObserver) {
        doRxRequest().
                signIn(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
