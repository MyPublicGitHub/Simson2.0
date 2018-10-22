package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.IndexSynchysisBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class HomeModel extends BaseModel {

    public void indexSynchysis(String json, RxObserver<List<IndexSynchysisBean>> rxObserver) {
        doRxRequest().
                indexSynchysis(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
