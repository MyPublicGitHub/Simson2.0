package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.DoctorBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class HDAExpertAdapter extends BaseQuickAdapter<DoctorBean.DoctorItemBean, BaseViewHolder> {

    public HDAExpertAdapter(@Nullable List<DoctorBean.DoctorItemBean> data) {
        super(R.layout.item_hda_expert, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorBean.DoctorItemBean item) {

        GlideUtils.with(item.getDoctor_head(), helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_name, item.getDoctor_name() + "");
        helper.setText(R.id.tv_post, item.getPosition() + "");
        helper.setText(R.id.tv_appointment, "擅长：" + item.getPosition());
        helper.addOnClickListener(R.id.tv_follow);
    }
}

