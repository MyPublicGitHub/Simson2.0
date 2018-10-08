package com.simson.www.ui.mine.set.address;

import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface AddressContract {
    interface Presenter {

        void getAddress();

    }

    interface View extends IView {
        int getPage();

        void showAddress(List<AddressBean> bean);
    }
}
