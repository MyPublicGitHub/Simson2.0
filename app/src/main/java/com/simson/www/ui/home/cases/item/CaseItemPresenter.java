package com.simson.www.ui.home.cases.item;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.CaseBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.CaseModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CaseItemPresenter extends BasePresenter<CaseItemContract.View> implements CaseItemContract.Presenter {
    private CaseModel mModel;
    private CaseItemContract.View mView;

    public CaseItemPresenter() {
        this.mModel = new CaseModel();
    }


    @Override
    public void getCase() {
        mView = getView();
        RxObserver<List<CaseBean>> observer = new RxObserver<List<CaseBean>>(this) {

            @Override
            public void onSuccess(List<CaseBean> mData) {
                mView.showCase(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("hospitalId", mView.getHospitalId());
        map.put("itemTypeId", mView.getItemTypeId());
        map.put("pageIndex", mView.getPage());
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getCase(json, observer);
        addDisposable(observer);
    }

}
