package com.simson.www.ui.shop.detail.diary;


import com.google.gson.Gson;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.DetailModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.shop.detail.detail.DetailContract;
import com.simson.www.utils.DateUtils;

import java.util.HashMap;
import java.util.Map;


public class DiaryPresenter extends BasePresenter<DiaryContract.View>
        implements DiaryContract.Presenter {
    private DetailModel mModel;
    private DiaryContract.View mView;

    DiaryPresenter() {
        this.mModel = new DetailModel();
    }

    @Override
    public void getCommodityDetailPicture() {
        mView = getView();
        RxObserver<CommodityDetailBean> observer = new RxObserver<CommodityDetailBean>(this) {

            @Override
            public void onSuccess(CommodityDetailBean mData) {
                mView.showCommodityDetailPicture(mData);
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
        mModel.getCommodityDetailPicture(json, observer);
        addDisposable(observer);
    }


}
