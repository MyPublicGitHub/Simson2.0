package com.simson.www.ui.core.model;

import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.RegisterBean;
import com.simson.www.net.callback.RxObserver;

/**
 * Created by  on 2018/2/1.
 */

public class RegisterModel extends BaseModel {

    public void register(String json, RxObserver<RegisterBean> callback) {
        doRxRequest()
                .register(json)
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }

    public void getCode(String json, RxObserver<CodeBean> callback) {
        doRxRequest()
                .getCode(json)
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }

}
