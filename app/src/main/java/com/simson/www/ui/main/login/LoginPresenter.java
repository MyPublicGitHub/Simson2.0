package com.simson.www.ui.main.login;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.simson.www.R;
import com.simson.www.application.AppContext;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.LoginBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.impl.LoginModel;
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

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements LoginContract.ILoginPresenter {
    private String username, password;
    private LoginModel mLoginModel;
    private LoginContract.ILoginView mLoginView;

    LoginPresenter() {
        this.mLoginModel = new LoginModel();
    }

    /**
     * 帐号验证
     */
    private boolean verifyAccount() {
        username = mLoginView.getUserName();
        password = mLoginView.getPassWord();
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
    public void login() {
        mLoginView = getView();
        if (verifyAccount()) {
            RxObserver<LoginBean> login = new RxObserver<LoginBean>(this) {
                @Override
                protected void onStart() {
                    mLoginView.showLoading(AppContext.getContext().getString(R.string.loging));
                }

                @Override
                protected void onSuccess(LoginBean userBean) {
                    mLoginModel.saveUserInfo(userBean);
                    mLoginView.showResult(AppContext.getContext().getString(R.string.login_success));
                }

                @Override
                protected void onFail(int errorCode, String errorMsg) {
                    mLoginView.showFail(errorMsg);
                }
            };
            Map<String, String> map = new HashMap<>();
            map.put("mobile", mLoginView.getUserName());
            map.put("code", mLoginView.getPassWord());
            map.put("timestamp", DateUtils.getStringDate());
            Gson gson = new Gson();
            String json = gson.toJson(map);
            mLoginModel.login(json, login);
            addDisposable(login);
        }
    }

    @Override
    public void getCode() {
        mLoginView = getView();
        if (TextUtils.isEmpty(mLoginView.getUserName())) {
            ToastUtils.showToast("请输入您的手机号");
            return;
        }
        RxObserver<CodeBean> getCode = new RxObserver<CodeBean>(this) {

            @Override
            protected void onSuccess(CodeBean codeBean) {
                mLoginView.showResult("获取验证码成功");
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                mLoginView.showResult(errorMsg);
            }
        };
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mLoginView.getUserName());
        map.put("timestamp", DateUtils.getStringDate());
        Gson gson = new Gson();
        String json = gson.toJson(map);
        mLoginModel.getCode(json, getCode);
        addDisposable(getCode);
    }

}
