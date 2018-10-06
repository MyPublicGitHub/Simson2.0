package com.simson.www.ui.shop.detail.procedure;


import com.google.gson.Gson;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.CommodityDetailModel;
import com.simson.www.ui.core.model.ProcedureModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.shop.detail.detail.DetailContract;
import com.simson.www.utils.DateUtils;

import java.util.HashMap;
import java.util.Map;


public class ProcedurePresenter extends BasePresenter<ProcedureContract.View>
        implements ProcedureContract.Presenter {
    private ProcedureModel mModel;
    private ProcedureContract.View mView;

    ProcedurePresenter() {
        this.mModel = new ProcedureModel();
    }

    @Override
    public void getCommoditySubscribeProcess() {
        mView = getView();
        RxObserver<CommodityDetailBean> observer = new RxObserver<CommodityDetailBean>(this) {

            @Override
            public void onSuccess(CommodityDetailBean mData) {
                mView.showCommoditySubscribeProcess(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("itemId", mView.getItemId());
        String json = new Gson().toJson(map);
        mModel.getCommoditySubscribeProcess(json, observer);
        addDisposable(observer);
    }


}
