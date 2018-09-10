package com.simson.www.ui.core.model;

import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

/**
 * 通用业务接口
 */

public interface ICommonItemTypeModel {



    void getItemType(RxObserver<List<ItemTypeBean>> callback);


}
