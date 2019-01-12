package com.simson.www.ui.mine;

import com.simson.www.net.bean.mine.CustomerBasicBean;
import com.simson.www.net.bean.mine.CustomerBean;
import com.simson.www.ui.core.view.IView;

public interface MineContract {
    interface Presenter {

        void getCustomer();

        void getCustomerBasicInfo();

    }

    interface View extends IView {

        void showCustomer(CustomerBean bean);

        void getCustomerBasicInfo(CustomerBasicBean bean);
    }
}
