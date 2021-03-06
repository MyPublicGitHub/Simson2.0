package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

public class ProcedureModel extends BaseModel {
    public void getCommoditySubscribeProcess(String json, RxObserver<CommodityDetailBean> rxObserver) {
        doRxRequest().
                getCommoditySubscribeProcess(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
