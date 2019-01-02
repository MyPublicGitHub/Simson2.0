package com.simson.www.ui.core.model;


import com.simson.www.net.RxSchedulers;
import com.simson.www.net.bean.mine.RedEnvelopesBean;
import com.simson.www.net.bean.mine.TransactionRecordBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.utils.AESUtils;

import java.util.List;

public class RedEnvelopesModel extends BaseModel {

    public void redEnvelopesRecord(String url,String json, RxObserver<List<RedEnvelopesBean>> rxObserver) {
        doRxRequest().
                redEnvelopesRecord(url,json)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }
}
