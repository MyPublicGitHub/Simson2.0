package com.simson.www.ui.mine.wallet.recharge;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.core.view.IView;

public interface RechargeContract {
    interface Presenter {

        void paymentRechargeOrder();


    }

    interface View extends IView {

        String transactionMoney();

        String paymentType();

        void paymentRechargeOrder(BaseBean bean);

    }
}
