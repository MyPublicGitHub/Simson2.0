package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.utils.AESUtils;

public class NewHospitalTestModel extends BaseModel {

    public void saveHospitalTesting(String json, RxBaseObserver rxObserver) {
        doRxRequest().
                saveHospitalTesting(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
