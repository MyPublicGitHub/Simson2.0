package com.simson.www.ui.hospital;

import com.simson.www.net.bean.shop.BigEventBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface HospitalContract {
    interface Presenter {

        void bigEventList();


    }

    interface View extends IView {

        void bigEventList(List<BigEventBean> bean);

        String pageIndex();

    }
}
