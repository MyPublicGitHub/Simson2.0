package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.community.DoctorBean;
import com.simson.www.net.bean.community.QuestionsBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class ExpertModel extends BaseModel {

    public void getDoctorList(String json, RxObserver<DoctorBean> rxObserver) {
        doRxRequest().
                getDoctorList(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
