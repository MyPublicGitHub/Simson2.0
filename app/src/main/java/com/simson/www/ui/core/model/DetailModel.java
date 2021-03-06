package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

public class DetailModel extends BaseModel {
    public void getCommodityDetailPicture(String json, RxObserver<CommodityDetailBean> rxObserver) {
        doRxRequest().
                getCommodityDetailPicture(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
