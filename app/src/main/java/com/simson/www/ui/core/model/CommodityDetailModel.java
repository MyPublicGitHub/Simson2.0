package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.LatelyHospitalBean;
import com.simson.www.net.bean.mine.SubmitOrderBean;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.bean.shop.CommodityDetailPraiseBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

public class CommodityDetailModel extends BaseModel {
    public void getLatelyHospital(String json, RxObserver<LatelyHospitalBean> rxObserver) {
        doRxRequest().
                getLatelyHospital(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void submitOrder(String json, RxObserver<SubmitOrderBean> rxObserver) {
        doRxRequest().
                submitOrder(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void getCommodityDetail(String json, RxObserver<CommodityDetailBean> rxObserver) {
        doRxRequest().
                getCommodityDetail(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void getCommodityDetailPicture(String json, RxObserver<CommodityDetailBean> rxObserver) {
        doRxRequest().
                getCommodityDetailPicture(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void getCommodityDetailPraise(String json, RxObserver<CommodityDetailPraiseBean> rxObserver) {
        doRxRequest().
                getCommodityDetailPraise(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void saveShopCart(String json, RxBaseObserver rxObserver) {
        doRxRequest().
                saveShopCart(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
