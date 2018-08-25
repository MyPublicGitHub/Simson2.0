package com.simson.www.net.callback;


import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.core.presenter.BasePresenter;

/**
 * RxRetrofit通用接口回调类
 */

public abstract class RxObserver<T> extends RxBaseObserver<T> {
    public RxObserver(BasePresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public void onNext(BaseBean<T> mBaseBean) {

        //请求成功
        if (mBaseBean.result == NetConfig.REQUEST_SUCCESS) {
            onSuccess(mBaseBean.data);
        } else {
            //失败
            onFail(mBaseBean.result, mBaseBean.message);
        }
    }

    protected abstract void onSuccess(T data);

    protected abstract void onFail(int errorCode, String errorMsg);

}
