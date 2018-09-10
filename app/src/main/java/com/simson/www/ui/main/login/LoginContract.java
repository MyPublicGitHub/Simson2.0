package com.simson.www.ui.main.login;

import com.simson.www.ui.core.view.IView;

/**
 * Home协约类
 * author:
 * date: 2018/3/6
 */

public interface LoginContract {
    interface ILoginPresenter {
        void login();

        void getCode();
    }

    interface ILoginView extends IView {

        /**
         * 获取用户名
         *
         * @return
         */
        String getUserName();

        /**
         * 获取密码
         *
         * @return
         */
        String getPassWord();

        /**
         * 登录Result
         */
        void showResult(String msg);

    }
}
