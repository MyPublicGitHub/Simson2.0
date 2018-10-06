package com.simson.www.ui.mine.pay;

import android.app.Activity;
import android.content.Context;

import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.net.bean.mine.PaymentOrderBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface PayContract {
    interface Presenter {

        void paymentOrder();

        //void alipay(Activity a, String orderInfo);

        //void wechatPay(Context context, PayReq payReq);

    }

    interface View extends IView {
        String getOrderId();
        String getTransactionMoney();
        String getTtransactionPoint();
        String getPaymentType();
        String getDeliveryId();

        void showPaymentOrder(PaymentOrderBean bean);
    }
}
