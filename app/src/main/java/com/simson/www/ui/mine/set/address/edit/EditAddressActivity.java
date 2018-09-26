package com.simson.www.ui.mine.set.address.edit;

import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.base.BasePresenterActivity;

public class EditAddressActivity extends BasePresenterActivity<EditAddressPresenter, EditAddressContract.View>
        implements EditAddressContract.View {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_address;
    }

    @Override
    public void showSuccess(BaseBean bean) {

    }

    @Override
    public String getDeliveryId() {
        return null;
    }

    @Override
    public String getConsignee() {
        return null;
    }

    @Override
    public String getContactNumber() {
        return null;
    }

    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String getIsDefault() {
        return null;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("修改地址");
        return true;
    }

    @Override
    protected EditAddressPresenter createPresenter() {
        return new EditAddressPresenter();
    }
}
