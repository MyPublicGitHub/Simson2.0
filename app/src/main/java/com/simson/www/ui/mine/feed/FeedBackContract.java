package com.simson.www.ui.mine.feed;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface FeedBackContract {
    interface Presenter {

        void feedback();

    }

    interface View extends IView {


        String content();

        String mobile();

        List<String> pictures();

        void feedback(BaseBean bean);

    }
}
