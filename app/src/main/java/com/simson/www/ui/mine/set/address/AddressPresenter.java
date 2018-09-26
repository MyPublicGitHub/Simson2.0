package com.simson.www.ui.mine.set.address;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.net.bean.mine.CustomerBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.AddressModel;
import com.simson.www.ui.core.model.MineModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.MineContract;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddressPresenter extends BasePresenter<AddressContract.View> implements AddressContract.Presenter {
    private AddressModel mModel;
    private AddressContract.View mView;

    AddressPresenter() {
        this.mModel = new AddressModel();
    }


    @Override
    public void getAddress() {
        mView = getView();
        RxObserver<List<AddressBean>> observer = new RxObserver<List<AddressBean>>(this) {

            @Override
            public void onSuccess(List<AddressBean> mData) {
                mView.showAddress(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("pageIndex", mView.getPage()+"");
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getAddress(json, observer);
        addDisposable(observer);
    }

}
