package com.simson.www.ui.community.diary.detail;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.community.DiaryDetailAppendBean;
import com.simson.www.net.bean.community.DiaryDetailBean;
import com.simson.www.net.bean.community.DiaryDetailRecommendBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.DiaryDetailModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiaryDetailPresenter extends BasePresenter<DiaryDetailContract.View> implements DiaryDetailContract.Presenter {
    private DiaryDetailModel mModel;
    private DiaryDetailContract.View mView;

    DiaryDetailPresenter() {
        mModel = new DiaryDetailModel();
    }

    @Override
    public void getDiaryDetailRecommend() {
        mView = getView();
        RxObserver<List<DiaryDetailRecommendBean>> observer = new RxObserver<List<DiaryDetailRecommendBean>>(this) {

            @Override
            public void onSuccess(List<DiaryDetailRecommendBean> mData) {
                mView.showDiaryDetailRecommend(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };


        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));
        map.put("itemTypeId", mView.getItemTypeId());//日记类型
        map.put("diaryId", mView.getId());

        String json = new Gson().toJson(map);
        mModel.getDiaryDetailRecommend(json, observer);
        addDisposable(observer);
    }

    @Override
    public void getDiaryDetail() {
        mView = getView();
        RxObserver<DiaryDetailBean> observer = new RxObserver<DiaryDetailBean>(this) {

            @Override
            public void onSuccess(DiaryDetailBean mData) {
                mView.showDiaryDetail(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };


        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));
        map.put("diaryId", mView.getId());

        String json = new Gson().toJson(map);
        mModel.getDiaryDetail(json, observer);
        addDisposable(observer);
    }

    @Override
    public void getDiaryDetailAppend() {
        mView = getView();
        RxObserver<List<DiaryDetailAppendBean>> observer = new RxObserver<List<DiaryDetailAppendBean>>(this) {

            @Override
            public void onSuccess(List<DiaryDetailAppendBean> mData) {
                mView.showDiaryDetailAppend(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };


        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("diaryId", mView.getId());
        map.put("px", mView.getPX());
        String json = new Gson().toJson(map);
        mModel.getDiaryDetailAppend(json, observer);
        addDisposable(observer);
    }

}
