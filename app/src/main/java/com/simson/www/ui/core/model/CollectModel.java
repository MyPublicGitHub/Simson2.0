package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.ItemCollectBean;
import com.simson.www.net.bean.mine.SignBean;
import com.simson.www.net.bean.mine.SignPageBean;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class CollectModel extends BaseModel {

    public void itemCollectList(String json,RxObserver<List<ShopListBean>> rxObserver) {
        doRxRequest().
                itemCollectList(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
