package com.simson.www.ui.mine.cart;

import com.simson.www.net.bean.mine.ShopCartBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface ShopCartContract {
    interface Presenter {

        void getShopCart();

    }

    interface View extends IView {
        int getPage();

        void showShopCart(List<ShopCartBean> bean);
    }
}
