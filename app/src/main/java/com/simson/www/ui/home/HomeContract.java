package com.simson.www.ui.home;

import com.simson.www.net.bean.home.BroadcastsBean;
import com.simson.www.net.bean.home.HomeDataBean;
import com.simson.www.ui.core.view.IListDataView;

import java.util.List;

/**
 * Home协约类
 * author:
 * date: 2018/3/6
 */

public interface HomeContract {
    interface IHomePresenter {
        void getHomeList();
    }

    interface IHomeView extends IListDataView<HomeDataBean> {

        void setBannerData(List<BroadcastsBean> banner);

    }
}
