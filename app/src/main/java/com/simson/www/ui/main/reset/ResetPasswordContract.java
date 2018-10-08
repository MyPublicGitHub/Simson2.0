package com.simson.www.ui.main.reset;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.ui.core.view.IView;

public interface ResetPasswordContract {
    interface Presenter {

        void pwdCode();

        void updateCustomerPwd();

    }

    interface View extends IView {

        String newPwd();

        String code();

        String mobile();

        void pwdCode(CodeBean bean);

        void updateCustomerPwd(BaseBean bean);
    }
}
