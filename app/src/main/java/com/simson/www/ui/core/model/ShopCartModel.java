package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.ShopCartBean;
import com.simson.www.net.bean.mine.SubmitOrderBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class ShopCartModel extends BaseModel {

    public void getShopCart(String json, RxObserver<List<ShopCartBean>> rxObserver) {
        doRxRequest().
                getShopCart(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void removeShopCart(String json, RxObserver rxObserver) {
        doRxRequest().
                removeShopCart(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void updateShopCart(String json, RxBaseObserver rxObserver) {
        doRxRequest().
                updateShopCart(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void submitOrder(String json, RxObserver<SubmitOrderBean> rxObserver) {
        doRxRequest().
                submitOrder(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
