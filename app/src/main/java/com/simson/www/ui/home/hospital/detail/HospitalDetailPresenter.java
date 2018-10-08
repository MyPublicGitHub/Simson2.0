package com.simson.www.ui.home.hospital.detail;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.bean.community.DoctorBean;
import com.simson.www.net.bean.home.HospitalDetailBean;
import com.simson.www.net.bean.home.HospitalDeviceBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.DiaryItemModel;
import com.simson.www.ui.core.model.ExpertModel;
import com.simson.www.ui.core.model.HospitalDetailModel;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HospitalDetailPresenter extends CommonPresenter<HospitalDetailContract.View> implements HospitalDetailContract.Presenter {
    private HospitalDetailModel mModel;
    private ExpertModel mDoctorModel;
    private DiaryItemModel mDiaryModel;
    private HospitalDetailContract.View mView;

    public HospitalDetailPresenter() {
        this.mModel = new HospitalDetailModel();
        this.mDoctorModel = new ExpertModel();
        this.mDiaryModel = new DiaryItemModel();
    }

    @Override
    public void getDiaryList() {
        mView = getView();
        RxObserver<List<DiaryBean>> observer = new RxObserver<List<DiaryBean>>(this) {

            @Override
            public void onSuccess(List<DiaryBean> mData) {
                mView.showDiaryList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("myCustomerId", "");//当前登录人
        map.put("itemTypeId", "");//项目类型
        map.put("hospitalId", mView.getHospitalId());//医院id
        map.put("type", "");//1推荐，2关注，空不传是全部
        map.put("search", "");
        map.put("pageIndex", "1");
        map.put("pageSize", "3");
        String json = new Gson().toJson(map);
        mDiaryModel.getDiaryList(json, observer);
        addDisposable(observer);
    }

    @Override
    public void getHospitalDeviceList() {
        mView = getView();
        RxObserver<List<HospitalDeviceBean>> observer = new RxObserver<List<HospitalDeviceBean>>(this) {

            @Override
            public void onSuccess(List<HospitalDeviceBean> mData) {
                mView.showHospitalDeviceList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("pageIndex", "1");
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getHospitalDeviceList(json, observer);
        addDisposable(observer);
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
        map.put("hospitalId", mView.getHospitalId());//医院id必填
        map.put("pageIndex", "1");
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mDoctorModel.getDoctorList(json, observer);
        addDisposable(observer);
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
    public void followHospital() {
        follow(mView.getFollowCustomerId(), mView.getMethod(), Const.FOLLOW_TYPE.HOSPITAL);
    }

}
