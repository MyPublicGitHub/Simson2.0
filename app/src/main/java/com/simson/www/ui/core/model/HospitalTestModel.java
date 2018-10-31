package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.HospitalTestBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class HospitalTestModel extends BaseModel {

    public void hospitalTestingList(String json, RxObserver<List<HospitalTestBean>> rxObserver) {
        doRxRequest().
                hospitalTestingList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
