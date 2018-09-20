package com.simson.www.ui.core.model;

import com.simson.www.net.api.ApiServer;
import com.simson.www.net.RxRetrofit;


public class BaseModel {

    public ApiServer doRxRequest() {
        return RxRetrofit.Api();
    }
}
