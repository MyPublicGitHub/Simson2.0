package com.simson.www.ui.shop.detail;

import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.bean.shop.CommodityDetailPraiseBean;
import com.simson.www.ui.core.view.IView;


public interface CommodityDetailContract {
    interface Presenter {
        void getCommodityDetail();

        void getCommodityDetailPicture();

        void getCommodityDetailPraise();
    }

    interface View extends IView {

        String getItemId();

        void showCommodityDetail(CommodityDetailBean bean);

        void showCommodityDetailPicture(CommodityDetailBean bean);

        void showCommodityDetailPraise(CommodityDetailPraiseBean bean);

    }
}
