package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.HospitalDeviceBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class DeviceModel extends BaseModel {

    public void getHospitalDeviceList(String json, RxObserver<List<HospitalDeviceBean>> rxObserver) {
        doRxRequest().
                getHospitalDeviceList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
