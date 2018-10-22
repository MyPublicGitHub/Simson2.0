package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.utils.AESUtils;

public class SaveFriendCircleModel extends BaseModel {

    public void saveFriendsCircle(String json, RxBaseObserver rxObserver) {
        doRxRequest().
                saveFriendsCircle(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
