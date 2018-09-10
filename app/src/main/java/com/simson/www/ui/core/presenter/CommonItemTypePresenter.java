package com.simson.www.ui.core.presenter;


import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.impl.CommonItemTypeModel;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public class CommonItemTypePresenter<V extends IView> extends BasePresenter<V> implements ICommonItemTypePresenter {
    private CommonItemTypeModel mCommonModel;

    public CommonItemTypePresenter() {
        this.mCommonModel = new CommonItemTypeModel();
    }


    @Override
    public void getItemType() {
        RxObserver<List<ItemTypeBean>> mCollectRxObserver = new RxObserver<List<ItemTypeBean>>(this) {

            @Override
            protected void onSuccess(List<ItemTypeBean> data) {
                view.setItemType(data);
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                view.showFail(errorMsg);
            }


        };
        mCommonModel.getItemType(mCollectRxObserver);
        addDisposable(mCollectRxObserver);
    }


}
