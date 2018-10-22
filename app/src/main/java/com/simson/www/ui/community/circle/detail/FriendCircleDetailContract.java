package com.simson.www.ui.community.circle.detail;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.FriendsCircleCommentBean;
import com.simson.www.net.bean.community.FriendsCircleDetailBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface FriendCircleDetailContract {
    interface Presenter {
        void getFriendsCircle();
        void fiendsCircleCommentList();
        void saveFriendsCircleComment();
    }

    interface View extends IView {
        String friendsCircleId();
        int pageIndex();
        String content();

        void getFriendsCircle(FriendsCircleDetailBean bean);
        void fiendsCircleCommentList(List<FriendsCircleCommentBean> bean);
        void saveFriendsCircleComment(BaseBean bean);

    }
}
