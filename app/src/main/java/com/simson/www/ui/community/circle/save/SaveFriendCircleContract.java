package com.simson.www.ui.community.circle.save;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface SaveFriendCircleContract {
    interface Presenter {
        void saveFriendsCircle();
    }

    interface View extends IView {
        String content();

        List<String> pictures();

        void saveFriendsCircle(BaseBean bean);

    }
}
