package com.simson.www.ui.mine.set.address.edit;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.AddressBean;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class EditAddressActivity extends BasePresenterActivity<EditAddressPresenter, EditAddressContract.View>
        implements EditAddressContract.View {
    @BindView(R.id.et_consignee)
    EditText etConsignee;
    @BindView(R.id.et_contact_number)
    EditText etContactNumber;
    @BindView(R.id.tv_as_located)
    TextView tvAsLocated;
    @BindView(R.id.et_detailed_address)
    EditText etDetailedAddress;
    @BindView(R.id.switch_default_address)
    Switch switchDefaultAddress;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_address;
    }

    @Override
    protected void initViews() {
        initPicker();
        etConsignee.setText(dataBean.getConsignee() + "");
        etContactNumber.setText(dataBean.getContact_number() + "");
        tvAsLocated.setText(dataBean.getLocation() + "");
        etDetailedAddress.setText(dataBean.getAddress() + "");
        switchDefaultAddress.setChecked(dataBean.getIs_default() == 0 ? false : true);
    }

    AddressBean dataBean;

    @Override
    protected void getIntent(Intent intent) {
        dataBean = (AddressBean) intent.getSerializableExtra("data");
    }

    @Override
    public void showSuccess(BaseBean bean) {
        ToastUtils.showToast("编辑成功");
    }

    @OnClick({R.id.tv_as_located, R.id.tv_preservation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_as_located:
                mPicker.showCityPicker();
                break;
            case R.id.tv_preservation:
                mPresenter.editAddress();
                break;
        }
    }

    @Override
    public String getDeliveryId() {
        return dataBean.getDelivery_id();
    }

    @Override
    public String getConsignee() {
        return etConsignee.getText().toString();
    }

    @Override
    public String getContactNumber() {
        return etContactNumber.getText().toString();
    }

    @Override
    public String getLocation() {
        return tvAsLocated.getText().toString();
    }

    @Override
    public String getAddress() {
        return etDetailedAddress.getText().toString();
    }

    @Override
    public String getIsDefault() {
        return switchDefaultAddress.isChecked() ? "1" : "0";
    }


    @Override
    protected boolean initToolbar() {
        mTitle.setText("编辑地址");
        return true;
    }

    @Override
    protected EditAddressPresenter createPresenter() {
        return new EditAddressPresenter();
    }

    String provinceStr, cityStr, districtStr;
    CityPickerView mPicker;

    private void initPicker() {
        mPicker = new CityPickerView();
        mPicker.init(this);
        //添加默认的配置，不需要自己定义
        CityConfig cityConfig = new CityConfig.Builder().build();
        cityConfig.setProvinceCyclic(false);
        cityConfig.setCityCyclic(false);
        cityConfig.setDistrictCyclic(false);
        mPicker.setConfig(cityConfig);
        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                //省份
                if (province != null) {
                    provinceStr = province.getName();
                }
                //城市
                if (city != null) {
                    cityStr = city.getName();
                }
                //地区
                if (district != null) {
                    districtStr = district.getName();
                }
                tvAsLocated.setText(provinceStr + cityStr + districtStr);
            }

            @Override
            public void onCancel() {
                //ToastUtils.showToast("已取消");
            }
        });
    }
}
