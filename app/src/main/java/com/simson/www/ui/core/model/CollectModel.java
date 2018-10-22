package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class CollectModel extends BaseModel {

    public void itemCollectList(String json, RxObserver<List<ShopListBean>> rxObserver) {
        doRxRequest().
                itemCollectList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
