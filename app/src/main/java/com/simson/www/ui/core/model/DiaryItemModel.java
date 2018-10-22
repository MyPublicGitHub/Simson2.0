package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class DiaryItemModel extends BaseModel {

    public void getDiaryList(String json, RxObserver<List<DiaryBean>> rxObserver) {
        doRxRequest().
                getDiaryList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
