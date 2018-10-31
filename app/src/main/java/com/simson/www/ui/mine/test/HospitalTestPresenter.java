package com.simson.www.ui.mine.test;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.HospitalTestBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.HospitalTestModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HospitalTestPresenter extends BasePresenter<HospitalTestContract.View> implements HospitalTestContract.Presenter {
    private HospitalTestModel mModel;
    private HospitalTestContract.View mView;

    public HospitalTestPresenter() {
        this.mModel = new HospitalTestModel();
    }


    @Override
    public void hospitalTestingList() {
        mView = getView();
        RxObserver<List<HospitalTestBean>> observer = new RxObserver<List<HospitalTestBean>>(this) {

            @Override
            public void onSuccess(List<HospitalTestBean> mData) {
                mView.hospitalTestingList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("pageIndex", mView.pageIndex());
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.hospitalTestingList(json, observer);
        addDisposable(observer);
    }

}
