package com.simson.www.ui.mine.follow;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.mine.FansBean;
import com.simson.www.net.bean.mine.FollowBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.FollowModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FollowPresenter extends BasePresenter<FollowContract.View> implements FollowContract.Presenter {
    private FollowModel mModel;
    private FollowContract.View mView;

    FollowPresenter() {
        this.mModel = new FollowModel();
    }


    @Override
    public void queryMyFollowList() {
        mView = getView();
        RxObserver<List<FollowBean>> observer = new RxObserver<List<FollowBean>>(this) {

            @Override
            public void onSuccess(List<FollowBean> mData) {
                mView.queryMyFollowList(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("pageIndex", mView.pageIndex());
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.queryMyFollowList(json, observer);
        addDisposable(observer);
    }

}
