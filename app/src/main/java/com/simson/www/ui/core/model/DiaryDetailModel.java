package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.community.DiaryDetailAppendBean;
import com.simson.www.net.bean.community.DiaryDetailBean;
import com.simson.www.net.bean.community.DiaryDetailRecommendBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class DiaryDetailModel extends BaseModel {

    public void getDiaryDetail(String json, RxObserver<DiaryDetailBean> rxObserver) {
        doRxRequest().
                getDiaryDetail(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void getDiaryDetailAppend(String json, RxObserver<List<DiaryDetailAppendBean>> rxObserver) {
        doRxRequest().
                getDiaryDetailAppend(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    public void getDiaryDetailRecommend(String json, RxObserver<List<DiaryDetailRecommendBean>> rxObserver) {
        doRxRequest().
                getDiaryDetailRecommend(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
