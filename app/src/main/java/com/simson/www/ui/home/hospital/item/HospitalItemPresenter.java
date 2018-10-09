package com.simson.www.ui.home.hospital.item;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.HospitalItemModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HospitalItemPresenter extends BasePresenter<HospitalItemContract.View> implements HospitalItemContract.Presenter {
    private HospitalItemModel mModel;
    private HospitalItemContract.View mView;

    public HospitalItemPresenter() {
        this.mModel = new HospitalItemModel();
    }


    @Override
    public void getHospital() {
        mView = getView();
        RxObserver<List<HospitalItemBean>> observer = new RxObserver<List<HospitalItemBean>>(this) {

            @Override
            public void onSuccess(List<HospitalItemBean> mData) {
                mView.showHospital(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("cityId", mView.getCityId());
        map.put("pageIndex", mView.getPage());
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getHospital(json, observer);
        addDisposable(observer);
    }
}
