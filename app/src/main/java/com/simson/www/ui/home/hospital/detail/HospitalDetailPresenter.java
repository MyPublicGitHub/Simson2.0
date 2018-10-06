package com.simson.www.ui.home.hospital.detail;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.home.HospitalDetailBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.HospitalDetailModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;


public class HospitalDetailPresenter extends CommonPresenter<HospitalDetailContract.View> implements HospitalDetailContract.Presenter {
    private HospitalDetailModel mModel;
    private HospitalDetailContract.View mView;

    public HospitalDetailPresenter() {
        this.mModel = new HospitalDetailModel();
    }


    @Override
    public void getHospitalDetail() {
        mView = getView();
        RxObserver<HospitalDetailBean> observer = new RxObserver<HospitalDetailBean>(this) {

            @Override
            public void onSuccess(HospitalDetailBean mData) {
                mView.showHospitalDetail(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));
        map.put("hospitalId", mView.getHospitalId());
        String json = new Gson().toJson(map);
        mModel.getHospitalDetail(json, observer);
        addDisposable(observer);
    }

    @Override
    public void follow() {
        follow(mView.getFollowCustomerId(),mView.getMethod(),mView.getType());
    }

}
