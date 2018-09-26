package com.simson.www.ui.mine.set.address.detail;

import com.simson.www.net.bean.mine.AddressDetailBean;
import com.simson.www.ui.core.view.IView;

public interface AddressDetailContract {
    interface Presenter {

        void getAddressDetail();

    }

    interface View extends IView {
        String getDeliveryId();

        void showAddressDetail(AddressDetailBean bean);
    }
}
