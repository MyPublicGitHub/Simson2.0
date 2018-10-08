package com.simson.www.ui.mine.user;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.UserInfoModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;


public class UserInfoPresenter extends BasePresenter<UserInfoContract.View> implements UserInfoContract.Presenter {
    private UserInfoModel mModel;
    private UserInfoContract.View mView;

    UserInfoPresenter() {
        this.mModel = new UserInfoModel();
    }

    @Override
    public void updateCustomerInfo() {
        mView = getView();
        RxBaseObserver<BaseBean> observer = new RxBaseObserver<BaseBean>(this) {
            @Override
            public void onNext(BaseBean bean) {
                //请求成功
                if (bean.result == NetConfig.REQUEST_SUCCESS) {
                    mView.showUpdateCustomerInfo(bean);
                } else {
                    //失败
                    mView.showFail(bean.message);
                }
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("customerName", mView.customerName());
        map.put("customerHead", mView.customerHead());
        map.put("gender", mView.gender());
        map.put("birthday", mView.birthday());
        map.put("location", mView.location());
        String json = new Gson().toJson(map);
        LogUtils.e(json);
        mModel.updateCustomerInfo(json, observer);
        addDisposable(observer);
    }
//    private String jsonFormat(Map<String,String> map){
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }
//    }
    @Override
    public void getCustomerInfo() {
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
