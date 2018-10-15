package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.net.bean.mine.SubscribeListBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class SubscribeModel extends BaseModel {

    public void subscribeList(String json,RxObserver<List<SubscribeListBean>> rxObserver) {
        doRxRequest().
                subscribeList(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
