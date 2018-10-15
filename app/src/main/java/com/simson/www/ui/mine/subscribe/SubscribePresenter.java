package com.simson.www.ui.mine.subscribe;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.net.bean.mine.SubscribeListBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.OrderModel;
import com.simson.www.ui.core.model.SubscribeModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.order.item.OrderContract;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SubscribePresenter extends BasePresenter<SubscribeContract.View> implements SubscribeContract.Presenter {
    private SubscribeModel mModel;
    private SubscribeContract.View mView;

    SubscribePresenter() {
        this.mModel = new SubscribeModel();
    }


    @Override
    public void subscribeList() {
        mView = getView();
        RxObserver<List<SubscribeListBean>> observer = new RxObserver<List<SubscribeListBean>>(this) {

            @Override
            public void onSuccess(List<SubscribeListBean> mData) {
                mView.subscribeList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("subscribeType", mView.subscribeType());//查询条件：1项目预约；2脱发检测；空查询全部
        map.put("pageIndex", mView.pageIndex());
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.subscribeList(json, observer);
        addDisposable(observer);
    }

}
