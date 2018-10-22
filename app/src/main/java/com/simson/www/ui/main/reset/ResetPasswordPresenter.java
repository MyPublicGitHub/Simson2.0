package com.simson.www.ui.main.reset;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.main.CodeBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.ResetPasswordModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;
import com.simson.www.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;


public class ResetPasswordPresenter extends BasePresenter<ResetPasswordContract.View> implements ResetPasswordContract.Presenter {
    private ResetPasswordModel mModel;
    private ResetPasswordContract.View mView;

    ResetPasswordPresenter() {
        this.mModel = new ResetPasswordModel();
    }

    @Override
    public void updateCustomerPwd() {
        mView = getView();
        if (TextUtils.isEmpty(mView.mobile())) {
            ToastUtils.showToast("手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(mView.code())) {
            ToastUtils.showToast("验证码不能为空");
            return;
        }
        if (TextUtils.isEmpty(mView.newPwd())) {
            ToastUtils.showToast("密码不能为空");
            return;
        }
        if (mView.newPwd().length() < 16) {
            ToastUtils.showToast("密码不能小于16位");
            return;
        }
        RxBaseObserver<BaseBean> observer = new RxBaseObserver<BaseBean>(this) {
            @Override
            public void onNext(BaseBean bean) {
                //请求成功
                if (bean.result == NetConfig.REQUEST_SUCCESS) {
                    mView.updateCustomerPwd(bean);
                } else {
                    //失败
                    mView.showFail(bean.message);
                }
            }
        };
        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("mobile", mView.mobile());
        map.put("code", mView.code());
        map.put("newPwd", mView.newPwd());
        String json = new Gson().toJson(map);
        mModel.updateCustomerPwd(json, observer);
        addDisposable(observer);
    }

    @Override
    public void pwdCode() {
        mView = getView();
        if (TextUtils.isEmpty(mView.mobile())) {
            ToastUtils.showToast("手机号不能为空");
            return;
        }
        RxObserver<CodeBean> observer = new RxObserver<CodeBean>(this) {

            @Override
            public void onSuccess(CodeBean mData) {
                mView.pwdCode(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("mobile", mView.mobile());
        String json = new Gson().toJson(map);
        mModel.pwdCode(json, observer);
        addDisposable(observer);
    }

}
