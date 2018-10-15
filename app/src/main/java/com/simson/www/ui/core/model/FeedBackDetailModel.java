package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.IntegralDetailBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class FeedBackDetailModel extends BaseModel {

    public void feedback(String json,RxBaseObserver rxObserver) {
        doRxRequest().
                feedback(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
