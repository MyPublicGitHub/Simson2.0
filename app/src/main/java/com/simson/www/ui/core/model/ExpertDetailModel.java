package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.DoctorDetailBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

public class ExpertDetailModel extends BaseModel {

    public void getDoctorDetail(String json, RxObserver<DoctorDetailBean> rxObserver) {
        doRxRequest().
                getDoctorDetail(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
