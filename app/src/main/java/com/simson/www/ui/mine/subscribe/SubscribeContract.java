package com.simson.www.ui.mine.subscribe;

import com.simson.www.net.bean.mine.SubscribeListBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface SubscribeContract {
    interface Presenter {

        void subscribeList();

    }

    interface View extends IView {

        String subscribeType();

        String pageIndex();

        void subscribeList(List<SubscribeListBean> bean);
    }
}
