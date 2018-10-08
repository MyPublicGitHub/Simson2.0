package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.mine.CustomerInfoBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;

public class ResetPasswordModel extends BaseModel {

    public void pwdCode(String json,RxObserver<CodeBean> rxObserver) {
        doRxRequest().
                pwdCode(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
    public void updateCustomerPwd(String json,RxBaseObserver rxObserver) {
        doRxRequest().
                updateCustomerPwd(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
