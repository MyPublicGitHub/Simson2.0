package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class SelectHospitalAdapter extends BaseQuickAdapter<HospitalItemBean, BaseViewHolder> {

    public SelectHospitalAdapter(@Nullable List<HospitalItemBean> data) {
        super(R.layout.item_select_hospital, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HospitalItemBean item) {

        GlideUtils.with(item.getHospital_icon(), helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_name, item.getHospital_name() + "");
        helper.setText(R.id.tv_hospital_name, item.getHospital_address());
    }
}
