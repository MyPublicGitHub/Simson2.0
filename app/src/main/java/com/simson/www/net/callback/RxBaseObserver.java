package com.simson.www.net.callback;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.simson.www.R;
import com.simson.www.application.AppContext;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.core.view.IView;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.NetworkUtils;
import com.simson.www.utils.ToastUtils;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;


/**
 * RxRetrofit请求回调基类
 */

public abstract class RxBaseObserver<T> extends DisposableObserver<BaseBean<T>> {

    protected IView view;

    public RxBaseObserver(BasePresenter mPresenter) {
        this.view = mPresenter.getView();
    }


    @Override
    protected void onStart() {
        //显示loading
        if (!NetworkUtils.isAvailable(AppContext.getContext())) {
            ToastUtils.showToast("网络异常，请检查您的网络连接");
            return;
        }
        super.onStart();
        showLoading();
    }

    public void showLoading() {
        view.showLoading("");
    }

    @Override
    public void onError(Throwable e) {
        //隐藏loading
        hideLoading();
        if (!NetworkUtils.isAvailable(AppContext.getContext())) {
            ToastUtils.showToast("网络异常，请检查您的网络连接");
            return;
        }
        //处理异常
        dealException(AppContext.getContext(), e);
    }

    @Override
    public void onComplete() {
        hideLoading();
    }

    private void hideLoading() {
        if (null != view)
            this.view.hideLoading();
    }

    /**
     * 处理异常错误
     *
     * @param t
     */
    void dealException(Context context, Throwable t) {
        LogUtils.e("Throwable:" + t.getMessage());
        if (t instanceof ConnectException || t instanceof UnknownHostException) {
            //连接错误
            onException(NetConfig.CONNECT_ERROR, context);
        } else if (t instanceof InterruptedException) {
            //连接超时
            onException(NetConfig.CONNECT_TIMEOUT, context);
        } else if (t instanceof JsonParseException
                || t instanceof JSONException
                || t instanceof ParseException) {
            //解析错误
            //onException(NetConfig.PARSE_ERROR, context);
            //onException(NetConfig.PARSE_ERROR, context);
        } else if (t instanceof SocketTimeoutException) {
            //请求超时
            onException(NetConfig.REQUEST_TIMEOUT, context);
        } else if (t instanceof UnknownError) {
            //未知错误
            onException(NetConfig.UNKNOWN_ERROR, context);
        } else {
            //未知错误
            onException(NetConfig.UNKNOWN_ERROR, context);
        }
    }


    void onException(int errorCode, Context context) {
        LogUtils.e("-------------http错误代码：" + errorCode);
        switch (errorCode) {
            case NetConfig.CONNECT_ERROR:
                ToastUtils.showToast(context, R.string.connect_error);
                break;
            case NetConfig.CONNECT_TIMEOUT:
                ToastUtils.showToast(context, R.string.connect_timeout);
                break;
            case NetConfig.PARSE_ERROR:
                ToastUtils.showToast(context, R.string.parse_error);
                break;
            case NetConfig.REQUEST_TIMEOUT:
                ToastUtils.showToast(context, R.string.request_timeout);
                break;
            case NetConfig.UNKNOWN_ERROR:
                ToastUtils.showToast(context, R.string.unknown_error);
                break;
        }
    }
}
