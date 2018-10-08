package com.simson.www.ui.mine.collect;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.CollectModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CollectPresenter extends BasePresenter<CollectContract.View> implements CollectContract.Presenter {
    private CollectModel mModel;
    private CollectContract.View mView;

    CollectPresenter() {
        this.mModel = new CollectModel();
    }

    @Override
    public void itemCollectList() {
        mView = getView();
        RxObserver<List<ShopListBean>> observer = new RxObserver<List<ShopListBean>>(this) {

            @Override
            public void onSuccess(List<ShopListBean> mData) {
                mView.itemCollectList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("pageIndex", mView.pageIndex());
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.itemCollectList(json, observer);
        addDisposable(observer);
    }

}
