package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.HospitalDetailBean;
import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class HospitalDetailModel extends BaseModel {

    public void getHospitalDetail(String json,RxObserver<HospitalDetailBean> rxObserver) {
        doRxRequest().
                getHospitalDetail(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
