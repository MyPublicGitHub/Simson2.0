package com.simson.www.ui.mine;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.CustomerBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.MineModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

public class MinePresenter extends BasePresenter<MineContract.View> implements MineContract.Presenter {
    private MineModel mModel;
    private MineContract.View mView;

    MinePresenter() {
        this.mModel = new MineModel();
    }


    @Override
    public void getCustomer() {
        mView = getView();
        RxObserver<CustomerBean> observer = new RxObserver<CustomerBean>(this) {

            @Override
            public void onSuccess(CustomerBean mData) {
                mView.showCustomer(mData);
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
        mModel.getCustomer(json, observer);
        addDisposable(observer);
    }

}
