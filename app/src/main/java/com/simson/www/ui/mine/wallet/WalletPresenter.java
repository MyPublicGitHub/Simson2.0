package com.simson.www.ui.mine.wallet;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.UserInfoModel;
import com.simson.www.ui.core.model.WalletModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.user.UserInfoContract;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;


public class WalletPresenter extends BasePresenter<WalletContract.View> implements WalletContract.Presenter {
    private WalletModel mModel;
    private WalletContract.View mView;

    WalletPresenter() {
        this.mModel = new WalletModel();
    }

    @Override
    public void showCustomerInfo() {
        mView = getView();
        RxObserver<CustomerInfoBean> observer = new RxObserver<CustomerInfoBean>(this) {

            @Override
            public void onSuccess(CustomerInfoBean mData) {
                mView.showCustomerInfo(mData);
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
        mModel.getCustomerInfo(json, observer);
        addDisposable(observer);
    }

}
