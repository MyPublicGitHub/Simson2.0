package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.community.FriendsCircleCommentBean;
import com.simson.www.net.bean.community.FriendsCircleDetailBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class FriendCircleDetailModel extends BaseModel {

    public void getFriendsCircle(String json, RxObserver<FriendsCircleDetailBean> rxObserver) {
        doRxRequest().
                getFriendsCircle(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
    public void fiendsCircleCommentList(String json, RxObserver<List<FriendsCircleCommentBean>> rxObserver) {
        doRxRequest().
                fiendsCircleCommentList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
    public void saveFriendsCircleComment(String json, RxBaseObserver rxObserver) {
        doRxRequest().
                saveFriendsCircleComment(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
