package com.simson.www.ui.main.login;

import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.LoginBean;
import com.simson.www.ui.core.view.IView;


public interface LoginContract {
    interface Presenter {
        void login();

        void getCode();
    }

    interface View extends IView {

        String getUserName();

        String getPassWord();


        void showCode(CodeBean bean);
        void showLogin(LoginBean bean);

    }
}
