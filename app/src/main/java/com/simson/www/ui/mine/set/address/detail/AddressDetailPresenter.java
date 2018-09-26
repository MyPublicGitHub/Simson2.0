package com.simson.www.ui.mine.set.address.detail;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.net.bean.mine.AddressDetailBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.AddressDetailModel;
import com.simson.www.ui.core.model.AddressModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.set.address.AddressContract;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddressDetailPresenter extends BasePresenter<AddressDetailContract.View> implements AddressDetailContract.Presenter {
    private AddressDetailModel mModel;
    private AddressDetailContract.View mView;

    AddressDetailPresenter() {
        this.mModel = new AddressDetailModel();
    }


    @Override
    public void getAddressDetail() {
        mView = getView();
        RxObserver<AddressDetailBean> observer = new RxObserver<AddressDetailBean>(this) {

            @Override
            public void onSuccess(AddressDetailBean mData) {
                mView.showAddressDetail(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("deliveryId", mView.getDeliveryId());
        String json = new Gson().toJson(map);
        mModel.getAddressDetail(json, observer);
        addDisposable(observer);
    }

}
