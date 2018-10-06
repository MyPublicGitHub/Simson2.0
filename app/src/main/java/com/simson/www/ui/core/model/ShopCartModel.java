package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.ShopCartBean;
import com.simson.www.net.bean.mine.SubmitOrderBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class ShopCartModel extends BaseModel {

    public void getShopCart(String json, RxObserver<List<ShopCartBean>> rxObserver) {
        doRxRequest().
                getShopCart(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void removeShopCart(String json, RxObserver rxObserver) {
        doRxRequest().
                removeShopCart(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void updateShopCart(String json, RxBaseObserver rxObserver) {
        doRxRequest().
                updateShopCart(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void submitOrder(String json, RxObserver<SubmitOrderBean> rxObserver) {
        doRxRequest().
                submitOrder(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
