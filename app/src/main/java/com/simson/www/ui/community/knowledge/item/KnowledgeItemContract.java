package com.simson.www.ui.community.knowledge.item;

import com.simson.www.net.bean.community.PopularizationBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface KnowledgeItemContract {
    interface Presenter {
        void getPopularizationList();
    }

    interface View extends IView {
        int getPage();

        String getType();

        String itemTypeId();

        String search();

        void showPopularizationList(List<PopularizationBean> bean);

    }
}
