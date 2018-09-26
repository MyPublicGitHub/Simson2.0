package com.simson.www.ui.mine.set.address.edit;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.AddressModel;
import com.simson.www.ui.core.model.EditAddressModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.set.address.AddressContract;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EditAddressPresenter extends BasePresenter<EditAddressContract.View> implements EditAddressContract.Presenter {
    private EditAddressModel mModel;
    private EditAddressContract.View mView;

    EditAddressPresenter() {
        this.mModel = new EditAddressModel();
    }


    @Override
    public void editAddress() {
        mView = getView();
        RxObserver<BaseBean> observer = new RxObserver<BaseBean>(this) {

            @Override
            public void onSuccess(BaseBean mData) {
                mView.showSuccess(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("deliveryId", mView.getDeliveryId());
        map.put("consignee", mView.getConsignee());
        map.put("contactNumber", mView.getContactNumber());
        map.put("location", mView.getLocation());
        map.put("address", mView.getAddress());
        map.put("isDefault", mView.getIsDefault());
        String json = new Gson().toJson(map);
        mModel.editAddress(json, observer);
        addDisposable(observer);
    }

}
