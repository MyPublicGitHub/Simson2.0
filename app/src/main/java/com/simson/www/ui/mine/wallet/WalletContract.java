package com.simson.www.ui.mine.wallet;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.ui.core.view.IView;

public interface WalletContract {
    interface Presenter {

        void showCustomerInfo();


    }

    interface View extends IView {

        void showCustomerInfo(CustomerInfoBean bean);

    }
}
