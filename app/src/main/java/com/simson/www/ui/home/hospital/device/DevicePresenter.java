package com.simson.www.ui.home.hospital.device;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.home.HospitalDeviceBean;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.CollectModel;
import com.simson.www.ui.core.model.DeviceModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.collect.CollectContract;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DevicePresenter extends BasePresenter<DeviceContract.View> implements DeviceContract.Presenter {
    private DeviceModel mModel;
    private DeviceContract.View mView;

    DevicePresenter() {
        this.mModel = new DeviceModel();
    }

    @Override
    public void getHospitalDeviceList() {
        mView = getView();
        RxObserver<List<HospitalDeviceBean>> observer = new RxObserver<List<HospitalDeviceBean>>(this) {

            @Override
            public void onSuccess(List<HospitalDeviceBean> mData) {
                mView.getHospitalDeviceList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("pageIndex", mView.pageIndex());
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getHospitalDeviceList(json, observer);
        addDisposable(observer);
    }

}
