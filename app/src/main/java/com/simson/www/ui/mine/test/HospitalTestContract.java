package com.simson.www.ui.mine.test;

import com.simson.www.net.bean.mine.HospitalTestBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface HospitalTestContract {
    interface Presenter {

        void hospitalTestingList();

    }

    interface View extends IView {

        String pageIndex();

        void hospitalTestingList(List<HospitalTestBean> bean);
    }
}
