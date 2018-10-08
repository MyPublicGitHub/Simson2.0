package com.simson.www.ui.home.hospital.device;

import com.simson.www.net.bean.home.HospitalDeviceBean;
import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface DeviceContract {
    interface Presenter {

        void getHospitalDeviceList();


    }

    interface View extends IView {

        String pageIndex();

        void getHospitalDeviceList(List<HospitalDeviceBean> bean);
    }
}
