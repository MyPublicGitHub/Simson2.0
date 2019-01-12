package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.CityListBean;
import com.simson.www.net.bean.shop.BigEventBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class HospitalFragmentModel extends BaseModel {
    public void bigEventList(String json, RxObserver<List<BigEventBean>> rxObserver) {
        doRxRequest().
                bigEventList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
