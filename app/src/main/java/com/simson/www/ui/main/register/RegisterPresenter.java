package com.simson.www.ui.main.register;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.RegisterBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.RegisterModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Login Presenter
 * author:
 * date: 2018/2/11
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.IRegisterView> implements RegisterContract.IRegisterPresenter {
    private String username, password;
    private RegisterModel registerModel;
    private RegisterContract.IRegisterView iRegisterView;

    RegisterPresenter() {
        this.registerModel = new RegisterModel();
    }

    /**
     * 帐号验证
     */
    private boolean verifyAccount() {
        username = iRegisterView.getUserName();
        password = iRegisterView.getPassword();
        if (TextUtils.isEmpty(username)) {
            ToastUtils.showToast("手机号不能为空");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showToast("密码");
            return false;
        }
        return true;
    }

    @Override
    public void register() {
        iRegisterView = getView();
        if (verifyAccount()) {
            RxObserver<RegisterBean> register = new RxObserver<RegisterBean>(this) {

                @Override
                protected void onSuccess(RegisterBean bean) {
                    ToastUtils.showToast("成功");
                }

                @Override
                protected void onFail(int errorCode, String errorMsg) {
                    iRegisterView.showFail(errorMsg);
                }
            };
            Map<String, String> map = new HashMap<>();
            map.put("mobile", iRegisterView.getUserName());
            map.put("code", iRegisterView.getCode());
            map.put("pwd", iRegisterView.getPassword());
            map.put("deviceToken", "");
            map.put("appType", "2");
            map.put("timestamp", DateUtils.getStringDate());
            Gson gson = new Gson();
            String json = gson.toJson(map);
            registerModel.register(json, register);
            addDisposable(register);
        }
    }

    @Override
    public void getCode() {
        iRegisterView = getView();
        if (TextUtils.isEmpty(iRegisterView.getUserName())) {
            ToastUtils.showToast("请输入您的手机号");
            return;
        }
        RxObserver<CodeBean> getCode = new RxObserver<CodeBean>(this) {

            @Override
            protected void onSuccess(CodeBean codeBean) {
                iRegisterView.showResult("获取验证码成功");
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                iRegisterView.showResult(errorMsg);
            }
        };
        Map<String, String> map = new HashMap<>();
        map.put("mobile", iRegisterView.getUserName());
        map.put("timestamp", DateUtils.getStringDate());
        Gson gson = new Gson();
        String json = gson.toJson(map);
        registerModel.getCode(json, getCode);
        addDisposable(getCode);
    }

}
