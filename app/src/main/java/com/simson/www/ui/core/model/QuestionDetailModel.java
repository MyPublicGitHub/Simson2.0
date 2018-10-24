package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.community.QuestionsDetailBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

public class QuestionDetailModel extends BaseModel {
    public void questionsDetail(String json, RxObserver<QuestionsDetailBean> rxObserver) {
        doRxRequest().
                questionsDetail(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
