package com.simson.www.ui.core.model;

import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.LoginBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;


public class LoginModel extends BaseModel {

    public void login(String json, RxObserver<LoginBean> callback) {
        doRxRequest()
                .login(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }

    public void getCode(String json, RxObserver<CodeBean> callback) {
        doRxRequest()
                .getCode(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }

}
