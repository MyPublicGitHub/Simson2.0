package com.simson.www.ui.mine.set.address.add;

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
import com.simson.www.ui.base.BasePresenterActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class NewAddressActivity extends BasePresenterActivity<NewAddressPresenter, NewAddressContract.View>
        implements NewAddressContract.View {


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
    @BindView(R.id.tv_preservation)
    TextView tvPreservation;

    @Override
    protected void initViews() {
        initPicker();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_address;
    }


    @OnClick({R.id.tv_as_located, R.id.tv_preservation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_as_located:
                mPicker.showCityPicker();
                break;
            case R.id.tv_preservation:
                mPresenter.newAddress();
                break;
        }
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
    public void showSuccess(BaseBean bean) {

    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("新增收货地址");
        return true;
    }

    @Override
    protected NewAddressPresenter createPresenter() {
        return new NewAddressPresenter();
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
