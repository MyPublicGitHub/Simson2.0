package com.simson.www.ui.mine.wallet.log;

import com.simson.www.net.bean.mine.TransactionRecordBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface TransactionRecordContract {
    interface Presenter {

        void rechargeOrderList();


    }

    interface View extends IView {

        String pageIndex();

        void rechargeOrderList(List<TransactionRecordBean> bean);

    }
}
