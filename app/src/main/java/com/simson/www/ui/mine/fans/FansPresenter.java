package com.simson.www.ui.mine.fans;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.FansBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.FansModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FansPresenter extends BasePresenter<FansContract.View> implements FansContract.Presenter {
    private FansModel mModel;
    private FansContract.View mView;

    FansPresenter() {
        this.mModel = new FansModel();
    }


    @Override
    public void queryMyFansList() {
        mView = getView();
        RxObserver<List<FansBean>> observer = new RxObserver<List<FansBean>>(this) {

            @Override
            public void onSuccess(List<FansBean> mData) {
                mView.queryMyFansList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("pageIndex", mView.pageIndex());
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.queryMyFansList(json, observer);
        addDisposable(observer);
    }

}
