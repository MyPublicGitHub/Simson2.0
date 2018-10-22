package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.SubscribeListBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class SubscribeModel extends BaseModel {

    public void subscribeList(String json, RxObserver<List<SubscribeListBean>> rxObserver) {
        doRxRequest().
                subscribeList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
