package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.net.bean.mine.IntegralDetailBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class IntegralDetailModel extends BaseModel {

    public void pointList(String json,RxObserver<List<IntegralDetailBean>> rxObserver) {
        doRxRequest().
                pointList(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
