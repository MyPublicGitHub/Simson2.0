package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.HospitalDetailBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

public class HospitalDetailModel extends BaseModel {
    public void getHospitalDetail(String json, RxObserver<HospitalDetailBean> rxObserver) {
        doRxRequest().
                getHospitalDetail(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
