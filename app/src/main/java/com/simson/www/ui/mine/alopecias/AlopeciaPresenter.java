package com.simson.www.ui.mine.alopecias;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.AlopeciaBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.AlopeciaModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlopeciaPresenter extends BasePresenter<AlopeciaContract.View> implements AlopeciaContract.Presenter {
    private AlopeciaModel mModel;
    private AlopeciaContract.View mView;

    AlopeciaPresenter() {
        this.mModel = new AlopeciaModel();
    }


    @Override
    public void alopeciaTestingList() {
        mView = getView();
        RxObserver<List<AlopeciaBean>> observer = new RxObserver<List<AlopeciaBean>>(this) {

            @Override
            public void onSuccess(List<AlopeciaBean> mData) {
                mView.alopeciaTestingList(mData);
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
        mModel.alopeciaTestingList(json, observer);
        addDisposable(observer);
    }

}
