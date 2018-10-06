package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.CauseListBean;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class CauseModel extends BaseModel {

    public void getCauseList(String json,RxObserver<List<CauseListBean>> rxObserver) {
        doRxRequest().
                getCauseList(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
