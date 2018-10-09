package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.TransactionRecordBean;
import com.simson.www.net.callback.RxObserver;

import java.util.List;

public class TransactionRecordModel extends BaseModel {

    public void rechargeOrderList(String json, RxObserver<List<TransactionRecordBean>> rxObserver) {
        doRxRequest().
                rechargeOrderList(json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
