package com.simson.www.ui.community.knowledge.item;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.bean.community.PopularizationBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.community.diary.item.DiaryItemContract;
import com.simson.www.ui.core.model.DiaryItemModel;
import com.simson.www.ui.core.model.KnowledgeItemModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.interfaces.PBEKey;

public class KnowledgeItemPresenter extends CommonPresenter<KnowledgeItemContract.View> implements KnowledgeItemContract.Presenter {
    private KnowledgeItemModel mModel;
    private KnowledgeItemContract.View mView;

    public KnowledgeItemPresenter() {
        this.mModel = new KnowledgeItemModel();
    }

    @Override
    public void getPopularizationList() {
        mView = getView();
//        if (TextUtils.isEmpty((String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""))){
//            ToastUtils.showToast("请先登录");
//            mView.goToLogin();
//            return;
//        }
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
        map.put("itemTypeId",mView.itemTypeId());//项目类型
        map.put("type", mView.getType());//1推荐，2关注，空不传是全部
        map.put("search", mView.search());
        map.put("pageIndex", mView.getPage() + "");
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getPopularizationList(json, observer);
        addDisposable(observer);
    }

}
