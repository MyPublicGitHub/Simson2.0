package com.simson.www.ui.core.model.impl;

import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.LoginBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.ILoginModel;

/**
 * Created by  on 2018/2/1.
 */

public class LoginModel extends BaseModel implements ILoginModel {

    @Override
    public void login(String json, RxObserver<LoginBean> callback) {
        doRxRequest()
                .login(json)
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }

    @Override
    public void getCode(String json, RxObserver<CodeBean> callback) {
        doRxRequest()
                .getCode(json)
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }
    @Override
    public void saveUserInfo(LoginBean userBean) {
        //加密保存用户信息和密钥
        //SPUtils.put();
    }

}
