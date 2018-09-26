package com.simson.www.ui.mine.set.address.edit;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface EditAddressContract {
    interface Presenter {

        void editAddress();

    }

    interface View extends IView {
        String getDeliveryId();
        String getConsignee();//联系人必填
        String getContactNumber();//联系电话必填
        String getLocation();//地区必填
        String getAddress();//详细地址必填
        String getIsDefault();//1默认；0否必填
        void showSuccess(BaseBean bean);
    }
}
