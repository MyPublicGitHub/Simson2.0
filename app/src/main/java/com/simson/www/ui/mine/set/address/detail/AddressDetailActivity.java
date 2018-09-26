package com.simson.www.ui.mine.set.address.detail;

import com.simson.www.R;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.net.bean.mine.AddressDetailBean;
import com.simson.www.ui.base.BasePresenterActivity;

import java.util.List;

public class AddressDetailActivity extends BasePresenterActivity<AddressDetailPresenter, AddressDetailContract.View>
        implements AddressDetailContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_detail;
    }

    @Override
    public String getDeliveryId() {
        return null;
    }

    @Override
    public void showAddressDetail(AddressDetailBean bean) {

    }

    @Override
    protected AddressDetailPresenter createPresenter() {
        return new AddressDetailPresenter();
    }
}
