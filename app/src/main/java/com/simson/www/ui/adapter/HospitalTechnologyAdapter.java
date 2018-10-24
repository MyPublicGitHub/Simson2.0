package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.home.HospitalBean;
import com.simson.www.net.bean.home.TechnologyBean;

import java.util.List;

public class HospitalTechnologyAdapter extends BaseQuickAdapter<TechnologyBean, BaseViewHolder> {

    public HospitalTechnologyAdapter(@Nullable List<TechnologyBean> data) {
        super(R.layout.item_hospital_technology_pop, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TechnologyBean item) {
        helper.setText(R.id.tv_technology, item.getTechnology_name() + "/");
        helper.setText(R.id.tv_price, item.getUnit_price() + "元");

    }
}

