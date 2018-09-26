package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.community.DoctorBean;

import java.util.List;

public class ExpertCommunityAdapter extends BaseQuickAdapter<DoctorBean.DoctorItemBean, BaseViewHolder> {

    public ExpertCommunityAdapter(@Nullable List<DoctorBean.DoctorItemBean> data) {
        super(R.layout.item_expert_commuinty, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorBean.DoctorItemBean item) {

    }
}

