package com.simson.www.ui.home.test;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.home.AlopeciaTestBean;
import com.simson.www.net.bean.home.QuestionBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.TestModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;


public class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter {
    private TestModel mModel;
    private TestContract.View mView;

    TestPresenter() {
        this.mModel = new TestModel();
    }

    @Override
    public void getQuestion() {
        mView = getView();
        RxObserver<QuestionBean> observer = new RxObserver<QuestionBean>(this) {

            @Override
            public void onSuccess(QuestionBean mData) {
                mView.getQuestion(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };
        Map map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));
        map.put("optionId", mView.optionId());
        map.put("correspondingId", mView.correspondingId());
        map.put("questionnaireId", mView.questionnaireId());
        String json = new Gson().toJson(map);
        mModel.getQuestion(json, observer);
        addDisposable(observer);
    }

    @Override
    public void saveAlopeciaTesting() {
        mView = getView();
        RxObserver<AlopeciaTestBean> observer = new RxObserver<AlopeciaTestBean>(this) {

            @Override
            public void onSuccess(AlopeciaTestBean mData) {
                mView.saveAlopeciaTesting(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };


        Map map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));
        map.put("deviceToken", mView.deviceToken());//必填一个
        map.put("questions", mView.questions());//问题id数组
        map.put("options", mView.options());//选项id数组
        map.put("pictures", mView.pictures());//图片数组
        String json = new Gson().toJson(map);
        mModel.saveAlopeciaTesting(json, observer);
        addDisposable(observer);
    }

}
