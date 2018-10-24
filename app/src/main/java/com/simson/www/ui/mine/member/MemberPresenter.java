package com.simson.www.ui.mine.member;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.VIPBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.MemberModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;


public class MemberPresenter extends BasePresenter<MemberContract.View> implements MemberContract.Presenter {
    private MemberModel mModel;
    private MemberContract.View mView;

    MemberPresenter() {
        this.mModel = new MemberModel();
    }


    @Override
    public void getCustomerVIP() {
        mView = getView();
        RxObserver<VIPBean> observer = new RxObserver<VIPBean>(this) {

            @Override
            public void onSuccess(VIPBean mData) {
                mView.getCustomerVIP(mData);
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
        mModel.getCustomerVIP(json, observer);
        addDisposable(observer);
    }

}
