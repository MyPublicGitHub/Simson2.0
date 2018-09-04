package com.simson.www.ui.core.model;

import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.RegisterBean;
import com.simson.www.net.callback.RxObserver;


public interface IRegisterModel {

    void register(String json, RxObserver<RegisterBean> callback);
    void getCode(String json, RxObserver<CodeBean> callback);

}
