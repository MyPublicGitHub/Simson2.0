package com.simson.www.ui.shop.detail.praise;

import com.simson.www.net.bean.shop.CommentBean;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface PraiseContract {
    interface Presenter {

        void getCommodityQueryItemComment();

    }

    interface View extends IView {

        String getItemId();

        String getPageIndex();

        void showCommodityQueryItemComment(List<CommentBean> bean);

    }
}
