package com.simson.www.ui.shop;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.ShopModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.utils.AESUtils;
import com.simson.www.utils.DateUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShopPresenter extends CommonPresenter<ShopContract.View> implements ShopContract.Presenter {
    private ShopModel mModel;
    private ShopContract.View mView;

    ShopPresenter() {
        this.mModel = new ShopModel();
    }

    @Override
    public void getShopList() {
        mView = getView();
        RxObserver<List<ShopListBean>> observer = new RxObserver<List<ShopListBean>>(this) {

            @Override
            public void onSuccess(List<ShopListBean> mData) {
                mView.setShopListResponse(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String,String> map = new HashMap();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("isPoint", "0");//1积分项目；0普通项目 必填
        map.put("itemTypeId", "");//项目类型id
        map.put("search", "");
        map.put("pageIndex", mView.getPageCommodity()+"");
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        String jsonEn = AESUtils.encrypt(json);
        /*String url = "";
        try {
            url = "itemController/itemList?json=" + URLEncoder.encode(jsonEn, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        mModel.getShopList(json, observer);
        addDisposable(observer);
    }
    public void getShopIntegralList() {
        mView = getView();
        RxObserver<List<ShopListBean>> observer = new RxObserver<List<ShopListBean>>(this) {

            @Override
            public void onSuccess(List<ShopListBean> mData) {
                mView.setShopIntegralListResponse(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map map = new HashMap();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("isPoint", "1");//1积分项目；0普通项目 必填
        map.put("itemTypeId", "");//项目类型id
        map.put("search", "");
        map.put("pageIndex", mView.getPageIntegral()+"");
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getShopList(json, observer);
        addDisposable(observer);
    }

}
