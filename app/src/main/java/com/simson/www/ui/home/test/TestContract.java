package com.simson.www.ui.home.test;

import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.net.bean.home.IndexSynchysisBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface TestContract {
    interface Presenter {
        void indexSynchysis();

    }

    interface View extends IView {
        void indexSynchysis(List<IndexSynchysisBean> bean);

    }
}
