package com.simson.www.net.callback;

import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 */

public abstract class RxConsumer<T> implements Consumer<BaseBean<T>> {

    @Override
    public void accept(@NonNull BaseBean<T> tBaseBean) throws Exception {
        if (tBaseBean.result == NetConfig.REQUEST_SUCCESS){
            onSuccess(tBaseBean.data);
        }else {
            onFail(tBaseBean.message);
        }
    }

    protected abstract void onFail(String errorMsg);

    protected abstract void onSuccess(T data);
}