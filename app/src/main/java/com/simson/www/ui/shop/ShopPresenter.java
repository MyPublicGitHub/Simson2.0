package com.simson.www.ui.shop;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.ShopModel;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShopPresenter extends CommonPresenter<ShopContract.View> implements ShopContract.Presenter {
    private ShopModel mModel;
    private ShopContract.View mView;

    public ShopPresenter() {
        this.mModel = new ShopModel();
    }

    @Override
    public void getShopList(int isPoint) {
        mView = getView();
        RxObserver<List<ShopListBean>> observer = new RxObserver<List<ShopListBean>>(this) {

            @Override
            public void onSuccess(List<ShopListBean> mData) {
                if (isPoint == 0) {
                    mView.setShopListResponse(mData);
                } else {
                    mView.setShopIntegralListResponse(mData);
                }
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("isPoint", isPoint + "");//1积分项目；0普通项目 必填
        map.put("itemTypeId", mView.itemTypeId());//项目类型id
        map.put("search", mView.search());
        map.put("pageIndex", mView.getPageCommodity() + "");
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getShopList(json, observer);
        addDisposable(observer);
    }

}
