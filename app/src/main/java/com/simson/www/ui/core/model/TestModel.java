package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.home.AlopeciaTestBean;
import com.simson.www.net.bean.home.QuestionBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

public class TestModel extends BaseModel {

    public void getQuestion(String json, RxObserver<QuestionBean> rxObserver) {
        doRxRequest().
                getQuestion(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void saveAlopeciaTesting(String json, RxObserver<AlopeciaTestBean> rxObserver) {
        doRxRequest().
                saveAlopeciaTesting(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
