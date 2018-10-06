package com.simson.www.ui.home.hospital.item;

import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.net.bean.mine.CaseBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface HospitalItemContract {
    interface Presenter {

        void getHospital();

    }

    interface View extends IView {
        String getPage();
        String getCityId();

        void showHospital(List<HospitalItemBean> bean);
    }
}
