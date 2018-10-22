package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.FollowBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class FollowModel extends BaseModel {

    public void queryMyFollowList(String json, RxObserver<List<FollowBean>> rxObserver) {
        doRxRequest().
                queryMyFollowList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
