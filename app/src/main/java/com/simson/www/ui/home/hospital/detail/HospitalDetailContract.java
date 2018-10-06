package com.simson.www.ui.home.hospital.detail;

import com.simson.www.net.bean.home.HospitalDetailBean;
import com.simson.www.ui.core.view.IView;

public interface HospitalDetailContract {
    interface Presenter {
        void getHospitalDetail();

        void follow();
    }

    interface View extends IView {

        String getHospitalId();

        String getFollowCustomerId();

        String getMethod();

        String getType();

        void showHospitalDetail(HospitalDetailBean bean);
    }
}
