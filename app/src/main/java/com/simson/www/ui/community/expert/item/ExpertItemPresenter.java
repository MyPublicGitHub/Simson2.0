package com.simson.www.ui.community.expert.item;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.community.QuestionsBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.ExpertItemModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpertItemPresenter extends CommonPresenter<ExpertItemContract.View> implements ExpertItemContract.Presenter {
    private ExpertItemModel mModel;
    private ExpertItemContract.View mView;

    public ExpertItemPresenter() {
        this.mModel = new ExpertItemModel();
    }


    @Override
    public void getQuestionsList() {
        mView = getView();
//        if (TextUtils.isEmpty((String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""))){
//            ToastUtils.showToast("请先登录");
//            mView.goToLogin();
//            return;
//        }
        RxObserver<List<QuestionsBean>> observer = new RxObserver<List<QuestionsBean>>(this) {

            @Override
            public void onSuccess(List<QuestionsBean> mData) {
                mView.showQuestionsList(mData);
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
        map.put("doctorId", "");//医生类型
        map.put("type", mView.getType());//1推荐，2关注，空不传是全部
        map.put("pageIndex", mView.getPage() + "");
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getQuestionsList(json, observer);
        addDisposable(observer);
    }

}
