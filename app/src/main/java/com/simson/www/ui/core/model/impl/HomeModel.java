package com.simson.www.ui.core.model.impl;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.HomeDataBean;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.callback.RxFunction;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.IHomeModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeModel extends BaseModel {

    public void getHomeBannerData(RxObserver<HomeBannerBean> rxObserver) {
        doRxRequest().
                getHomeBannerData()
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
