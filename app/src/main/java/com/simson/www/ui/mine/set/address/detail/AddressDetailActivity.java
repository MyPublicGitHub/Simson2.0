package com.simson.www.ui.mine.set.address.detail;

import android.content.Intent;

import com.simson.www.R;
import com.simson.www.net.bean.mine.AddressDetailBean;
import com.simson.www.ui.base.BasePresenterActivity;

public class AddressDetailActivity extends BasePresenterActivity<AddressDetailPresenter, AddressDetailContract.View>
        implements AddressDetailContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_detail;
    }

    @Override
    protected void initData() {
        mPresenter.getAddressDetail();
    }

    @Override
    protected void getIntent(Intent intent) {
        deliveryId = intent.getStringExtra("deliveryId");
    }

    String deliveryId;

    @Override
    public String getDeliveryId() {
        return deliveryId;
    }

    @Override
    public void showAddressDetail(AddressDetailBean bean) {

    }

    @Override
    protected AddressDetailPresenter createPresenter() {
        return new AddressDetailPresenter();
    }
}
