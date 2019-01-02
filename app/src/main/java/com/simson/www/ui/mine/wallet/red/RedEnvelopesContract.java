package com.simson.www.ui.mine.wallet.red;

import com.simson.www.net.bean.mine.RedEnvelopesBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface RedEnvelopesContract {
    interface Presenter {

        void redEnvelopesRecord();


    }

    interface View extends IView {

        void redEnvelopesRecord(List<RedEnvelopesBean> bean);

    }
}
