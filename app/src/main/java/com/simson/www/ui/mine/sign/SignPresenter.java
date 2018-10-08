package com.simson.www.ui.mine.sign;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.net.bean.mine.SignBean;
import com.simson.www.net.bean.mine.SignPageBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.SignModel;
import com.simson.www.ui.core.model.UserInfoModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.user.UserInfoContract;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;


public class SignPresenter extends BasePresenter<SignContract.View> implements SignContract.Presenter {
    private SignModel mModel;
    private SignContract.View mView;

    SignPresenter() {
        this.mModel = new SignModel();
    }

    @Override
    public void signInPage() {
        mView = getView();
        RxObserver<SignPageBean> observer = new RxObserver<SignPageBean>(this) {

            @Override
            public void onSuccess(SignPageBean mData) {
                mView.signInPage(mData);
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
        mModel.signInPage(json, observer);
        addDisposable(observer);
    }

    @Override
    public void signIn() {
        mView = getView();
        RxObserver<SignBean> observer = new RxObserver<SignBean>(this) {

            @Override
            public void onSuccess(SignBean mData) {
                mView.signIn(mData);
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
        mModel.signIn(json, observer);
        addDisposable(observer);
    }

}
