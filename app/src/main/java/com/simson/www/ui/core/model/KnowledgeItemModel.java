package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.bean.community.PopularizationBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class KnowledgeItemModel extends BaseModel {

    public void getPopularizationList(String json,RxObserver<List<PopularizationBean>> rxObserver) {
        doRxRequest().
                getPopularizationList(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
