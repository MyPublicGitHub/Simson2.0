package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.SubmitOrderBean;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.bean.shop.CommodityDetailPraiseBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;

public class DetailModel extends BaseModel {
    public void getCommodityDetailPicture(String json, RxObserver<CommodityDetailBean> rxObserver) {
        doRxRequest().
                getCommodityDetailPicture(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
