package com.simson.www.ui.home.test;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.net.bean.home.IndexSynchysisBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.model.HomeModel;
import com.simson.www.ui.core.model.HospitalItemModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.ui.home.HomeContract;
import com.simson.www.ui.home.hospital.item.HospitalItemContract;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter {
    private HomeModel mModel;
    private TestContract.View mView;

    TestPresenter() {
        this.mModel = new HomeModel();
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
        mModel.indexSynchysis(json, observer);
        addDisposable(observer);
    }

}
