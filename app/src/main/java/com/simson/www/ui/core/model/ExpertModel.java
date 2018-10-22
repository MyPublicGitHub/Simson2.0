package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.community.DoctorBean;
import com.simson.www.net.bean.home.HospitalBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class ExpertModel extends BaseModel {
    public void getDoctorList(String json, RxObserver<DoctorBean> rxObserver) {
        doRxRequest().
                getDoctorList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void getHospitalList(String json, RxObserver<List<HospitalBean>> rxObserver) {
        doRxRequest().
                getHospitalList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
