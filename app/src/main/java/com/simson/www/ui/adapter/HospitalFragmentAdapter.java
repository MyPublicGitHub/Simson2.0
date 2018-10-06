package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;

import java.util.List;

public class HospitalFragmentAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {

    public HospitalFragmentAdapter(@Nullable List<BaseBean> data) {
        super(R.layout.item_hospital, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
        // Glide.with(helper.getView(R.id.popLayoutId).getContext()).
    }


}
