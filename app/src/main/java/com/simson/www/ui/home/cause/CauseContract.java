package com.simson.www.ui.home.cause;

import com.simson.www.net.bean.home.CauseListBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface CauseContract {
    interface Presenter {

        void getCauseList();
    }

    interface View extends IView {
        String getPage();

        void showCauseList(List<CauseListBean> bean);

    }
}
