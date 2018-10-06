package com.simson.www.ui.home.cause;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.home.CauseListBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.CauseModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CausePresenter extends BasePresenter<CauseContract.View> implements CauseContract.Presenter {
    private CauseModel mModel;
    private CauseContract.View mView;

    CausePresenter() {
        this.mModel = new CauseModel();
    }

    @Override
    public void getCauseList() {
        mView = getView();
        RxObserver<List<CauseListBean>> observer = new RxObserver<List<CauseListBean>>(this) {

            @Override
            public void onSuccess(List<CauseListBean> mData) {
                mView.showCauseList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };
        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("pageIndex", mView.getPage());
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getCauseList(json, observer);
        addDisposable(observer);
    }
}
