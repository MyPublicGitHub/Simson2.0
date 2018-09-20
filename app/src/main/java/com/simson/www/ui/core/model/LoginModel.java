package com.simson.www.ui.core.model;

import com.simson.www.common.Const;
import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.LoginBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.SPUtils;


public class LoginModel extends BaseModel {

    public void login(String json, RxObserver<LoginBean> callback) {
        doRxRequest()
                .login(json)
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }

    public void getCode(String json, RxObserver<CodeBean> callback) {
        doRxRequest()
                .getCode(json)
                .compose(RxSchedulers.io_main())
                .subscribe(callback);
    }
    public void saveUserInfo(LoginBean userBean) {
        SPUtils.put(Const.USER_INFO.CUSTOMER_ID,userBean.getCustomerId());
        SPUtils.put(Const.USER_INFO.CUSTOMER_HEAD,userBean.getCustomerHead());
        SPUtils.put(Const.USER_INFO.CUSTOMER_NAME,userBean.getCustomerName());
        SPUtils.put(Const.USER_INFO.CUSTOMER_NICK_NAME,userBean.getNickName());
    }

}
