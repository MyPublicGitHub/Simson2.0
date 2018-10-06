package com.simson.www.ui.mine.cart;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.ShopCartBean;
import com.simson.www.net.bean.mine.SubmitOrderBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.ShopCartModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShopCartPresenter extends BasePresenter<ShopCartContract.View> implements ShopCartContract.Presenter {
    private ShopCartModel mModel;
    private ShopCartContract.View mView;

    ShopCartPresenter() {
        this.mModel = new ShopCartModel();
    }


    @Override
    public void getShopCart() {
        mView = getView();
        RxObserver<List<ShopCartBean>> observer = new RxObserver<List<ShopCartBean>>(this) {

            @Override
            public void onSuccess(List<ShopCartBean> mData) {
                mView.showShopCart(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("pageIndex", mView.getPage());
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getShopCart(json, observer);
        addDisposable(observer);
    }

    @Override
    public void removeShopCart() {
        mView = getView();
        RxObserver<BaseBean> observer = new RxObserver<BaseBean>(this) {

            @Override
            public void onSuccess(BaseBean mData) {
                mView.showRemoveShopCart(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail("移除失败");
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("cardId", mView.getCartId());//购物车id必填,多个逗号隔开
        String json = new Gson().toJson(map);
        mModel.removeShopCart(json, observer);
        addDisposable(observer);
    }

    @Override
    public void updateShopCart() {
        mView = getView();
        RxBaseObserver<BaseBean> mObserver = new RxBaseObserver<BaseBean>(this) {
            @Override
            public void onNext(BaseBean bean) {
                //请求成功
                if (bean.result == NetConfig.REQUEST_SUCCESS) {
                    mView.showUpdateShopCart(bean);
                } else {
                    //失败
                    //mView.showFail("");
                    LogUtils.e("修改购物车失败");
                }
            }

        };
        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("itemIds", mView.getItemIds());//itemIds：项目id多个逗号隔开必填
        map.put("buyNums", mView.getBuyNums());//buyNums：购买数量多个逗号隔开必填
        map.put("cartId", mView.getCartId());//cartId：购物车id必填
        String json = new Gson().toJson(map);
        mModel.updateShopCart(json, mObserver);
        addDisposable(mObserver);
    }

    @Override
    public void submitOrder() {
        mView = getView();
        RxObserver<SubmitOrderBean> observer = new RxObserver<SubmitOrderBean>(this) {

            @Override
            public void onSuccess(SubmitOrderBean mData) {
                mView.showSubmitOrder(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("itemIds", mView.getItemIds());//itemIds：项目id多个逗号隔开必填
        map.put("buyNums", mView.getBuyNums());//buyNums：购买数量多个逗号隔开必填
        String json = new Gson().toJson(map);
        mModel.submitOrder(json, observer);
        addDisposable(observer);
    }

}
