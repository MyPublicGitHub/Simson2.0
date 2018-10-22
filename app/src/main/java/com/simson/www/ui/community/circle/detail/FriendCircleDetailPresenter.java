package com.simson.www.ui.community.circle.detail;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.FriendsCircleCommentBean;
import com.simson.www.net.bean.community.FriendsCircleDetailBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.FriendCircleDetailModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;
import com.simson.www.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendCircleDetailPresenter extends BasePresenter<FriendCircleDetailContract.View> implements FriendCircleDetailContract.Presenter {
    private FriendCircleDetailModel mModel;
    private FriendCircleDetailContract.View mView;

    public FriendCircleDetailPresenter() {
        this.mModel = new FriendCircleDetailModel();
    }

    @Override
    public void saveFriendsCircleComment() {
        mView = getView();
        if (TextUtils.isEmpty(mView.content())) {
            ToastUtils.showToast("请输入您的评论内容");
            return;
        }
        if (TextUtils.isEmpty((String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""))) {
            ToastUtils.showToast("请先登录");
            return;
        }
        RxBaseObserver<BaseBean> observer = new RxBaseObserver<BaseBean>(this) {
            @Override
            public void onNext(BaseBean<BaseBean> bean) {
                if (bean.result == NetConfig.REQUEST_SUCCESS) {
                    mView.saveFriendsCircleComment(bean);
                } else {
                    mView.showFail(bean.message);
                }
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("content", mView.content());
        map.put("friendsCircleId", mView.friendsCircleId());
        String json = new Gson().toJson(map);
        mModel.saveFriendsCircleComment(json, observer);
        addDisposable(observer);
    }

    @Override
    public void fiendsCircleCommentList() {
        mView = getView();
        RxObserver<List<FriendsCircleCommentBean>> observer = new RxObserver<List<FriendsCircleCommentBean>>(this) {

            @Override
            public void onSuccess(List<FriendsCircleCommentBean> mData) {
                mView.fiendsCircleCommentList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("friendsCircleId", mView.friendsCircleId());
        map.put("pageIndex", mView.pageIndex() + "");
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.fiendsCircleCommentList(json, observer);
        addDisposable(observer);
    }

    @Override
    public void getFriendsCircle() {
        mView = getView();
        RxObserver<FriendsCircleDetailBean> observer = new RxObserver<FriendsCircleDetailBean>(this) {

            @Override
            public void onSuccess(FriendsCircleDetailBean mData) {
                mView.getFriendsCircle(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("friendsCircleId", mView.friendsCircleId());
        String json = new Gson().toJson(map);
        mModel.getFriendsCircle(json, observer);
        addDisposable(observer);
    }

}
