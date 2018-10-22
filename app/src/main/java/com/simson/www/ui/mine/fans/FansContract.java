package com.simson.www.ui.mine.fans;

import com.simson.www.net.bean.mine.FansBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface FansContract {
    interface Presenter {

        void queryMyFansList();

    }

    interface View extends IView {
        int pageIndex();

        void queryMyFansList(List<FansBean> bean);
    }
}
