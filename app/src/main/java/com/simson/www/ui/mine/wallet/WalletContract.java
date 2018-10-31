package com.simson.www.ui.mine.wallet;

import com.simson.www.net.bean.mine.CustomerBasicBean;
import com.simson.www.ui.core.view.IView;

public interface WalletContract {
    interface Presenter {

        void getCustomerBasicInfo();


    }

    interface View extends IView {

        void getCustomerBasicInfo(CustomerBasicBean bean);

    }
}
