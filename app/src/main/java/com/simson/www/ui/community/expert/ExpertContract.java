package com.simson.www.ui.community.expert;

import com.simson.www.net.bean.community.DoctorBean;
import com.simson.www.net.bean.home.HospitalBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface ExpertContract {
    interface Presenter {
        void getDoctorList();

        void getHospitalList();
    }

    interface View extends IView {
        int getPage();

        String hospitalId();

        void showDoctorList(DoctorBean bean);

        void getHospitalList(List<HospitalBean> bean);

    }
}
