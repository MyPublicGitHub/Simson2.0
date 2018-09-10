package com.simson.www.ui.core.model.impl;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.ICommonItemTypeModel;

import java.util.List;


public class CommonItemTypeModel extends BaseModel implements ICommonItemTypeModel {
    @Override
    public void getItemType(RxObserver<List<ItemTypeBean>> callback) {
        doRxRequest().
                getItemType()
                .compose(RxSchedulers.io_main())
                .subscribe(callback);

    }

}
