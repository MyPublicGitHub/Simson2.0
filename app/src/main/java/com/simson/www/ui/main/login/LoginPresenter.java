package com.simson.www.ui.main.login;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.simson.www.BuildConfig;
import com.simson.www.R;
import com.simson.www.application.AppContext;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.bean.main.LoginBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.LoginModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;


public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    private String username, password;
    private LoginModel mModel;
    private LoginContract.View mView;

    LoginPresenter() {
        this.mModel = new LoginModel();
    }

    /**
     * 帐号验证
     */
    private boolean verifyAccount() {
        username = mView.getUserName();
        password = mView.getPassWord();
        if (!BuildConfig.DEBUG) {
            if (TextUtils.isEmpty(username)) {
                ToastUtils.showToast("手机号不能为空");
                return false;
            }
            if (TextUtils.isEmpty(password)) {
                ToastUtils.showToast("密码不能为空");
                return false;
            }
        }
        return true;
    }

    @Override
    public void login() {
        mView = getView();
        if (verifyAccount()) {
            RxObserver<LoginBean> login = new RxObserver<LoginBean>(this) {
                @Override
                protected void onStart() {
                    mView.showLoading(AppContext.getContext().getString(R.string.loging));
                }

                @Override
                protected void onSuccess(LoginBean userBean) {
                    mModel.saveUserInfo(userBean);
                    mView.showLogin(userBean);
                }

                @Override
                protected void onFail(int errorCode, String errorMsg) {
                    mView.showFail(errorMsg);
                }
            };
            Map<String, String> map = new HashMap<>();
            map.put("mobile", mView.getUserName());
            map.put("code", mView.getPassWord());
            if (BuildConfig.DEBUG) {
                if ("".equals(mView.getUserName())) {
                    map.put("mobile", "17633717732");
                    map.put("code", "8888888888888888");
                } /*else if ("2".equals(mView.getUserName())) {
                    map.put("mobile", "13073400396");
                    map.put("code", "123456");
                }*/
            }
            map.put("deviceToken", "android");
            map.put("appType", "2");
            map.put("timestamp", DateUtils.getStringDate());
            Gson gson = new Gson();
            String json = gson.toJson(map);
            mModel.login(json, login);
            addDisposable(login);
        }
    }

    @Override
    public void getCode() {
        mView = getView();
        if (TextUtils.isEmpty(mView.getUserName())) {
            ToastUtils.showToast("请输入您的手机号");
            return;
        }
        RxObserver<CodeBean> getCode = new RxObserver<CodeBean>(this) {

            @Override
            protected void onSuccess(CodeBean codeBean) {
                mView.showCode(codeBean);
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mView.getUserName());
        map.put("timestamp", DateUtils.getStringDate());
        Gson gson = new Gson();
        String json = gson.toJson(map);
        mModel.getCode(json, getCode);
        addDisposable(getCode);
    }

}
