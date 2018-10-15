package com.simson.www.ui.mine.subscribe.save;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.core.view.IView;

public interface NewSubscribeContract {
    interface Presenter {

        void saveSubscribe();

    }

    interface View extends IView {

        void saveSubscribe(BaseBean bean);

        String hospitalId();//医院id必填

        String subscribeType();//预约类型；1预约项目；2脱发检测预约 必填

        String itemTypeId();//项目id必填；脱发检测0

        String mobile();//手机号必填

        String subscribeDate();//到院日期必填

        String subscribeTime();//到院时间必填

        String accompanyFriends();//有无好友陪同，多个逗号隔开

        String isCar();//是否开车，0否；1是

        String isSpecialCar();//是否预约专车，0否；1是

        String detailedAddress();//详细地址

        String remark();//备注

    }
}
