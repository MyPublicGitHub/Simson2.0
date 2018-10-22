package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.community.PopularizationBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class KnowledgeItemModel extends BaseModel {

    public void getPopularizationList(String json, RxObserver<List<PopularizationBean>> rxObserver) {
        doRxRequest().
                getPopularizationList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
