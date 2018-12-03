package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.mine.AddressBean;

import java.util.List;

public class AddressAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {

    public AddressAdapter(@Nullable List<AddressBean> data) {
        super(R.layout.item_address, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean item) {
        /**
         * delivery_id : 3acdb0e085944f27837e6aa597d1f9b5
         * customer_id : 2018090115357871316905625
         * address : sdf  df
         * consignee : ff
         * contact_number : 17633717732
         * is_default : 0
         * location : 浙江省杭州市滨江区
         */
        helper.setText(R.id.tv_name, item.getConsignee() + "");
        helper.setText(R.id.tv_phone, item.getContact_number());
        helper.setText(R.id.tv_address, item.getLocation() + item.getAddress());
        helper.setChecked(R.id.cb_default, item.getIs_default() == 1);
        helper.addOnClickListener(R.id.tv_edit);
        helper.addOnClickListener(R.id.tv_delete);
//        helper.setOnCheckedChangeListener(R.id.cb_default, new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked)
//                    activity.setDefaultAddress(item.getId());
//            }
//        });
    }
}
