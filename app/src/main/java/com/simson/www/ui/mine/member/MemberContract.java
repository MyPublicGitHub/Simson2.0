package com.simson.www.ui.mine.order.item;

import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface OrderContract {
    interface Presenter {

        void getOrder();

    }

    interface View extends IView {
        String getPage();
        String getStatus();

        void showOrder(List<OrderBean> bean);
    }
}
