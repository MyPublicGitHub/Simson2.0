package com.simson.www.ui.mine.member;

import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.net.bean.mine.VIPBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface MemberContract {
    interface Presenter {

        void getCustomerVIP();

    }

    interface View extends IView {

        void getCustomerVIP(VIPBean bean);
    }
}
