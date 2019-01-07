package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class HomeAdapterItemAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HomeAdapterItemAdapter(@Nullable List<String> data) {
        super(R.layout.item_home_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        GlideUtils.with(item, helper.getView(R.id.iv_image));
    }
}
