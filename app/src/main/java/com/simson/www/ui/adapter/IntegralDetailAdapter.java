package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.mine.IntegralDetailBean;

import java.util.List;

public class IntegralDetailAdapter extends BaseQuickAdapter<IntegralDetailBean, BaseViewHolder> {
    public IntegralDetailAdapter(@Nullable List<IntegralDetailBean> data) {
        super(R.layout.item_integral_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntegralDetailBean item) {
        helper.setText(R.id.point, item.getRecord_name() + "：" + item.getPoint());
        helper.setText(R.id.tv_date, item.getCreate_time());
    }
}