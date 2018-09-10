package com.simson.www.ui.home;

import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.ui.core.view.IView;


public interface HomeContract {
    interface IHomePresenter {
        void getBanner();
    }

    interface IHomeView extends IView {

        void setBannerData(HomeBannerBean bean);

    }
}
