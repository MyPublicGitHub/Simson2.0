package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.shop.CommentBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class PraiseModel extends BaseModel {
    public void getCommodityQueryItemComment(String json, RxObserver<List<CommentBean>> rxObserver) {
        doRxRequest().
                getCommodityQueryItemComment(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
