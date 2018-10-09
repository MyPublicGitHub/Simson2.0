package com.simson.www.ui.mine.wallet.log;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.TransactionRecordBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.TransactionRecordModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TransactionRecordPresenter extends BasePresenter<TransactionRecordContract.View> implements TransactionRecordContract.Presenter {
    private TransactionRecordModel mModel;
    private TransactionRecordContract.View mView;

    TransactionRecordPresenter() {
        this.mModel = new TransactionRecordModel();
    }

    @Override
    public void rechargeOrderList() {
        mView = getView();
        RxObserver<List<TransactionRecordBean>> observer = new RxObserver<List<TransactionRecordBean>>(this) {

            @Override
            public void onSuccess(List<TransactionRecordBean> mData) {
                mView.rechargeOrderList(mData);
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
        mModel.rechargeOrderList(json, observer);
        addDisposable(observer);
    }

}
