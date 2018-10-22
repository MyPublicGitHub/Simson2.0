package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.community.FriendsCircleBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class FriendCircleModel extends BaseModel {

    public void friendsCircleList(String json, RxObserver<List<FriendsCircleBean>> rxObserver) {
        doRxRequest().
                friendsCircleList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
