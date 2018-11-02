package com.simson.www.ui.community.circle.save;


import android.os.Handler;
import android.os.Message;
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
import com.simson.www.utils.ImageUtils;
import com.simson.www.utils.SPUtils;
import com.simson.www.utils.ToastUtils;

import java.util.ArrayList;
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

        Map map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("content", mView.content());
        if (mView.pictures() != null && mView.pictures().size() > 0) {
            map.put("pictures", codeList1.toArray(new String[codeList1.size()]));
        } else {
            //map.put("pictures", "");
        }
        String json = new Gson().toJson(map);
        mModel.saveFriendsCircle(json, observer);
        addDisposable(observer);
    }
    List<String> codeList1;

    //开启子线程处理图片压缩转码
    public void initImage() {
        mView = getView();
        if (mView.pictures() == null || mView.pictures().size() == 0) {
            saveFriendsCircle();
            return;
        }
        new Thread(() -> {
            codeList1 = new ArrayList<>();
            for (int i = 0; i < mView.pictures().size(); i++) {
                String imgCode = ImageUtils.compressedPicture(mView.pictures().get(i));
                if (!imgCode.equals("")) {
                    codeList1.add(imgCode);
                }
            }
            Message message = new Message();
            mHandler.sendMessage(message);
        }).start();

    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            saveFriendsCircle();
        }
    };
}
