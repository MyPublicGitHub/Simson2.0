package com.simson.www.ui.mine.cart;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.ShopCartBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.ShopCartModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
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
        map.put("pageIndex", mView.getPage() + "");
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getShopCart(json, observer);
        addDisposable(observer);
    }

}
