package com.simson.www.ui.home.expert.detail;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.community.PopularizationBean;
import com.simson.www.net.bean.community.QuestionsBean;
import com.simson.www.net.bean.home.DoctorDetailBean;
import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.ExpertDetailModel;
import com.simson.www.ui.core.model.ExpertItemModel;
import com.simson.www.ui.core.model.KnowledgeItemModel;
import com.simson.www.ui.core.model.OrderModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.ui.mine.order.item.OrderContract;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExpertDetailPresenter extends CommonPresenter<ExpertDetailContract.View> implements ExpertDetailContract.Presenter {
    private ExpertDetailModel mModel;
    private ExpertItemModel mModelExpert;
    private KnowledgeItemModel mModelKnow;
    private ExpertDetailContract.View mView;

    ExpertDetailPresenter() {
        mModel = new ExpertDetailModel();
        mModelExpert = new ExpertItemModel();
        mModelKnow = new KnowledgeItemModel();
    }

    @Override
    public void getPopularizationList() {
        mView = getView();
        RxObserver<List<PopularizationBean>> observer = new RxObserver<List<PopularizationBean>>(this) {

            @Override
            public void onSuccess(List<PopularizationBean> mData) {
                mView.showPopularizationList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };


        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("itemTypeId", "");//项目类型
        map.put("type", "");//1推荐，2关注，空不传是全部
        map.put("search", "");
        map.put("pageIndex", "1");
        map.put("pageSize", "5");
        String json = new Gson().toJson(map);
        mModelKnow.getPopularizationList(json, observer);
        addDisposable(observer);
    }
    @Override
    public void getDoctorDetail() {
        mView = getView();
        RxObserver<DoctorDetailBean> observer = new RxObserver<DoctorDetailBean>(this) {

            @Override
            public void onSuccess(DoctorDetailBean mData) {
                mView.showDoctorDetail(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("doctorId", mView.getDoctorId());
        String json = new Gson().toJson(map);
        mModel.getDoctorDetail(json, observer);
        addDisposable(observer);
    }

    @Override
    public void getExpert() {
        mView = getView();
        RxObserver<List<QuestionsBean>> observer = new RxObserver<List<QuestionsBean>>(this) {

            @Override
            public void onSuccess(List<QuestionsBean> mData) {
                mView.shoExpert(mData);
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
        map.put("doctorId", mView.getDoctorId());//医生类型
        map.put("type", mView.getType());//1推荐，2关注，空不传是全部
        map.put("pageIndex", "1");
        map.put("pageSize", "5");
        String json = new Gson().toJson(map);
        mModelExpert.getQuestionsList(json, observer);
        addDisposable(observer);
    }

    @Override
    public void follow() {
        follow(mView.getFollowCustomerId(),mView.getMethod(),mView.getType());
    }

}
