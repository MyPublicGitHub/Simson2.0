package com.simson.www.ui.home.expert.detail;

import com.simson.www.net.bean.community.PopularizationBean;
import com.simson.www.net.bean.community.QuestionsBean;
import com.simson.www.net.bean.home.DoctorDetailBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface ExpertDetailContract {
    interface Presenter {

        void getDoctorDetail();

        void getExpert();

        void getPopularizationList();

        void follow();

    }

    interface View extends IView {
        String getDoctorId();

        String getFollowCustomerId();

        String getMethod();

        String getType();

        void showDoctorDetail(DoctorDetailBean bean);

        void shoExpert(List<QuestionsBean> bean);
        void showPopularizationList(List<PopularizationBean> bean);
    }
}
