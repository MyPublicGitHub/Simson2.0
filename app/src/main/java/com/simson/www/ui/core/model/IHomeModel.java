package com.simson.www.ui.core.model;


import com.simson.www.net.bean.home.HomeDataBean;
import com.simson.www.net.bean.home.HomeHeaderBean;
import com.simson.www.net.callback.RxConsumer;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

/**
 * 首页业务接口
 * author:
 * date: 2018/2/22
 */

public interface IHomeModel {
    /**
     * 获取首页文章列表和Banner
     *
     * @param consumer
     * @param rxObserver
     */
    void getHomeData(int page, RxConsumer<HomeHeaderBean> consumer, RxObserver<List<HomeDataBean>> rxObserver);

}
