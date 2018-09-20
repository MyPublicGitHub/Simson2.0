package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class ShopModel extends BaseModel {

    public void getShopList(String json,RxObserver<List<ShopListBean>> rxObserver) {
        doRxRequest().
                getShopList(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
