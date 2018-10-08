package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class HomeItemModel extends BaseModel{

    public void getHomeItemData(String json,RxObserver<List<DiaryBean>> rxObserver) {
        doRxRequest().
                getHomeItemData(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }


}
