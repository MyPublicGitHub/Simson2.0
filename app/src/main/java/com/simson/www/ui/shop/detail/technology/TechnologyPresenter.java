package com.simson.www.ui.shop.detail.technology;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.home.TechnologyBean;
import com.simson.www.net.bean.shop.CommentBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.PraiseModel;
import com.simson.www.ui.core.model.TechnologyModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.shop.detail.praise.PraiseContract;
import com.simson.www.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TechnologyPresenter extends BasePresenter<TechnologyContract.View>
        implements TechnologyContract.Presenter {
    private TechnologyModel mModel;
    private TechnologyContract.View mView;

    TechnologyPresenter() {
        this.mModel = new TechnologyModel();
    }

    @Override
    public void getPlantingTechnology() {
        mView = getView();
        RxObserver<List<TechnologyBean>> observer = new RxObserver<List<TechnologyBean>>(this) {

            @Override
            public void onSuccess(List<TechnologyBean> mData) {
                mView.getPlantingTechnology(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("hospitalId", mView.hospitalId());
        String json = new Gson().toJson(map);
        mModel.getPlantingTechnology(json, observer);
        addDisposable(observer);
    }


}
