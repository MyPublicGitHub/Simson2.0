package com.simson.www.ui.community.expert.save;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.ui.core.model.NewQuestionModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.ImageUtils;
import com.simson.www.utils.SPUtils;
import com.simson.www.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewQuestionPresenter extends BasePresenter<NewQuestionContract.View> implements NewQuestionContract.Presenter {
    private NewQuestionModel mModel;
    private NewQuestionContract.View mView;

    public NewQuestionPresenter() {
        this.mModel = new NewQuestionModel();
    }

    @Override
    public void questions() {
        mView = getView();
        if (TextUtils.isEmpty((String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""))) {
            ToastUtils.showToast("请先登录");
            return;
        }
        if (TextUtils.isEmpty(mView.content())) {
            ToastUtils.showToast("请输入您的提问");
            return;
        }
        RxBaseObserver<BaseBean> observer = new RxBaseObserver<BaseBean>(this) {
            @Override
            public void onNext(BaseBean bean) {
                if (bean.result == NetConfig.REQUEST_SUCCESS) {
                    mView.questions(bean);
                } else {
                    mView.showFail(bean.message);
                }
            }

        };

        Map map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("content", mView.content());
        map.put("is_display", mView.is_display());
        if (mView.pictures().size() == 0) {
            //map.put("pictures", "");
        } else {
            //map.put("pictures", "");
        }
        String json = new Gson().toJson(map);
        mModel.questions(json, observer);
        addDisposable(observer);
    }

    String[] codeList;
    List<String> codeList1;

    //开启子线程处理图片压缩转码
    public void initImage() {
        mView = getView();
        if (mView.pictures() == null) return;
        //mView.showLoading("图片处理中...");
        new Thread(() -> {
            codeList = new String[4];
            codeList1 = new ArrayList<>();
            for (int i = 0; i < mView.pictures().size(); i++) {
                String imgCode = ImageUtils.compressedPicture(mView.pictures().get(i));
                if (!imgCode.equals("")) {
                    codeList[i] = imgCode;
                    codeList1.add(imgCode);
                }
            }
            //mView.hideLoading();
            questions();
        }).start();
    }
}
