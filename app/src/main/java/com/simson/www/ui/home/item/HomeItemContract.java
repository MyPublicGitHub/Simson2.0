package com.simson.www.ui.home.item;

import com.simson.www.net.bean.home.HomeItemBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface HomeItemContract {
    interface Presenter {
        void getHomeItemData();
    }

    interface View extends IView {

        void setHomeItemData(List<HomeItemBean> bean);

        int getPage();
        String getType();
        String getItemType();

    }
}
