package com.simson.www.ui.mine.wallet;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.CustomerBasicBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.WalletModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;


public class WalletPresenter extends BasePresenter<WalletContract.View> implements WalletContract.Presenter {
    private WalletModel mModel;
    private WalletContract.View mView;

    public WalletPresenter() {
        this.mModel = new WalletModel();
    }

    @Override
    public void getCustomerBasicInfo() {
        mView = getView();
        RxObserver<CustomerBasicBean> observer = new RxObserver<CustomerBasicBean>(this) {

            @Override
            public void onSuccess(CustomerBasicBean mData) {
                mView.getCustomerBasicInfo(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        String json = new Gson().toJson(map);
        mModel.getCustomerBasicInfo(json, observer);
        addDisposable(observer);
    }

}
