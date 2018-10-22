package com.simson.www.ui.community.circle.save;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.FriendsCircleBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.community.circle.FriendCircleContract;
import com.simson.www.ui.core.model.SaveFriendCircleModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;
import com.simson.www.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveFriendCirclePresenter extends BasePresenter<SaveFriendCircleContract.View> implements SaveFriendCircleContract.Presenter {
    private SaveFriendCircleModel mModel;
    private SaveFriendCircleContract.View mView;

    public SaveFriendCirclePresenter() {
        this.mModel = new SaveFriendCircleModel();
    }

    @Override
    public void saveFriendsCircle() {
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
                    mView.saveFriendsCircle(bean);
                } else {
                    mView.showFail(bean.message);
                }
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("content", mView.content());
        //map.put("pictures", "");
        String json = new Gson().toJson(map);
        mModel.saveFriendsCircle(json, observer);
        addDisposable(observer);
    }

}
