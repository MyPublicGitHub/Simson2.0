package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.utils.AESUtils;

public class NewQuestionModel extends BaseModel {
    public void questions(String json, RxBaseObserver rxObserver) {
        doRxRequest().
                questions(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
