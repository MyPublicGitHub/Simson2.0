package com.simson.www.ui.mine.sign;

import com.simson.www.net.bean.mine.SignBean;
import com.simson.www.net.bean.mine.SignPageBean;
import com.simson.www.ui.core.view.IView;

public interface SignContract {
    interface Presenter {

        void signInPage();

        void signIn();

    }

    interface View extends IView {

        void signInPage(SignPageBean bean);

        void signIn(SignBean bean);
    }
}
