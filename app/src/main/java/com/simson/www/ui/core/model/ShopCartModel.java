package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.net.bean.mine.ShopCartBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class ShopCartModel extends BaseModel {

    public void getShopCart(String json,RxObserver<List<ShopCartBean>> rxObserver) {
        doRxRequest().
                getShopCart(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
