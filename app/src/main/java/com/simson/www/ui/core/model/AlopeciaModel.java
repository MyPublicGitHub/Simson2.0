package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.AlopeciaBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class AlopeciaModel extends BaseModel {

    public void alopeciaTestingList(String json, RxObserver<List<AlopeciaBean>> rxObserver) {
        doRxRequest().
                alopeciaTestingList(AESUtils.encrypt(json))
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
