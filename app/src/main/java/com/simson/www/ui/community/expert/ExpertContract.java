package com.simson.www.ui.community.expert;

import com.simson.www.net.bean.community.DoctorBean;
import com.simson.www.net.bean.community.QuestionsBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface ExpertContract {
    interface Presenter {
        void getDoctorList();
    }

    interface View extends IView {
        int getPage();

        void showDoctorList(DoctorBean bean);

    }
}
