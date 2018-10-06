package com.simson.www.ui.shop.detail;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.SubmitOrderBean;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.bean.shop.CommodityDetailPraiseBean;
import com.simson.www.ui.core.view.IView;


public interface CommodityDetailContract {
    interface Presenter {
        void getCommodityDetail();

        void getCommodityDetailPicture();

        void getCommodityDetailPraise();

        void saveShopCart();

        void submitOrder();

        void collect();
    }

    interface View extends IView {

        String getItemId();

        String getMethod();

        String getType();

        String getBuyNum();

        void showCommodityDetail(CommodityDetailBean bean);

        void showCommodityDetailPicture(CommodityDetailBean bean);

        void showCommodityDetailPraise(CommodityDetailPraiseBean bean);

        void showSaveShopCart(BaseBean bean);

        void showSubmitOrder(SubmitOrderBean bean);

    }
}
