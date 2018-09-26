package com.simson.www.ui.mine.set.address.add;

import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.base.BasePresenterActivity;

public class NewAddressActivity extends BasePresenterActivity<NewAddressPresenter, NewAddressContract.View>
        implements NewAddressContract.View {


    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_address;
    }

    @Override
    protected NewAddressPresenter createPresenter() {
        return new NewAddressPresenter();
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
    public void showSuccess(BaseBean bean) {

    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("新增收货地址");
        return true;
    }

}
