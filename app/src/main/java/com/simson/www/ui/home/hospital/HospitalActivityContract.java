package com.simson.www.ui.home.hospital;

import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.ui.core.view.IView;


public interface HospitalActivityContract {
    interface Presenter {
        void getBanner();
    }

    interface View extends IView {

        void setBannerData(HomeBannerBean bean);

    }
}
