package com.simson.www.ui.adapter;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.mine.OrderBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class OrderItemAdapter extends BaseQuickAdapter<OrderBean.ItemsBean, BaseViewHolder> {
    public OrderItemAdapter(@Nullable List<OrderBean.ItemsBean> data) {
        super(R.layout.item_order_all_item_content, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean.ItemsBean item) {
        GlideUtils.with(item.getItem_icon(), helper.getView(R.id.iv_content));
        TextView original = helper.getView(R.id.tv_original);
        original.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        helper.setText(R.id.tv_content, item.getItem_name());
        helper.setText(R.id.tv_present, "￥" + item.getPresent_price());
        helper.setText(R.id.tv_original, "￥" + item.getOriginal_price());
        TextView textView = helper.getView(R.id.tv_original);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        helper.setText(R.id.tv_number, "X" + item.getBuy_num());
    }
}
