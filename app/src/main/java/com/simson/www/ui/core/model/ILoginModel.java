package com.simson.www.ui.core.model;

import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.LoginBean;
import com.simson.www.net.callback.RxObserver;

/**
 * 登录业务接口
 */

public interface ILoginModel {
    /**
     * 登录
     */
    void login(String json, RxObserver<LoginBean> callback);

    void getCode(String json, RxObserver<CodeBean> callback);


    /**
     * 保存用户信息
     *
     * @param userBean
     */
    void saveUserInfo(LoginBean userBean);

}
