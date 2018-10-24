package com.simson.www.ui.community.expert.detail;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.community.QuestionsDetailBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.QuestionDetailModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

public class QuestionDetailPresenter extends CommonPresenter<QuestionDetailContract.View> implements QuestionDetailContract.Presenter {
    private QuestionDetailModel mModel;
    private QuestionDetailContract.View mView;

    public QuestionDetailPresenter() {
        this.mModel = new QuestionDetailModel();
    }

    @Override
    public void questionsDetail() {
        mView = getView();
        RxObserver<QuestionsDetailBean> observer = new RxObserver<QuestionsDetailBean>(this) {

            @Override
            public void onSuccess(QuestionsDetailBean mData) {
                mView.questionsDetail(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("questionsId", mView.questionsId());
        String json = new Gson().toJson(map);
        mModel.questionsDetail(json, observer);
        addDisposable(observer);
    }
}
