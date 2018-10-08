package com.simson.www.ui.home.item;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.HomeItemModel;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeItemPresenter extends CommonPresenter<HomeItemContract.View> implements HomeItemContract.Presenter {
    private HomeItemModel mHomeItemModel;
    private HomeItemContract.View mView;

    HomeItemPresenter() {
        this.mHomeItemModel = new HomeItemModel();
    }


    @Override
    public void getHomeItemData() {
        mView = getView();
        RxObserver<List<DiaryBean>> observer = new RxObserver<List<DiaryBean>>(this) {
            @Override
            protected void onStart() {

            }

            @Override
            public void onSuccess(List<DiaryBean> mData) {
                mView.setHomeItemData(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };
        Map map = new HashMap();
        map.put("customerId", SPUtils.get(Const.USER_INFO.CUSTOMER_ID,""));
        map.put("itemTypeId", mView.getItemType());//项目类型
        map.put("type", mView.getType());//1推荐，2关注，空不传是全部
        map.put("search", "");
        map.put("pageIndex", String.valueOf(mView.getPage()));
        map.put("pageSize", Const.PAGE_SIZE);
        map.put("timestamp", DateUtils.getStringDate());
        Gson gson = new Gson();
        String json = gson.toJson(map);
        mHomeItemModel.getHomeItemData(json,observer);
        addDisposable(observer);
    }

}
