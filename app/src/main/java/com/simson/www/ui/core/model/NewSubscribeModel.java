package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

public class NewSubscribeModel extends BaseModel {

    public void saveSubscribe(String json, RxObserver<BaseBean> rxObserver) {
        doRxRequest().
                saveSubscribe(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
