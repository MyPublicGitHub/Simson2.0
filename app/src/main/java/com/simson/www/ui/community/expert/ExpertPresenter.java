package com.simson.www.ui.community.expert;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.community.DoctorBean;
import com.simson.www.net.bean.home.HospitalBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.ExpertModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpertPresenter extends BasePresenter<ExpertContract.View> implements ExpertContract.Presenter {
    private ExpertModel mModel;
    private ExpertContract.View mView;

    public ExpertPresenter() {
        this.mModel = new ExpertModel();
    }

    @Override
    public void getDoctorList() {
        mView = getView();
        RxObserver<DoctorBean> observer = new RxObserver<DoctorBean>(this) {

            @Override
            public void onSuccess(DoctorBean mData) {
                mView.showDoctorList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("hospitalId", mView.hospitalId());//医院id必填
        map.put("pageIndex", mView.getPage() + "");
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getDoctorList(json, observer);
        addDisposable(observer);
    }

    @Override
    public void getHospitalList() {
        mView = getView();
        RxObserver<List<HospitalBean>> observer = new RxObserver<List<HospitalBean>>(this) {

            @Override
            public void onSuccess(List<HospitalBean> mData) {
                mView.getHospitalList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        String json = new Gson().toJson(map);
        mModel.getHospitalList(json, observer);
        addDisposable(observer);
    }

}
