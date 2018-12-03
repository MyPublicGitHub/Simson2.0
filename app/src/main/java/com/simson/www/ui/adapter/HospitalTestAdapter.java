package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.mine.HospitalTestBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class HospitalTestAdapter extends BaseQuickAdapter<HospitalTestBean, BaseViewHolder> {

    public HospitalTestAdapter(@Nullable List<HospitalTestBean> data) {
        super(R.layout.item_hospital_test, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HospitalTestBean item) {
        GlideUtils.with(item.getHospital_icon(), helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_name, item.getHospital_name() + "");
        helper.setText(R.id.tv_date, item.getSubscribe_time());
    }
}
