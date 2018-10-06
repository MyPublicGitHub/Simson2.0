package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.CaseBean;
import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class CaseModel extends BaseModel {

    public void getCase(String json,RxObserver<List<CaseBean>> rxObserver) {
        doRxRequest().
                getCase(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
