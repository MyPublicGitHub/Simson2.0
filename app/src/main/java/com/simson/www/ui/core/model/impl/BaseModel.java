package com.simson.www.ui.core.model.impl;

import com.simson.www.net.api.ApiServer;
import com.simson.www.net.RxRetrofit;
import com.simson.www.ui.core.model.IModel;


public class BaseModel implements IModel {

    @Override
    public ApiServer doRxRequest() {
        return RxRetrofit.Api();
    }
}
