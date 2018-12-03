package com.simson.www.net;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author:
 * date: 2018/1/30
 */

public class RxSchedulers {

    /**
     * 指定被观察者为io线程
     * 观察者为主线程
     */

    public static ObservableTransformer io_main() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
