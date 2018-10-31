package com.simson.www.ui.mine.test.save;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.core.view.IView;

public interface NewHospitalTestContract {
    interface Presenter {

        void saveHospitalTesting();

    }

    interface View extends IView {

        String hospitalId();

        String mobile();

        String subscribeTime();

        String customerName();

        String customerSex();

        String customerAge();

        String remark();

        void saveHospitalTesting(BaseBean bean);
    }
}
