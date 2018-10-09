package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.home.HospitalItemBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class HospitalItemAdapter extends BaseQuickAdapter<HospitalItemBean, BaseViewHolder> {

    public HospitalItemAdapter(@Nullable List<HospitalItemBean> data) {
        super(R.layout.item_hospital_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HospitalItemBean item) {

        GlideUtils.with(item.getHospital_icon(), helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_name, item.getHospital_name() + "");
        helper.setRating(R.id.rate, item.getHospital_star());
        helper.setText(R.id.tv_case, item.getSubscribes() + "人预约");
        helper.setText(R.id.tv_hospital_name, item.getHospital_address());
        helper.setText(R.id.tv_reserve, "" + item.getConsulting_phone());
    }
}
