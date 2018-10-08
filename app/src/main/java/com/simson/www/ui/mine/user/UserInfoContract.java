package com.simson.www.ui.mine.user;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.ui.core.view.IView;

public interface UserInfoContract {
    interface Presenter {

        void getCustomerInfo();

        void updateCustomerInfo();

    }

    interface View extends IView {

        String customerName();

        String customerHead();

        String birthday();

        String gender();

        String location();

        void showCustomerInfo(CustomerInfoBean bean);

        void showUpdateCustomerInfo(BaseBean bean);
    }
}
