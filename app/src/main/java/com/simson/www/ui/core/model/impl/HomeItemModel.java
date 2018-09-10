package com.simson.www.ui.core.model.impl;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.HomeItemBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.IHomeItemModel;

import java.util.List;

public class HomeItemModel extends BaseModel implements IHomeItemModel {

    @Override
    public void getHomeItemData(String json,RxObserver<List<HomeItemBean>> rxObserver) {
        doRxRequest().
                getHomeItemData(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }


}
