package com.simson.www.ui.main;

import com.simson.www.net.bean.main.NewestRedEnvelopeBean;
import com.simson.www.net.bean.main.ReceiveRedEnvelopeBean;
import com.simson.www.ui.core.view.IView;

public interface MainContract {
    interface Presenter {

        void newestRedEnvelope();

        void receiveRedEnvelope();


    }

    interface View extends IView {

        String crowd_no();

        void newestRedEnvelope(NewestRedEnvelopeBean bean);

        void newestRedEnvelopeFail();

        void receiveRedEnvelope(ReceiveRedEnvelopeBean bean);

    }
}
