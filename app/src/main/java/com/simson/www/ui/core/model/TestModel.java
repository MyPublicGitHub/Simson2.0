package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.AlopeciaTestBean;
import com.simson.www.net.bean.home.QuestionBean;
import com.simson.www.net.callback.RxObserver;

public class TestModel extends BaseModel {

    public void getQuestion(String json, RxObserver<QuestionBean> rxObserver) {
        doRxRequest().
                getQuestion(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void saveAlopeciaTesting(String json, RxObserver<AlopeciaTestBean> rxObserver) {
        doRxRequest().
                saveAlopeciaTesting(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
