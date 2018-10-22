package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.CityListBean;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;


public class CommonModel extends BaseModel {
    public void cityList(String json, RxObserver<List<CityListBean>> rxObserver) {
        doRxRequest().
                getCityList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void getItemType(String json, RxObserver<List<ItemTypeBean>> callback) {
        doRxRequest().
                getItemType(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }

    public void collect(String json, RxBaseObserver callback) {
        doRxRequest().
                collect(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }

    public void follow(String json, RxBaseObserver callback) {
        doRxRequest().
                follow(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }

    public void praise(String json, RxBaseObserver callback) {
        doRxRequest().
                praise(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }
}
