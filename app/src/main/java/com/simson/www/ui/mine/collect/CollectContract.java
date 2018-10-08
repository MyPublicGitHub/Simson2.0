package com.simson.www.ui.mine.collect;

import com.simson.www.net.bean.shop.ShopListBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface CollectContract {
    interface Presenter {

        void itemCollectList();


    }

    interface View extends IView {

        String pageIndex();

        void itemCollectList(List<ShopListBean> bean);
    }
}
