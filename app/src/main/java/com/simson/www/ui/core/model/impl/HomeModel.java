package com.simson.www.ui.core.model.impl;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.HomeDataBean;
import com.simson.www.net.bean.home.HomeHeaderBean;
import com.simson.www.net.callback.RxConsumer;
import com.simson.www.net.callback.RxFunction;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.IHomeModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author:
 * date: 2018/2/22
 */

public class HomeModel extends BaseModel implements IHomeModel {

    @Override
    public void getHomeData(final int page, RxConsumer<HomeHeaderBean> consumer, final RxObserver<List<HomeDataBean>> rxObserver) {
        doRxRequest()
                .getHomeHeader()
                .compose(RxSchedulers.io_main())
                .doOnNext(consumer)
                .observeOn(Schedulers.io())
                .flatMap(new RxFunction<HomeHeaderBean, List<HomeDataBean>>() {
                    @Override
                    protected Observable doOnNextRequest() {
                        return doRxRequest().getHomeList("1");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rxObserver);
    }


}
