package com.simson.www.ui.home.hospital.detail;

import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.bean.community.DoctorBean;
import com.simson.www.net.bean.home.HospitalDetailBean;
import com.simson.www.net.bean.home.HospitalDeviceBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface HospitalDetailContract {
    interface Presenter {
        void getHospitalDeviceList();

        void getHospitalDetail();

        void getDoctorList();

        void getDiaryList();

        void followHospital();
    }

    interface View extends IView {

        String getHospitalId();

        String getFollowCustomerId();

        String getMethod();

        void showHospitalDetail(HospitalDetailBean bean);

        //int getPage();

        void showDoctorList(DoctorBean bean);

        void showHospitalDeviceList(List<HospitalDeviceBean> bean);

        void showDiaryList(List<DiaryBean> bean);
    }
}
