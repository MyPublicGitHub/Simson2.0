package com.simson.www.ui.home;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.net.bean.home.IndexSynchysisBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.HomeModel;
import com.simson.www.ui.core.model.HospitalItemModel;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.ui.home.hospital.item.HospitalItemContract;
import com.simson.www.utils.AESUtils;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomePresenter extends CommonPresenter<HomeContract.View> implements HomeContract.Presenter, HospitalItemContract.Presenter {
    private HomeModel mModel;
    private HospitalItemModel mHospitalModel;
    private HomeContract.View mView;

    HomePresenter() {
        this.mModel = new HomeModel();
        this.mHospitalModel = new HospitalItemModel();
    }


    @Override
    public void indexSynchysis() {
        mView = getView();
        RxObserver<List<IndexSynchysisBean>> observer = new RxObserver<List<IndexSynchysisBean>>(this) {

            @Override
            public void onSuccess(List<IndexSynchysisBean> mData) {
                mView.indexSynchysis(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };
        Map map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));
        String json = new Gson().toJson(map);
        String jsonEn = AESUtils.encrypt(json);
        /*String url = "";
        try {
            url = "indexController/indexSynchysis?json=" + URLEncoder.encode(jsonEn, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        mModel.indexSynchysis(jsonEn, observer);
    }

    @Override
    public void getHospital() {
        mView = getView();
        RxObserver<List<HospitalItemBean>> observer = new RxObserver<List<HospitalItemBean>>(this) {

            @Override
            public void onSuccess(List<HospitalItemBean> mData) {
                mView.getHospital(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
//        map.put("cityId", mView.getCityId());
//        map.put("pageIndex", mView.getPage());
//        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
      /*  String url = "";
        try {
            url = URLEncoder.encode(AESUtils.encrypt(json), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        mHospitalModel.getHospital(json, observer);
        addDisposable(observer);
    }
}
