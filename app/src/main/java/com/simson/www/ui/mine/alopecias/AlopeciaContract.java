package com.simson.www.ui.mine.alopecias;

import com.simson.www.net.bean.mine.AlopeciaBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface AlopeciaContract {
    interface Presenter {

        void alopeciaTestingList();

    }

    interface View extends IView {
        int pageIndex();

        void alopeciaTestingList(List<AlopeciaBean> bean);
    }
}
