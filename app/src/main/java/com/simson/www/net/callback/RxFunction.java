package com.simson.www.net.callback;

import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * 用来处理嵌套请求
 * author:
 * date: 2018/3/7
 */

public abstract class RxFunction<T, R> implements Function<BaseBean<T>, Observable<BaseBean<R>>> {

    @Override
    public Observable<BaseBean<R>> apply(BaseBean<T> tBaseBean) throws Exception {
        if (tBaseBean.result == NetConfig.REQUEST_SUCCESS){
            return doOnNextRequest();
        }
        return null;
    }

    protected abstract Observable<BaseBean<R>> doOnNextRequest();

}
