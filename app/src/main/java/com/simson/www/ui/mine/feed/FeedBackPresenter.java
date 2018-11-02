package com.simson.www.ui.mine.feed;


import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.IntegralDetailBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.FeedBackDetailModel;
import com.simson.www.ui.core.model.IntegralDetailModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.integral.detail.IntegralDetailContract;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.ImageUtils;
import com.simson.www.utils.SPUtils;
import com.simson.www.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FeedBackPresenter extends BasePresenter<FeedBackContract.View> implements FeedBackContract.Presenter {
    private FeedBackDetailModel mModel;
    private FeedBackContract.View mView;

    FeedBackPresenter() {
        this.mModel = new FeedBackDetailModel();
    }

    @Override
    public void feedback() {
        mView = getView();
        if (TextUtils.isEmpty(mView.mobile())){
            ToastUtils.showToast("手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(mView.content())){
            ToastUtils.showToast("请输入您的反馈");
            return;
        }

        RxBaseObserver<BaseBean> observer = new RxBaseObserver<BaseBean>(this) {
            @Override
            public void onNext(BaseBean bean) {
                //请求成功
                if (bean.result == NetConfig.REQUEST_SUCCESS) {
                    mView.feedback(bean);
                } else {
                    //失败
                    mView.showFail(bean.message);
                }
            }
        };
        Map map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId",SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("mobile", mView.mobile());
        map.put("content", mView.content());
        if (mView.pictures() != null && mView.pictures().size() > 0) {
            map.put("pictures", codeList1.toArray(new String[codeList1.size()]));
        } else {
            //map.put("pictures", "");
        }
        String json = new Gson().toJson(map);
        mModel.feedback(json, observer);
        addDisposable(observer);
    }

    List<String> codeList1;

    //开启子线程处理图片压缩转码
    public void initImage() {
        mView = getView();
        if (mView.pictures() == null || mView.pictures().size() == 0) {
            feedback();
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
            feedback();
        }
    };
}
