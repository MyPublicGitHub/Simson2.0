package com.simson.www.ui.mine.cart;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.ShopCartBean;
import com.simson.www.net.bean.mine.SubmitOrderBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface ShopCartContract {
    interface Presenter {

        void getShopCart();

        void removeShopCart();

        void updateShopCart();

        void submitOrder();

    }

    interface View extends IView {
        String getPage();

        String getItemIds();

        String getBuyNums();

        String getCartId();

        void showShopCart(List<ShopCartBean> bean);

        void showRemoveShopCart(BaseBean bean);

        void showUpdateShopCart(BaseBean bean);

        void showSubmitOrder(SubmitOrderBean bean);
    }
}
