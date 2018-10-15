package com.simson.www.ui.home;

import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.ui.core.view.IView;


public interface HomeContract {
    interface Presenter {
        void getBanner();
        void indexSynchysis();
    }

    interface View extends IView {

        void setBannerData(HomeBannerBean bean);

    }
}
