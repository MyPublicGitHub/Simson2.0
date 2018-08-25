package com.simson.www.ui.core.model;

import com.simson.www.net.api.ApiServer;


public interface IModel {
    /**
     * 使用RxRetrofit请求数据
     *
     * @return
     */
    ApiServer doRxRequest();
}
