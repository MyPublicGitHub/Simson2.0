package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.callback.RxRedObserver;
import com.simson.www.utils.AESUtils;

public class VoteModel extends BaseModel {

    public void program(String url,RxRedObserver rxObserver) {
        doRxRequest().
                program(url)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void vote(String url, RxRedObserver rxObserver) {
        doRxRequest().
                vote(url)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
