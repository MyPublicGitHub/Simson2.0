package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;


public class CommonItemTypeModel extends BaseModel {
    public void getItemType(String json,RxObserver<List<ItemTypeBean>> callback) {
        doRxRequest().
                getItemType(json)
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }

}
