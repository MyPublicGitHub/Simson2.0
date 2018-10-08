package com.simson.www.ui.mine.integral.detail;

import com.simson.www.net.bean.mine.IntegralDetailBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface IntegralDetailContract {
    interface Presenter {

        void pointList();

    }

    interface View extends IView {


        String pageIndex();

        void pointList(List<IntegralDetailBean> bean);

    }
}
