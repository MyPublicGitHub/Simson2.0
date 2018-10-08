package com.simson.www.ui.mine.set.address.add;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.ui.core.model.NewAddressModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;


public class NewAddressPresenter extends BasePresenter<NewAddressContract.View> implements NewAddressContract.Presenter {
    private NewAddressModel mModel;
    private NewAddressContract.View mView;

    NewAddressPresenter() {
        this.mModel = new NewAddressModel();
    }


    @Override
    public void newAddress() {
        mView = getView();
        RxBaseObserver<BaseBean> observer = new RxBaseObserver<BaseBean>(this) {
            @Override
            public void onNext(BaseBean<BaseBean> bean) {
                if (bean.result == NetConfig.REQUEST_SUCCESS) {
                    mView.showSuccess(bean);
                } else {
                    mView.showFail(bean.message);
                }
            }

        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("consignee", mView.getConsignee());
        map.put("contactNumber", mView.getContactNumber());
        map.put("location", mView.getLocation());
        map.put("address", mView.getAddress());
        map.put("isDefault", mView.getIsDefault());
        String json = new Gson().toJson(map);
        mModel.newAddress(json, observer);
        addDisposable(observer);
    }

}
