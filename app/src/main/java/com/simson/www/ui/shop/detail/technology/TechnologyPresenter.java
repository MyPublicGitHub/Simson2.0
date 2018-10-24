package com.simson.www.ui.shop.detail.praise;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.shop.CommentBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.PraiseModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PraisePresenter extends BasePresenter<PraiseContract.View>
        implements PraiseContract.Presenter {
    private PraiseModel mModel;
    private PraiseContract.View mView;

    PraisePresenter() {
        this.mModel = new PraiseModel();
    }

    @Override
    public void getCommodityQueryItemComment() {
        mView = getView();
        RxObserver<List<CommentBean>> observer = new RxObserver<List<CommentBean>>(this) {

            @Override
            public void onSuccess(List<CommentBean> mData) {
                mView.showCommodityQueryItemComment(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };

        Map<String, String> map = new HashMap();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("itemId", mView.getItemId());
        map.put("pageIndex", mView.getPageIndex());
        map.put("pageSize", Const.PAGE_SIZE);
        String json = new Gson().toJson(map);
        mModel.getCommodityQueryItemComment(json, observer);
        addDisposable(observer);
    }


}
