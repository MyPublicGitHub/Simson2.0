package com.simson.www.ui.home.item;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.home.HomeItemBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.impl.HomeItemModel;
import com.simson.www.ui.core.presenter.CommonItemTypePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeItemPresenter extends CommonItemTypePresenter<HomeItemContract.IHomeItemView> implements HomeItemContract.IHomeItemPresenter {
    private HomeItemModel mHomeItemModel;
    private HomeItemContract.IHomeItemView mView;

    HomeItemPresenter() {
        this.mHomeItemModel = new HomeItemModel();
    }


    @Override
    public void getHomeItemData() {
        mView = getView();
        RxObserver<List<HomeItemBean>> observer = new RxObserver<List<HomeItemBean>>(this) {
            @Override
            protected void onStart() {

            }

            @Override
            public void onSuccess(List<HomeItemBean> mData) {
                mView.setHomeItemData(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };
        Map map = new HashMap();
        map.put("customerId", SPUtils.get(Const.USERINFO.CUSTOMER_ID,"12312"));
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
