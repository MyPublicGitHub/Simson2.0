package com.simson.www.ui.mine.integral.detail;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.IntegralDetailBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.IntegralDetailModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IntegralDetailPresenter extends BasePresenter<IntegralDetailContract.View> implements IntegralDetailContract.Presenter {
    private IntegralDetailModel mModel;
    private IntegralDetailContract.View mView;

    IntegralDetailPresenter() {
        this.mModel = new IntegralDetailModel();
    }

    @Override
    public void pointList() {
        mView = getView();
        RxObserver<List<IntegralDetailBean>> observer = new RxObserver<List<IntegralDetailBean>>(this) {

            @Override
            public void onSuccess(List<IntegralDetailBean> mData) {
                mView.pointList(mData);
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
        mModel.pointList(json, observer);
        addDisposable(observer);
    }

}
