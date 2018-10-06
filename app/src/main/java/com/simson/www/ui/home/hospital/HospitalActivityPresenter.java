package com.simson.www.ui.home.hospital;


import com.google.gson.Gson;
import com.simson.www.net.bean.home.CityListBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.HospitalModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalActivityPresenter extends BasePresenter<HospitalActivityContract.View> implements HospitalActivityContract.Presenter {
    private HospitalModel mModel;
    private HospitalActivityContract.View mView;

    HospitalActivityPresenter() {
        this.mModel = new HospitalModel();
    }

    @Override
    public void getCityList() {
        mView = getView();
        RxObserver<List<CityListBean>> mObserver = new RxObserver<List<CityListBean>>(this) {

            @Override
            public void onSuccess(List<CityListBean> mData) {
                mView.setCityList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };
        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        String json = new Gson().toJson(map);
        mModel.getCityList(json, mObserver);
        addDisposable(mObserver);
    }

}
