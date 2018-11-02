package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.utils.GlideUtils;

import java.util.List;

public class PayAdapter extends BaseQuickAdapter<CommodityDetailBean, BaseViewHolder> {

    public PayAdapter(@Nullable List<CommodityDetailBean> data) {
        super(R.layout.item_pay, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityDetailBean item) {
        GlideUtils.with(item.getPicture().get(0), helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_title, item.getItem_name());
        helper.setText(R.id.tv_number, "X" + item.buyNumber);
        if (item.getIs_point() == 0) {
            helper.setText(R.id.tv_unity_price, "" + item.unityPrice);
        } else {
            helper.setText(R.id.tv_unity_price, "积分" + item.getItem_point());
        }
    }
}

