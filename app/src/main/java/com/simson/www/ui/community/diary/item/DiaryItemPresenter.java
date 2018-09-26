package com.simson.www.ui.community.diary.item;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.DiaryItemModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiaryItemPresenter extends BasePresenter<DiaryItemContract.View> implements DiaryItemContract.Presenter {
    private DiaryItemModel mModel;
    private DiaryItemContract.View mView;

    public DiaryItemPresenter() {
        this.mModel = new DiaryItemModel();
    }

    @Override
    public void getDiaryList() {
        mView = getView();
//        if (TextUtils.isEmpty((String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""))){
//            ToastUtils.showToast("请先登录");
//            mView.goToLogin();
//            return;
//        }
        RxObserver<List<DiaryBean>> observer = new RxObserver<List<DiaryBean>>(this) {

            @Override
            public void onSuccess(List<DiaryBean> mData) {
                mView.showDiaryList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("myCustomerId", "");//当前登录人
        map.put("itemTypeId", "");//项目类型
        map.put("hospitalId", "");//医院id
        map.put("type", mView.getType());//1推荐，2关注，空不传是全部
        map.put("search", "");
        map.put("pageIndex", mView.getPage() + "");
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getDiaryList(json, observer);
        addDisposable(observer);
    }

    public void getMyDiaryList() {
        mView = getView();
//        if (TextUtils.isEmpty((String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""))){
//            ToastUtils.showToast("请先登录");
//            mView.goToLogin();
//            return;
//        }
        RxObserver<List<DiaryBean>> observer = new RxObserver<List<DiaryBean>>(this) {

            @Override
            public void onSuccess(List<DiaryBean> mData) {
                mView.showDiaryList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("myCustomerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("itemTypeId", "");//项目类型
        map.put("hospitalId", "");//医院id
        map.put("type", mView.getType());//1推荐，2关注，空不传是全部
        map.put("search", "");
        map.put("pageIndex", mView.getPage() + "");
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getDiaryList(json, observer);
        addDisposable(observer);
    }

}
