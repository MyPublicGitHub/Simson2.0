package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.IntegralDetailBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class IntegralDetailModel extends BaseModel {

    public void pointList(String json, RxObserver<List<IntegralDetailBean>> rxObserver) {
        doRxRequest().
                pointList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
