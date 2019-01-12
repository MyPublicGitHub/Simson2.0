package com.simson.www.ui.hospital;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.shop.BigEventBean;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.HospitalFragmentModel;
import com.simson.www.ui.core.model.ShopModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HospitalPresenter extends BasePresenter<HospitalContract.View> implements HospitalContract.Presenter {
    private HospitalFragmentModel mModel;
    private HospitalContract.View mView;

    HospitalPresenter() {
        this.mModel = new HospitalFragmentModel();
    }

    @Override
    public void bigEventList() {
        mView = getView();
        RxObserver<List<BigEventBean>> observer = new RxObserver<List<BigEventBean>>(this) {

            @Override
            public void onSuccess(List<BigEventBean> mData) {
                mView.bigEventList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map map = new HashMap();
        map.put("customerId", SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));
        map.put("pageIndex", mView.pageIndex());
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.bigEventList(json, observer);
        addDisposable(observer);
    }

}
