package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.HospitalDetailBean;
import com.simson.www.net.bean.home.HospitalDeviceBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class DeviceModel extends BaseModel {

    public void getHospitalDeviceList(String json, RxObserver<List<HospitalDeviceBean>> rxObserver) {
        doRxRequest().
                getHospitalDeviceList(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
