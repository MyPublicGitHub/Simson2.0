package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.HomeItemBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class HomeItemModel extends BaseModel{

    public void getHomeItemData(String json,RxObserver<List<HomeItemBean>> rxObserver) {
        doRxRequest().
                getHomeItemData(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }


}
