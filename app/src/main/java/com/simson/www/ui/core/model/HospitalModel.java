package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.CityListBean;
import com.simson.www.net.bean.mine.CaseBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class HospitalModel extends BaseModel {

    public void getCityList(String json,RxObserver<List<CityListBean>> rxObserver) {
        doRxRequest().
                getCityList(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
