package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.HospitalDetailBean;
import com.simson.www.net.bean.home.HospitalDeviceBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class HospitalDetailModel extends BaseModel {

    public void getHospitalDeviceList(String json, RxObserver<List<HospitalDeviceBean>> rxObserver) {
        doRxRequest().
                getHospitalDeviceList(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void getHospitalDetail(String json, RxObserver<HospitalDetailBean> rxObserver) {
        doRxRequest().
                getHospitalDetail(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
