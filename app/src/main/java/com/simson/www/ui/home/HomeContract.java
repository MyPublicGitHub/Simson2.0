package com.simson.www.ui.home;

import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.net.bean.home.IndexSynchysisBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface HomeContract {
    interface Presenter {
        void indexSynchysis();

        void getHospital();
    }

    interface View extends IView {
        void indexSynchysis(List<IndexSynchysisBean> bean);

        void getHospital(List<HospitalItemBean> bean);

    }
}
