package com.simson.www.ui.community.circle;

import com.simson.www.net.bean.community.FriendsCircleBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface FriendCircleContract {
    interface Presenter {
        void friendsCircleList();
    }

    interface View extends IView {
        int getPage();

        void friendsCircleList(List<FriendsCircleBean> bean);

    }
}
