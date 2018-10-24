package com.simson.www.ui.shop;

import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface ShopContract {
    interface Presenter {
        void getShopList(int isPoint);
    }

    interface View extends IView {
        int getPageCommodity();

        String itemTypeId();
        String search();
        int getPageIntegral();
        void setShopListResponse(List<ShopListBean> bean);
        void setShopIntegralListResponse(List<ShopListBean> bean);

    }
}
