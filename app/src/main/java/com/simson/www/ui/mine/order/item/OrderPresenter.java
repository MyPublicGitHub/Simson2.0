package com.simson.www.ui.mine.order.item;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.OrderModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.order.item.OrderContract;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderPresenter extends BasePresenter<OrderContract.View> implements OrderContract.Presenter {
    private OrderModel mModel;
    private OrderContract.View mView;

    OrderPresenter() {
        this.mModel = new OrderModel();
    }


    @Override
    public void getOrder() {
        mView = getView();
        RxObserver<List<OrderBean>> observer = new RxObserver<List<OrderBean>>(this) {

            @Override
            public void onSuccess(List<OrderBean> mData) {
                mView.showOrder(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("status", mView.getStatus());
        map.put("pageIndex", mView.getPage());
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getOrder(json, observer);
        addDisposable(observer);
    }

}
