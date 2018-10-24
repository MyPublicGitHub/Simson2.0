package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.TechnologyBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class TechnologyModel extends BaseModel {
    public void getPlantingTechnology(String json, RxObserver<List<TechnologyBean>> rxObserver) {
        doRxRequest().
                getPlantingTechnology(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
