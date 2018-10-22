package com.simson.www.ui.mine.follow;

import com.simson.www.net.bean.mine.FollowBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface FollowContract {
    interface Presenter {

        void queryMyFollowList();

    }

    interface View extends IView {
        int pageIndex();

        void queryMyFollowList(List<FollowBean> bean);
    }
}
