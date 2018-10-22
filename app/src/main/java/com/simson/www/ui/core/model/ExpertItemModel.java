package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.community.QuestionsBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class ExpertItemModel extends BaseModel {

    public void getQuestionsList(String json, RxObserver<List<QuestionsBean>> rxObserver) {
        doRxRequest().
                getQuestionsList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }


}
