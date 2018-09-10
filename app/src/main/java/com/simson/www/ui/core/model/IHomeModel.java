package com.simson.www.ui.core.model;


import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.bean.home.HomeDataBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public interface IHomeModel {

    void getHomeBannerData(RxObserver<HomeBannerBean> rxObserver);

}
