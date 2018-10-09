package com.simson.www.ui.hospital;

import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface HospitalContract {
    interface Presenter {

        void getShopList();


    }

    interface View extends IView {

        void getShopList(List<ShopListBean> bean);

        String pageIndex();

    }
}
