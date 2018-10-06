package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;

import java.util.List;


public class CommonModel extends BaseModel {
    public void getItemType(String json,RxObserver<List<ItemTypeBean>> callback) {
        doRxRequest().
                getItemType(json)
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }
    public void collect(String json,RxBaseObserver callback) {
        doRxRequest().
                collect(json)
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }
    public void follow(String json,RxBaseObserver callback) {
        doRxRequest().
                follow(json)
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }
    public void praise(String json,RxBaseObserver callback) {
        doRxRequest().
                praise(json)
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }
}
