package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.net.bean.mine.CaseBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class HospitalItemModel extends BaseModel {

    public void getHospital(String json,RxObserver<List<HospitalItemBean>> rxObserver) {
        doRxRequest().
                getHospital(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
