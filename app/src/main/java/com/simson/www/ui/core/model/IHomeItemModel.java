package com.simson.www.ui.core.model;


import com.simson.www.net.bean.home.HomeItemBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public interface IHomeItemModel {


    void getHomeItemData(String json,RxObserver<List<HomeItemBean>> rxObserver);

}
