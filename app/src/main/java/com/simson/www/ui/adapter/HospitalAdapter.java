package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.home.HospitalBean;

import java.util.List;

public class HospitalAdapter extends BaseQuickAdapter<HospitalBean, BaseViewHolder> {

    public HospitalAdapter(@Nullable List<HospitalBean> data) {
        super(R.layout.item_hospital_pop, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HospitalBean item) {
        helper.setText(R.id.tv_name, item.getHospital_name() + "");

    }
}

