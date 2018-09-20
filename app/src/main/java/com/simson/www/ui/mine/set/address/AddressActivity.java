package com.simson.www.ui.mine.set.address;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.set.address.add.NewAddressActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressActivity extends BasePresenterActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_new_address)
    TextView tvNewAddress;


    @OnClick({R.id.tv_new_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_new_address:
                startActivity(new Intent(this, NewAddressActivity.class));
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("收货地址");
        return true;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

}
