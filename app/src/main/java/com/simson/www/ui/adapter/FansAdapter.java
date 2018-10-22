package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.net.bean.mine.FansBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class FansAdapter extends BaseQuickAdapter<FansBean, BaseViewHolder> {

    public FansAdapter(@Nullable List<FansBean> data) {
        super(R.layout.item_fans, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FansBean item) {
        GlideUtils.with(item.getCustomer_head(),helper.getView(R.id.iv_header));
        helper.setText(R.id.tv_name, item.getCustomer_name());
    }
}
