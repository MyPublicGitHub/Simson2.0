package com.simson.www.ui.shop.detail.procedure;

import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.ui.core.view.IView;


public interface ProcedureContract {
    interface Presenter {

        void getCommoditySubscribeProcess();

    }

    interface View extends IView {

        String getItemId();

        void showCommoditySubscribeProcess(CommodityDetailBean bean);

    }
}
