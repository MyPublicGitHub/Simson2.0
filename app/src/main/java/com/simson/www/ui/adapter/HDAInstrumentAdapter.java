package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.home.HospitalDeviceBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class HDAInstrumentAdapter extends BaseQuickAdapter<HospitalDeviceBean, BaseViewHolder> {

    public HDAInstrumentAdapter(@Nullable List<HospitalDeviceBean> data) {
        super(R.layout.item_hda_instrument, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HospitalDeviceBean item) {
        GlideUtils.with(item.getDevice_picture(), helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_name, item.getDevice_name() + "");
    }
}