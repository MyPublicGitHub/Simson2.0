package com.simson.www.ui.shop.detail.detail;

import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.ui.core.view.IView;


public interface DetailContract {
    interface Presenter {

        void getCommodityDetailPicture();

    }

    interface View extends IView {

        String getItemId();

        void showCommodityDetailPicture(CommodityDetailBean bean);

    }
}
