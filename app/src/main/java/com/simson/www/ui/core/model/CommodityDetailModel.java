package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.bean.shop.CommodityDetailPraiseBean;
import com.simson.www.net.callback.RxObserver;

public class CommodityDetailModel extends BaseModel {

    public void getCommodityDetail(String json, RxObserver<CommodityDetailBean> rxObserver) {
        doRxRequest().
                getCommodityDetail(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void getCommodityDetailPicture(String json, RxObserver<CommodityDetailBean> rxObserver) {
        doRxRequest().
                getCommodityDetailPicture(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void getCommodityDetailPraise(String json, RxObserver<CommodityDetailPraiseBean> rxObserver) {
        doRxRequest().
                getCommodityDetailPraise(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
