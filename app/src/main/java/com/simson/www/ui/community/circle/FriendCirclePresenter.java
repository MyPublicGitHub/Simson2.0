package com.simson.www.ui.community.circle;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.community.FriendsCircleBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.FriendCircleModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendCirclePresenter extends BasePresenter<FriendCircleContract.View> implements FriendCircleContract.Presenter {
    private FriendCircleModel mModel;
    private FriendCircleContract.View mView;

    public FriendCirclePresenter() {
        this.mModel = new FriendCircleModel();
    }

    @Override
    public void friendsCircleList() {
        mView = getView();
        RxObserver<List<FriendsCircleBean>> observer = new RxObserver<List<FriendsCircleBean>>(this) {

            @Override
            public void onSuccess(List<FriendsCircleBean> mData) {
                mView.friendsCircleList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("pageIndex", mView.getPage() + "");
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.friendsCircleList(json, observer);
        addDisposable(observer);
    }

}
