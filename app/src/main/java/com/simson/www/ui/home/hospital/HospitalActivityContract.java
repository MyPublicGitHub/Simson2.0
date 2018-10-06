package com.simson.www.ui.home.hospital;

import com.simson.www.net.bean.home.CityListBean;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface HospitalActivityContract {
    interface Presenter {
        void getCityList();
    }

    interface View extends IView {

        void setCityList(List<CityListBean> bean);

    }
}
