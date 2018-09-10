package com.simson.www.ui.main.register;

import com.simson.www.ui.core.view.IView;

/**
 * author:
 * date: 2018/3/6
 */

public interface RegisterContract {
    interface IRegisterPresenter {
        void register();

        void getCode();
    }

    interface IRegisterView extends IView {

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
        String getPassword();

        String getCode();
        /**
         * 登录Result
         */
        void showResult(String msg);

    }
}
